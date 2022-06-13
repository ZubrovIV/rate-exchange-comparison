package ru.zubrov.rateexchangecomparison.service.currency.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.domain.CurrencyRate;
import ru.zubrov.rateexchangecomparison.domain.service.CurrencyMapper;
import ru.zubrov.rateexchangecomparison.integration.RateClient;
import ru.zubrov.rateexchangecomparison.service.currency.CurrencyRateRetrieverService;

@Slf4j
@RequiredArgsConstructor
@Service("current")
public class CurrentCurrencyRateService implements CurrencyRateRetrieverService {
    private final RateClient rateClient;
    private final CurrencyMapper currencyMapper;

    @Value("${app.integrations.currencies-rate.id}")
    private String appId;
    @Value("${app.integrations.currencies-rate.baseCurrency}")
    private String baseCurrency;


    @Override
    public CurrencyRate retrieveRate(String currencyToCompare) {
        var info = rateClient.getCurrencyRateInfo(appId, baseCurrency, currencyToCompare);
        log.info("currencies for today {}", info);
        return currencyMapper.map(info, currencyToCompare);
    }
}
