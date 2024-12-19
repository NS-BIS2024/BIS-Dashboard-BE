package com.example.bis.simulator.dto;

public class StopStatusDTO {
    private String 정류장명;  // Current stop name
    private String 다음정류장; // Next stop name

    public StopStatusDTO(String 정류장명, String 다음정류장) {
        this.정류장명 = 정류장명;
        this.다음정류장 = 다음정류장;
    }

    // Getter and Setter (optional)
    public String get정류장명() {
        return 정류장명;
    }

    public void set정류장명(String 정류장명) {
        this.정류장명 = 정류장명;
    }

    public String get다음정류장() {
        return 다음정류장;
    }

    public void set다음정류장(String 다음정류장) {
        this.다음정류장 = 다음정류장;
    }
}
