package ru.zubrov.rateexchangecomparison.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comparison/rate")
public class CurrencyCompareController {
    @Operation(summary = "Возвращает гифку в зависимости от курса валют", tags = {"currency-rate-comparison"})
    @ApiResponse(responseCode = "200", description = "Если отношщение курсов валют меньшеч чем вчера" +
            "использовать один ресурс иначе другой", content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/v1/currencies/{currency}")
    public String getCompereOfCurrency(@PathVariable String currency) {
        log.info("started processing a request for currency {}", currency);
        return "Hello";
    }

}
