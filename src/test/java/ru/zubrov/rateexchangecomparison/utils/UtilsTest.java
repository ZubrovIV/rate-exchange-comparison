package ru.zubrov.rateexchangecomparison.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zubrov.rateexchangecomparison.util.Utils.dayMinusOne;

public class UtilsTest {
    @Test
    void test_dayMinusOneAsString() {
        assertThat(dayMinusOne())
                .isEqualTo(LocalDate.now().minusDays(1L) + ".json");
    }
}
