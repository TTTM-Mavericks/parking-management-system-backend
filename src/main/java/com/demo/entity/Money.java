package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Money {
    public static double CAR_MONEY_BY_DAY;
    public static double BIKE_MONEY_BY_DAY;
    public static double MOTO_MONEY_BY_DAY;
    public static double CAR_MONEY_BY_MONTH;
    public static double BIKE_MONEY_BY_MONTH;
    public static double MOTO_MONEY_BY_MONTH;


    @Override
    public String toString() {
        return
                "CAR_MONEY_BY_DAY='" + CAR_MONEY_BY_DAY + '\'' +
                ", BIKE_MONEY_BY_DAY='" + BIKE_MONEY_BY_DAY + '\'' +
                ", MOTO_MONEY_BY_DAY='" + MOTO_MONEY_BY_DAY + '\'' +
                ", CAR_MONEY_BY_MONTH='" + CAR_MONEY_BY_MONTH + '\'' +
                ", BIKE_MONEY_BY_MONTH='" + BIKE_MONEY_BY_MONTH + '\'' +
                ", MOTO_MONEY_BY_MONTH='" + MOTO_MONEY_BY_MONTH + '\'' ;
    }
}
