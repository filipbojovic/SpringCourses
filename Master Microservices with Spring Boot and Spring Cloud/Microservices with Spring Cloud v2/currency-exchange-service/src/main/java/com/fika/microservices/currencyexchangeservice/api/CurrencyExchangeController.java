package com.fika.microservices.currencyexchangeservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    private final String PORT = "local.server.port";

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {
        var currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

        if (Objects.isNull(currencyExchange)) {
            throw new RuntimeException("Unable to Find data for " +from +" and " +to);
        }
        currencyExchange.setEnvironment(environment.getProperty(PORT));

        return currencyExchange;
    }
}
