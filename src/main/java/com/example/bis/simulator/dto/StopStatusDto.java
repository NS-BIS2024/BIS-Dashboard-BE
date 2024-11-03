package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopStatusDto {
    private String 현재정류장;
    private String 다음정류장;
}
