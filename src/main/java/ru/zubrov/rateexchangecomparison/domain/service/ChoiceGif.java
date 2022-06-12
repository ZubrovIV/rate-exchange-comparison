package ru.zubrov.rateexchangecomparison.domain.service;

import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.domain.CurrencyMark;
import ru.zubrov.rateexchangecomparison.integration.model.GifInfo;
import ru.zubrov.rateexchangecomparison.service.gif.GifRetrieverService;

import java.util.Map;

@Service
public record ChoiceGif (Map<String, GifRetrieverService> service,
                         CurrencyRateComparisonService comparisonService) {
    public GifInfo choiceGif (String currencyToCompare){
        var comparisonResult = comparisonService.compareRates(currencyToCompare);
        return service.get(comparisonResult.currencyMark().name()).retrieveGif();
    }

}
