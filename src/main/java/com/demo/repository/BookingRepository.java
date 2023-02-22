package com.demo.repository;

import com.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(
        value = "select b.* from Booking b where b.start_date = ?1 and b.end_date = ?2 and b.start_time = ?3 and " +
                "b.end_time = ?4 and b.id_customer = ?5 and b.id_c_slot = ?6", nativeQuery = true
    )
    Booking findidBooking(Date startDate, Date endDate, String startTime, String endTime, String id_Customer, String id_C_slot);


}
