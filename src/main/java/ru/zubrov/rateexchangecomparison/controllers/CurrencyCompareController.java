package ru.zubrov.rateexchangecomparison.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zubrov.rateexchangecomparison.api.response.GifResponse;
import ru.zubrov.rateexchangecomparison.api.response.ResponseMapper;
import ru.zubrov.rateexchangecomparison.api.validator.ValidCurrency;
import ru.zubrov.rateexchangecomparison.domain.service.ChoiceGif;

import javax.validation.ConstraintViolationException;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comparison/rate")
public class CurrencyCompareController {
    private final ChoiceGif service;
    private final ResponseMapper mapper;

    @Operation(summary = "Возвращает рандомную гифку в  зависимомти от курса валют", tags = {"currency-rate-comparison"})
    @ApiResponse(responseCode = "200", description = "Если отношение курсов валют меньше чем вчера " +
            "использовать один ресурс иначе другой", content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/v1/currencies/{currency}")
    public ResponseEntity<GifResponse> getCompereOfCurrency(@PathVariable @ValidCurrency String currency) {
        log.info("started processing a request for currency {}", currency);
        var getInfo = service.choiceGif(currency);
        return ResponseEntity.ok(mapper.map(getInfo));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException() {
        log.info("not ISO currency code was provided");
        return "wrong ISO currency code";
    }
}
