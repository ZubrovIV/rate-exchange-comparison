package ru.zubrov.rateexchangecomparison.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.domain.CurrencyRate;
import ru.zubrov.rateexchangecomparison.integration.RateClient;
import ru.zubrov.rateexchangecomparison.integration.model.RateInfo;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class CurrencyMapper {
    public CurrencyRate map(RateInfo rateInfo, String currency) {
        var rates = rateInfo.getRates();
        var valueStr = rates.get(currency);
        var cost = new BigDecimal(Objects.requireNonNullElse(valueStr,"0"));
        log.info("currency rate value {}",cost);
        return new CurrencyRate(cost);
    }

}
