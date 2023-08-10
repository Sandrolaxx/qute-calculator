package org.aktie.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.aktie.model.EnumUserOption;
import org.aktie.services.CalculatorSerivce;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/calculator")
@Consumes(MediaType.TEXT_HTML)
@Produces(MediaType.TEXT_HTML)
public class Calculator {

    @Inject
    Template page;

    @Inject
    Template calculator;

    @Inject
    CalculatorSerivce serivce;

    @GET
    public TemplateInstance get(@QueryParam("name") String name) {
        List<String> arithmeticOperations = List.of(EnumUserOption.values()).stream()
                .map(e -> e.getValue()).collect(Collectors.toList());

        return page.data("name", name, "arithmeticOperations", arithmeticOperations);
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public TemplateInstance calculate(@FormParam("firstNumber") String firstNumber,
            @FormParam("secondNumber") String secondNumber, @FormParam("operation") String operation) {

        BigDecimal valueOne = BigDecimal.valueOf(Double.parseDouble(firstNumber));
        BigDecimal valueTwo = BigDecimal.valueOf(Double.parseDouble(secondNumber));

        if (valueTwo.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Erro! Segundo número inválido");
        }

        BigDecimal result = serivce.handleCalculate(EnumUserOption.parseByValue(operation), valueOne, valueTwo);

        return calculator.data("result", result);
    }

}
