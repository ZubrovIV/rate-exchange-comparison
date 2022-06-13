package ru.zubrov.rateexchangecomparison.api.response;

import org.springframework.stereotype.Service;
import ru.zubrov.rateexchangecomparison.integration.model.GifInfo;

@Service
public class ResponseMapper {
    public GifResponse map(GifInfo gifInfo) {
        return new GifResponse(gifInfo.getData().getUrl());
    }
}
