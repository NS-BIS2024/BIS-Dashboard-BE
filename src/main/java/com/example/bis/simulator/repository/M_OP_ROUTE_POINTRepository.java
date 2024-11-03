package com.example.bis.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bis.simulator.model.M_OP_ROUTE_POINT;
import com.example.bis.simulator.model.M_OP_ROUTE_POINT_ID;
import java.util.List;

public interface M_OP_ROUTE_POINTRepository extends JpaRepository<M_OP_ROUTE_POINT, M_OP_ROUTE_POINT_ID> {

    // 특정 ROUTE_ID와 POINT_DIV 조건으로 데이터 조회
    List<M_OP_ROUTE_POINT> findByRouteIdAndPointDiv(String routeId, String pointDiv);
}
