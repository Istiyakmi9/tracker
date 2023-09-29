package com.bottrack.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CacheModal {
    int oTP;
    Date generatedOn;
    Date expiryTime;
}
