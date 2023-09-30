package com.bottrack.service;

import com.bottrack.model.CacheModal;
import com.bottrack.model.Login;
import com.bottrack.repository.LoginRepository;
import com.bottrack.serviceinterfaces.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class LoginService implements ILoginService {
    private final ApplicationCache applicationCache;
    @Autowired
    LoginRepository loginRepository;

    @Value("${spring.otp.durationinminutes}")
    int otpDuration;

    @Autowired
    EmailService emailService;

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    public LoginService() {
        this.applicationCache = ApplicationCache.getInstance();
    }

    public ResponseEntity<Object> updateLoginByUserIdService(Login login, long userId){
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        login.setCreatedOn(date);
        Optional<Login> result = this.loginRepository.findById(userId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        login.setUserId(userId);
        loginRepository.save(login);
        return ResponseEntity.noContent().build();
    }

    public Login authenticateUserService(String emailOrMobile) {
        Login login = null;
        try {
            login = loginRepository.getLoginByMobile(emailOrMobile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return login;
    }

    public Login getLoginByMobile(String mobile) {
        Login login = null;
        try {
            login = loginRepository.getLoginByMobile(mobile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return login;
    }

    public String resetPasswordWithOTPService(Login login) throws Exception {
        if (login.getEmail().isEmpty())
            throw new Exception("invalid email id");

        if (login.getPassword().isEmpty())
            throw new Exception("invalid password");

        var loginDetail = loginRepository.getLoginByEmail(login.getEmail());
        if (loginDetail == null)
            throw new Exception("Invalid email entered");

        CacheModal cacheModal = this.applicationCache.get(loginDetail.getEmail());
        if (cacheModal == null || cacheModal.getOTP() != login.getOtp()) {
            throw new Exception("OTP is not matching or invalid.");
        }

        loginDetail.setPassword(EncryptPassword(login.getPassword()));
        loginRepository.save(loginDetail);
        return "Password reset successfully";
    }

    public String generateOTPForResetPassword(Login login) throws Exception {
        if (login.getEmail().isEmpty())
            throw new Exception("invalid email id");

        var loginDetail = loginRepository.getLoginByEmail(login.getEmail());
        if (loginDetail == null)
            throw new Exception("Your account doesn't exists with this email");

        int otp = getOTP();
        Calendar calendar = Calendar.getInstance();
        var now = calendar.getTime();

        calendar.add(Calendar.SECOND, this.otpDuration);
        var expiryTime = calendar.getTime();

        applicationCache.put(loginDetail.getEmail(),
                CacheModal.builder()
                        .oTP(otp)
                        .generatedOn(now)
                        .expiryTime(expiryTime)
                        .build()
        );

        String stringBuilder = "<html><body><p style='margin-top: 10px; margin-bottom: 25px;'>Hi " + loginDetail.getUserName() + "</p>" +
                "<span>Your OTP is <b>" + otp + "</b>.</span>" +
                "<span>This will expired in 1 hours.</span>" +
                "<div style='margin-top: 50px;'>Regards,</div>" +
                "<div>Team VoltMate</div></body></html>";

        scheduleRemoveOTP(expiryTime, loginDetail.getEmail());

        var dateFormat = new SimpleDateFormat("dd MMM, yyyy");

        var result = emailService.sendOTPEmail(loginDetail.getEmail(), "OPT request | " + dateFormat.format(expiryTime), stringBuilder);
        result.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fail to generate OTP. Please contact to admin."));
        return "OTP send successfully on your registered Email";
    }

    private void scheduleRemoveOTP(Date startTime, String email) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        TaskScheduler scheduler = new ConcurrentTaskScheduler(executorService);

        OTPClearanceJob job = new OTPClearanceJob(email);
        scheduler.schedule(job, startTime);

        executorService.shutdown();
    }

    private int getOTP() {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a 4-digit random number
        int min = 1000;  // Minimum 4-digit number
        int max = 9999;  // Maximum 4-digit number
        return random.nextInt(max - min + 1) + min;
    }

    private String EncryptPassword(String password) throws Exception {
        if(password == null || password.isEmpty()){
            throw new Exception("Invalid password for new user.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}

class OTPClearanceJob implements Runnable {
    private final String email;
    public OTPClearanceJob(String email) {
        this.email = email;
    }
    @Override
    public void run() {
        System.out.println("Schedular job ran successfully");

        var _cache = ApplicationCache.getInstance();
        _cache.remove(this.email);
    }
}
