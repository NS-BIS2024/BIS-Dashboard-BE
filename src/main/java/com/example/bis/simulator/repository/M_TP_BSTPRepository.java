package com.example.bis.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bis.simulator.model.M_TP_BSTP;

import java.util.Optional;

public interface M_TP_BSTPRepository extends JpaRepository<M_TP_BSTP, String> {
    // BSTP_ID를 통해 정류장 조회
    Optional<M_TP_BSTP> findByBstpId(String bstpId);
}
