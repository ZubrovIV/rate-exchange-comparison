package ru.zubrov.rateexchangecomparison.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zubrov.rateexchangecomparison.integration.model.RateInfo;

@FeignClient(value = "currency", url = "${app.integrations.currencies-rate.baseUrl}")
public interface RateClient {
    @GetMapping("/latest.json")
    RateInfo getCurrencyRateInfo(@RequestParam String app_id,
                                 @RequestParam String base,
                                 @RequestParam String symbols);

    @GetMapping("/historical/{date}")
    RateInfo getHistoricalCurrencyRateInfo(@PathVariable String date,
                                           @RequestParam String app_id,
                                           @RequestParam String base,
                                           @RequestParam String symbols);
}
