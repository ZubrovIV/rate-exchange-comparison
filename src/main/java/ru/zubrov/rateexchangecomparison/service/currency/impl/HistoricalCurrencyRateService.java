package ru.zubrov.rateexchangecomparison.service.currency.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.domain.CurrencyRate;
import ru.zubrov.rateexchangecomparison.domain.service.CurrencyMapper;
import ru.zubrov.rateexchangecomparison.integration.RateClient;
import ru.zubrov.rateexchangecomparison.service.currency.CurrencyRateRetrieverService;

import static ru.zubrov.rateexchangecomparison.util.Utils.dayMinusOne;

@Slf4j
@RequiredArgsConstructor
@Service("historical")
public class HistoricalCurrencyRateService implements CurrencyRateRetrieverService {
    private final RateClient rateClient;
    private final CurrencyMapper currencyMapper;
    @Value("${app.integrations.currencies-rate.id}")
    private String appID;
    @Value("${app.integrations.currencies-rate.baseCurrency}")
    private String baseCurrency;

    @Override
    public CurrencyRate retrieveRate(String currencyToCompare) {
        var date = dayMinusOne();
        var info = rateClient.getHistoricalCurrencyRateInfo(date,appID,baseCurrency,currencyToCompare);
        log.info("currency for yesterday{}",info);
        return currencyMapper.map(info,currencyToCompare);
    }
}
