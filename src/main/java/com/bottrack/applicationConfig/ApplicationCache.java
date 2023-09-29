package com.bottrack.applicationConfig;

import com.bottrack.model.CacheModal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ApplicationCache {
    @Bean
    public Map<String, CacheModal> memoryCache() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtpout.asia.secureserver.net");
        mailSender.setPort(587);
        mailSender.setUsername("info@bottomhalf.in");
        mailSender.setPassword("Bottomhalf@i9_0012");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");

        return mailSender;
    }
}
