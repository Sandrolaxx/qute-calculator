package org.aktie.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.aktie.model.EnumUserOption;
import org.aktie.services.CalculatorSerivce;

import io.quarkus.qute.CheckedTemplate;
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

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance page(String name, List<String> arithmeticOperations);

        public static native TemplateInstance calculator(BigDecimal result, String name);
    }

    @Inject
    CalculatorSerivce serivce;

    @GET
    public TemplateInstance get(@QueryParam("name") String name) {
        List<String> arithmeticOperations = List.of(EnumUserOption.values()).stream()
                .map(e -> e.getValue()).collect(Collectors.toList());

        return Templates.page(name, arithmeticOperations);
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

        return Templates.calculator(result, "Sandrolax");
    }

}
