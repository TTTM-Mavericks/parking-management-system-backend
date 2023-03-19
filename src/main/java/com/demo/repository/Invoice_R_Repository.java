package com.demo.repository;

import com.demo.entity.Resident_Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface Invoice_R_Repository extends JpaRepository<Resident_Invoice, String> {

    @Query
            (value = "select ri.* from resident_invoice ri join payment_r pr on pr.id_payment = ri.id_payment " +
                    "where pr.id_payment = ?1", nativeQuery = true)
    Resident_Invoice findResident_InvoiceByResidentPayment(String id_Payment);

    @Query
            (value = "update resident_invoice set status = ?1 where id_r_invoice = ?2", nativeQuery = true)
    void updateStatus(boolean status, String id_invoice);
    @Query
            (value = "update resident_invoice set time = ?1 where id_r_invoice = ?2", nativeQuery = true)
    void updateTime(Date status, String id_invoice);
}
