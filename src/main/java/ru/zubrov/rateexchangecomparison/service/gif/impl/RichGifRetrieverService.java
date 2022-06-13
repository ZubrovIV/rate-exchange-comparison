package ru.zubrov.rateexchangecomparison.service.gif.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.integration.GifClient;
import ru.zubrov.rateexchangecomparison.integration.model.GifInfo;
import ru.zubrov.rateexchangecomparison.service.gif.GifRetrieverService;

@Slf4j
@RequiredArgsConstructor
@Service("MORE")
public class RichGifRetrieverService implements GifRetrieverService {
    private final GifClient gifClient;

    private final GifClient client;
    @Value("${app.integrations.gif-provider.key}")
    private String appID;
    @Value("${app.integrations.gif-provider.moreTag}")
    private String tag;
    @Value("${app.integrations.gif-provider.limit}")
    private int limit;

    @Override
    public GifInfo retrieveGif() {
        log.info("started retrieving a gif with tag {}", tag);
        return client.getGif(appID, tag, limit);
    }
}
