package ru.zubrov.rateexchangecomparison.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zubrov.rateexchangecomparison.domain.service.CurrencyMapper;
import ru.zubrov.rateexchangecomparison.integration.model.RateInfo;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zubrov.rateexchangecomparison.service.currency.CurrencyRateComparisonServiceTest.BASE_CURRENCY;


@SpringBootTest
public class CurrencyRateMapperTest {

    @Autowired
    private CurrencyMapper mapper;

    @Test
    void test_map() {
        var rate = mapper.map(getData(), BASE_CURRENCY);
        assertThat(rate).isNotNull();
        assertThat(rate.rate()).isEqualTo(new BigDecimal("100"));
    }

    @Test
    void test_map_noRate() {
        var rate = mapper.map(getData(), "EUR");
        assertThat(rate).isNotNull();
        assertThat(rate.rate()).isEqualTo(new BigDecimal("0"));
    }

    private RateInfo getData() {
        var rates = Map.of("USD", "100");
        return RateInfo.builder()
                .rates(rates)
                .build();
    }
}
