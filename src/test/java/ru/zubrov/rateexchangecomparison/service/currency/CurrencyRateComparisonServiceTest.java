package ru.zubrov.rateexchangecomparison.service.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.zubrov.rateexchangecomparison.domain.CurrencyRate;
import ru.zubrov.rateexchangecomparison.domain.service.CurrencyRateComparisonService;
import ru.zubrov.rateexchangecomparison.service.currency.impl.CurrentCurrencyRateService;
import ru.zubrov.rateexchangecomparison.service.currency.impl.HistoricalCurrencyRateService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static ru.zubrov.rateexchangecomparison.domain.CurrencyMark.LESS;
import static ru.zubrov.rateexchangecomparison.domain.CurrencyMark.MORE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyRateComparisonServiceTest {
    public final static String BASE_CURRENCY = "USD";
    @MockBean
    private CurrentCurrencyRateService current;
    @MockBean
    private HistoricalCurrencyRateService historical;
    @Autowired
    private CurrencyRateComparisonService service;

    @Test
    void test_compareRates_historicalRateLess() {
        //given
        var curr = new CurrencyRate(new BigDecimal("100"));
        var hist = new CurrencyRate(new BigDecimal("10"));
        //when
        when(this.current.retrieveRate(anyString()))
                .thenReturn(curr);
        when(historical.retrieveRate(anyString()))
                .thenReturn(hist);
        //then
        var comparisonResult = service.compareRates(BASE_CURRENCY);
        assertThat(comparisonResult).isNotNull();
        assertThat(comparisonResult.currencyMark()).isEqualTo(LESS);
    }

    @Test
    void test_compareRates_currentRateLess() {
        //given
        var curr = new CurrencyRate(new BigDecimal("10"));
        var hist = new CurrencyRate(new BigDecimal("100"));
        //when
        when(this.current.retrieveRate(anyString()))
                .thenReturn(curr);
        when(historical.retrieveRate(anyString()))
                .thenReturn(hist);
        //then
        var comparisonResult = service.compareRates(BASE_CURRENCY);
        assertThat(comparisonResult).isNotNull();
        assertThat(comparisonResult.currencyMark()).isEqualTo(MORE);
    }
}
