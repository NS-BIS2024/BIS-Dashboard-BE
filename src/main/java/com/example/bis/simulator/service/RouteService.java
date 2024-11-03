package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.StopStatusDto;
import com.example.bis.simulator.dto.StopStatusDto;
import com.example.bis.simulator.model.M_OP_ROUTE_POINT;
import com.example.bis.simulator.model.M_TP_BSTP;
import com.example.bis.simulator.repository.M_OP_ROUTE_POINTRepository;
import com.example.bis.simulator.repository.M_TP_BSTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private M_OP_ROUTE_POINTRepository mOpRoutePointRepository;

    @Autowired
    private M_TP_BSTPRepository mTpBstpRepository;

    // 특정 ROUTE_ID에 해당하는 현재 정류장과 다음 정류장 조회
    public List<StopStatusDto> getBusStopPairsByRouteId(String routeId) {
        // POINT_DIV가 '1'인 정류장 목록 조회
        List<M_OP_ROUTE_POINT> routePoints = mOpRoutePointRepository.findByRouteIdAndPointDiv(routeId, "1");

        List<StopStatusDto> busStopPairs = new ArrayList<>();

        // 현재 정류장과 다음 정류장을 순서대로 설정하여 BusStopDto로 변환
        for (int i = 0; i < routePoints.size() - 1; i++) {
            Optional<M_TP_BSTP> currentStop = mTpBstpRepository.findByBstpId(routePoints.get(i).getPointId());
            Optional<M_TP_BSTP> nextStop = mTpBstpRepository.findByBstpId(routePoints.get(i + 1).getPointId());

            if (currentStop.isPresent() && nextStop.isPresent()) {
                StopStatusDto dto = new StopStatusDto(
                        currentStop.get().getBstpNm(), // 현재 정류장 이름
                        nextStop.get().getBstpNm()    // 다음 정류장 이름
                );
                busStopPairs.add(dto);
            }
        }

        return busStopPairs;
    }
}
