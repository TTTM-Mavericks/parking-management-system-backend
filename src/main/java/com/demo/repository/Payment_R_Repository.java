package com.demo.repository;

import com.demo.entity.Payment_R;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Payment_R_Repository extends JpaRepository<Payment_R, String> {
}
