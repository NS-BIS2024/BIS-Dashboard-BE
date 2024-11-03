package com.example.bis.simulator.model;

import java.io.Serializable;
import java.util.Objects;

public class M_OP_ROUTE_POINT_ID implements Serializable {

    private String routeId;
    private Integer pointSqno;

    public M_OP_ROUTE_POINT_ID() {}

    public M_OP_ROUTE_POINT_ID(String routeId, Integer pointSqno) {
        this.routeId = routeId;
        this.pointSqno = pointSqno;
    }

    // equals 및 hashCode 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        M_OP_ROUTE_POINT_ID that = (M_OP_ROUTE_POINT_ID) o;
        return Objects.equals(routeId, that.routeId) && Objects.equals(pointSqno, that.pointSqno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, pointSqno);
    }

    // Getters 및 Setters (필요한 경우)
}
