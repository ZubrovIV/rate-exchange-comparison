package ru.zubrov.rateexchangecomparison.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class Utils {
    private static final String JSON = ".json";
    private static final long DAYS_TO_SUBTRACT = 1L;

    public static String dayMinusOne() {
        var dayBefore = LocalDate.now().minusDays(DAYS_TO_SUBTRACT);
        return dayBefore + JSON;
    }

}
