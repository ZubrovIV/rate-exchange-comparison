package ru.zubrov.rateexchangecomparison.service.currency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.domain.ComparisonResult;
import ru.zubrov.rateexchangecomparison.domain.CurrencyMark;
import ru.zubrov.rateexchangecomparison.domain.CurrencyRate;

@Slf4j
@Service
public record CurrencyRateComparisonService(CurrencyRateRetrieverService current,
                                            CurrencyRateRetrieverService historical) {
    public ComparisonResult compareRates(String currencyToCompare) {
        var historyRate = historical.retrieveRate(currencyToCompare);
        var currentRate = current.retrieveRate(currencyToCompare);
        return compare(currentRate,historyRate);

    }

    public ComparisonResult compare(CurrencyRate currencyRate, CurrencyRate historyRate) {
        boolean more = historyRate.rate().compareTo(currencyRate.rate()) > 0;
        log.info("historical currency rate more than current rate is {}", more);
        return more ? new ComparisonResult(CurrencyMark.MORE) : new ComparisonResult(CurrencyMark.LESS);
    }
}
