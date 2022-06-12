package ru.zubrov.rateexchangecomparison.integration.model;

import lombok.Data;

import java.util.Map;

@Data
public class RateInfo {
    private Map<String, String> rates;

}
