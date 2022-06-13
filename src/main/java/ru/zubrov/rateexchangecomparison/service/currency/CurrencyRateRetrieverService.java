package ru.zubrov.rateexchangecomparison.service.currency;

import ru.zubrov.rateexchangecomparison.domain.CurrencyRate;

public interface CurrencyRateRetrieverService {
    CurrencyRate retrieveRate(String currencyToCompare);


}
