package com.bottrack.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class utils {
    @Bean(name = "currentDate")
    public Date getDate() {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        System.out.println("Current Date: " + date);
        return date;
    }

    @Bean(name = "newDate")
    public Date getCurrentDate() {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        System.out.println("New Date: " + date);
        return date;
    }
}
