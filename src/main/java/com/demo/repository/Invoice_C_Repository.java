package com.demo.repository;

import com.demo.entity.Customer_Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Invoice_C_Repository extends JpaRepository<Customer_Invoice, String> {
}
