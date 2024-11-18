package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.StopStatusDTO;
import com.example.bis.simulator.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    @Autowired
    private StopService stopService;

    @GetMapping("/{routeId}/{currentStopId}")
    public ResponseEntity<StopStatusDTO> getCurrentAndNextStops(@PathVariable String routeId, @PathVariable String currentStopId) {
        return stopService.getCurrentAndNextStops(routeId, currentStopId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
