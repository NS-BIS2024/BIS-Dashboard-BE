package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_TP_BSTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StopRepository extends JpaRepository<M_TP_BSTP, String> {

    // 현재 정류장 조회
    @Query("SELECT b FROM M_OP_ROUTE_POINT p JOIN M_TP_BSTP b ON p.pointId = b.bstpId " +
            "WHERE p.routeId = :routeId AND b.bstpId = :currentStopId AND p.pointDiv = '1'")
    Optional<M_TP_BSTP> findBusStopByRouteAndId(@Param("routeId") String routeId, @Param("currentStopId") String currentStopId);

    // 다음 정류장 조회 (정류장만 필터링, POINT_DIV = '1')
    @Query("SELECT b FROM M_OP_ROUTE_POINT p JOIN M_TP_BSTP b ON p.pointId = b.bstpId " +
            "WHERE p.routeId = :routeId AND p.pointSqno > " +
            "(SELECT p2.pointSqno FROM M_OP_ROUTE_POINT p2 WHERE p2.routeId = :routeId AND p2.pointId = :currentStopId) " +
            "AND p.pointDiv = '1' ORDER BY p.pointSqno ASC")
    List<M_TP_BSTP> findNextBusStopByRoute(@Param("routeId") String routeId, @Param("currentStopId") String currentStopId);
}
