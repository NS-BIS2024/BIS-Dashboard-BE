package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.StopStatusDTO;
import com.example.bis.simulator.model.M_TP_BSTP;
import com.example.bis.simulator.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    public Optional<StopStatusDTO> getCurrentAndNextStops(String routeId, String currentStopId) {
        // 현재 정류장 조회
        Optional<M_TP_BSTP> currentStopOpt = stopRepository.findBusStopByRouteAndId(routeId, currentStopId);

        // 다음 정류장 조회
        List<M_TP_BSTP> nextStops = stopRepository.findNextBusStopByRoute(routeId, currentStopId);

        // 현재 정류장이 없는 경우 처리
        if (currentStopOpt.isEmpty() || nextStops.isEmpty()) {
            return Optional.empty();
        }

        M_TP_BSTP currentStop = currentStopOpt.get();
        M_TP_BSTP nextStop = nextStops.get(0);

        // DTO 생성
        return Optional.of(new StopStatusDTO(
                currentStop.getBstpNm(),
                nextStop.getBstpNm()
        ));
    }
}
