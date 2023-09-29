package com.bottrack.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailConfiguration {
    String from;
    int port;
    boolean auth;
    int timeout;
    boolean starttls;
}
