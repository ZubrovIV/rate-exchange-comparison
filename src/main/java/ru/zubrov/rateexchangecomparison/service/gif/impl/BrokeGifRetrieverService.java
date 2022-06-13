package ru.zubrov.rateexchangecomparison.service.gif.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.integration.GifClient;
import ru.zubrov.rateexchangecomparison.integration.model.GifInfo;
import ru.zubrov.rateexchangecomparison.service.gif.GifRetrieverService;

@Slf4j
@Service("LESS")
@RequiredArgsConstructor
public class BrokeGifRetrieverService implements GifRetrieverService {

    private final GifClient client;

    @Value("${app.integrations.gif-provider.key}")
    private String appId;
    @Value("${app.integrations.gif-provider.lessTag}")
    private String tag;
    @Value("${app.integrations.gif-provider.limit}")
    private int limit;

    @Override
    public GifInfo retrieveGif() {
        log.info("started retrieving a gif with tag {}", tag);
        return client.getGif(appId, tag, limit);
    }
}
