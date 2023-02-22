package com.demo.utils.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDTO {
    double car_money_per_day;
    double moto_money_per_day;
    double bike_money_per_day;
}
