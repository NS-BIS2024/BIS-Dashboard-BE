package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusDataDTO;
import com.example.bis.simulator.dto.BusInformationDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.model.M_OP_BUS;
import com.example.bis.simulator.model.M_OP_OBU;
import com.example.bis.simulator.model.M_OP_ROUTE_POINT;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BusInformationService {

    @PersistenceContext
    private EntityManager entityManager;

    // OBU ID를 사용하여 BUS 및 OBU 정보로 BusInformationDTO 반환
    public Optional<BusInformationDTO> getBusInformationByObuId(String obuId) {
        // OBU 테이블에서 OBU ID로 조회
        M_OP_OBU obu = entityManager.find(M_OP_OBU.class, obuId);
        if (obu != null && obu.getBusId() != null) {
            // BUS 테이블에서 busId로 조회
            M_OP_BUS bus = entityManager.find(M_OP_BUS.class, obu.getBusId());

            if (bus != null) {
                // DTO에 필요한 정보 설정
                BusInformationDTO busInformationDTO = new BusInformationDTO();
                busInformationDTO.setBusNo(bus.getBusNo());
                busInformationDTO.setRideFixNumber(bus.getRideFixNumber());
                busInformationDTO.setInspectionDate(bus.getInspectionDate());
                busInformationDTO.setMnfctCoNm(obu.getMnfctCoNm());

                return Optional.of(busInformationDTO);
            }
        }

        return Optional.empty();
    }

    public List<BusDataDTO> getBusData(String routeId) {
        // Step 1: C_TC_BUS_RUNG 테이블에서 routeId로 데이터를 가져오기
        String busRungQuery = "SELECT b FROM C_TC_BUS_RUNG b WHERE b.routeId = :routeId";
        List<C_TC_BUS_RUNG> busRungs = entityManager.createQuery(busRungQuery, C_TC_BUS_RUNG.class)
                .setParameter("routeId", routeId)
                .getResultList();

        // Step 2: 각 버스의 preSttId와 nextSttId를 가져오기
        return busRungs.stream().map(busRung -> {
            // 이전 정류장 ID 가져오기
            String preSttIdQuery = "SELECT p FROM M_OP_ROUTE_POINT p WHERE p.routeId = :routeId AND p.pointSqno = :pointSqno";
            String preSttId = entityManager.createQuery(preSttIdQuery, M_OP_ROUTE_POINT.class)
                    .setParameter("routeId", busRung.getRouteId())
                    .setParameter("pointSqno", busRung.getPassagePointSqNo())
                    .getResultStream()
                    .findFirst()
                    .map(M_OP_ROUTE_POINT::getRouteId)
                    .orElse(null);

            // 다음 정류장 ID 가져오기
            String nextSttIdQuery = "SELECT p FROM M_OP_ROUTE_POINT p WHERE p.routeId = :routeId AND p.pointSqno = :pointSqno";
            String nextSttId = entityManager.createQuery(nextSttIdQuery, M_OP_ROUTE_POINT.class)
                    .setParameter("routeId", busRung.getRouteId())
                    .setParameter("pointSqno", busRung.getArrivalPlannedPointSqNo())
                    .getResultStream()
                    .findFirst()
                    .map(M_OP_ROUTE_POINT::getRouteId)
                    .orElse(null);

            // BusDataDTO 생성
            return BusDataDTO.builder()
                    .colDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .busId(busRung.getObuId())
                    .preSttId(preSttId)
                    .nextSttId(nextSttId)
                    .longitude(busRung.getXCord().doubleValue())
                    .latitude(busRung.getYCord().doubleValue())
                    .eventCode(busRung.getRungStatus().equals("1") ? "RUNNING" : "STOPPED")
                    .build();

        }).collect(Collectors.toList());
    }
}
