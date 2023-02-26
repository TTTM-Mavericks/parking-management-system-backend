package com.demo.repository;

import com.demo.entity.Resident_Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Invoice_R_Repository extends JpaRepository<Resident_Invoice, String> {
}
