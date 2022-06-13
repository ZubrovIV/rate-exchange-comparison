package ru.zubrov.rateexchangecomparison.api.response;

import ru.zubrov.rateexchangecomparison.integration.model.GifInfo;

public class ResponseMapper {
public  GifResponse map (GifInfo gifInfo){
    return new GifResponse(gifInfo.getData().getUrl());
}
}
