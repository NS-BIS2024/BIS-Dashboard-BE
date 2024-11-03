package com.example.bis.simulator.api;

import com.example.bis.simulator.dto.StopStatusDto;
import com.example.bis.simulator.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteControllerApi {

    @Autowired
    private RouteService routeService;

    // 특정 ROUTE_ID에 해당하는 현재 정류장과 다음 정류장 목록 조회
    @GetMapping("/{routeId}/stops")
    public ResponseEntity<List<StopStatusDto>> getBusStopPairsByRouteId(@PathVariable String routeId) {
        List<StopStatusDto> busStopPairs = routeService.getBusStopPairsByRouteId(routeId);
        if (busStopPairs == null || busStopPairs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(busStopPairs);
        }
    }
}
