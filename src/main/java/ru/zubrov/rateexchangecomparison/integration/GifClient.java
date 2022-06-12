package ru.zubrov.rateexchangecomparison.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zubrov.rateexchangecomparison.integration.model.GifInfo;

@FeignClient(value = "gif", url = "${app.integrations.gif-provider.baseUrl}")
public interface GifClient {
    @GetMapping
    GifInfo getGif(@RequestParam String api_key,
                   @RequestParam String tag,
                   @RequestParam int limit
    );
}
