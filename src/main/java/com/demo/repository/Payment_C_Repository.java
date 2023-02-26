package com.demo.repository;

import com.demo.entity.Payment_C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Payment_C_Repository extends JpaRepository<Payment_C, String>{
}
