package com.example.bis.simulator.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusStopInfoDTO {
    private String bstpId;
    private String bstpNm;
    private String nextBstpNm;
    
}
