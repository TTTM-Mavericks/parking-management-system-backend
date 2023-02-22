package com.demo.service.Impl;

import com.demo.entity.Money;
import com.demo.service.MoneyService;
import com.demo.utils.request.MoneyDTO;
import com.demo.utils.response.MoneyResponseDTO;
import org.springframework.stereotype.Controller;

import static com.demo.entity.Money.*;

@Controller
public class MoneyServiceImpl implements MoneyService {
    public Money money = new Money();

    @Override
    public MoneyResponseDTO saveMoneyFromAPI(MoneyDTO dto) {
        BIKE_MONEY_BY_DAY = (dto.getBike_money_per_day() != 0) ? dto.getBike_money_per_day()  : BIKE_MONEY_BY_DAY;
        CAR_MONEY_BY_DAY = (dto.getCar_money_per_day() != 0) ? dto.getCar_money_per_day()  : CAR_MONEY_BY_DAY;
        MOTO_MONEY_BY_DAY = (dto.getMoto_money_per_day() != 0) ? dto.getMoto_money_per_day()  : BIKE_MONEY_BY_DAY;
        calculateByMonth();
        return new MoneyResponseDTO(CAR_MONEY_BY_DAY, BIKE_MONEY_BY_DAY, MOTO_MONEY_BY_DAY, CAR_MONEY_BY_MONTH, BIKE_MONEY_BY_MONTH, MOTO_MONEY_BY_MONTH);
    }

    private void calculateByMonth()
    {
        CAR_MONEY_BY_MONTH = CAR_MONEY_BY_DAY * 28;
        BIKE_MONEY_BY_MONTH = BIKE_MONEY_BY_DAY * 28;
        MOTO_MONEY_BY_MONTH = MOTO_MONEY_BY_DAY * 28;
    }

    @Override
    public MoneyResponseDTO findALlTypeOfMoney() {
        return new MoneyResponseDTO(CAR_MONEY_BY_DAY, BIKE_MONEY_BY_DAY, MOTO_MONEY_BY_DAY, CAR_MONEY_BY_MONTH, BIKE_MONEY_BY_MONTH, MOTO_MONEY_BY_MONTH);
    }
}
