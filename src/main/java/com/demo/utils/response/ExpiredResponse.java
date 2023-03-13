package com.demo.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredResponse {
    String id_user;
    String current_date;
    String current_time;
    Date end_date;
    String end_time;
    Long expired;
    Float fine;
    Boolean warning;
}
