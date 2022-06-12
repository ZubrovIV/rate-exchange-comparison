package ru.zubrov.rateexchangecomparison.integration.model;

import lombok.Data;

@Data
public class GifInfo {
    private Data data;
@lombok.Data
    public static class Data {
        private String url;
    }
}
