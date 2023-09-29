package com.bottrack.service;

import com.bottrack.model.EmailConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailService {
    Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    EmailConfiguration emailConfiguration;

    public Optional<String> sendOTPEmail(String toAddress, String subject, String body) {
        String status = null;
        try {
            sendEmail(toAddress, subject, body);
            status = "OTP send successfully";
        } catch (Exception ex) {
            logger.error("Fail to send the OPT email.", ex);
            return Optional.empty();
        }

        return Optional.of(status);
    }

    private void sendEmail(String toAddress, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom("info@bottomhalf.in");
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(mimeMessage);
    }
}
