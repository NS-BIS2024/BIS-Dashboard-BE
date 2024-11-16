package com.example.bis.simulator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusDataDTO {
    private String colDate;
    private String busId;
    private String preSttId; // 보류
    private String nextSttId; // 보류
    private Double longitude;
    private Double latitude;
    private String eventCode; // 보류
}
