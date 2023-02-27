package com.demo.repository;

import com.demo.entity.Payment_C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Payment_C_Repository extends JpaRepository<Payment_C, String>{
    @Query
    (
            value = "select pc.* from payment_c pc join booking b on b.id_booking = pc.id_booking " +
                    "where b.id_booking = ?1", nativeQuery = true
    )
    Payment_C findPayment_C_By_Id_Booking(Long id_Booking);

    @Modifying
    @Query
    (value = "update payment_c set type_of_payment = ?1 where id_booking = ?2", nativeQuery = true)
    int updateTypeOfPayment(String type_of_payment, Long id_Booing);
}
