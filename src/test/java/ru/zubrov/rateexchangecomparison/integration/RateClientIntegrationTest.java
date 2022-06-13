package ru.zubrov.rateexchangecomparison.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.zubrov.rateexchangecomparison.config.CurrencyMock;
import ru.zubrov.rateexchangecomparison.config.TestConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zubrov.rateexchangecomparison.service.currency.CurrencyRateComparisonServiceTest.BASE_CURRENCY;

@SpringBootTest
@EnableConfigurationProperties
@ContextConfiguration(classes = {TestConfig.class})
public class RateClientIntegrationTest {
    private final static String APP_ID = "app_id";
    private final static String EUR = "EUR";

    @Autowired
    private WireMockServer server;

    @Autowired
    private RateClient client;

    @BeforeEach
    void setUp() {
        CurrencyMock.setupMockCurrencyResponse(server);
    }

    @Test
    void test_client_responsePresent() {
        var currencyRateInfo = client.getCurrencyRateInfo(APP_ID, BASE_CURRENCY, EUR);
        assertThat(currencyRateInfo).isNotNull();
        assertThat(currencyRateInfo.getRates()).isNotNull();
        assertThat(currencyRateInfo.getRates()).isNotEmpty();
    }

    @Test
    void test_client_assertResponse() {
        var currencyRateInfo = client.getCurrencyRateInfo(APP_ID, BASE_CURRENCY, EUR);
        assertThat(currencyRateInfo.getRates().get(EUR)).isNotNull();
        assertThat(currencyRateInfo.getRates().get(EUR)).isEqualTo("0.941659");
    }
}
