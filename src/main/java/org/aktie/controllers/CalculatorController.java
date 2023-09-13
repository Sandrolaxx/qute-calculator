package org.aktie.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.aktie.dto.ExternalTextDTO;
import org.aktie.model.EnumUserOption;
import org.aktie.services.CalculatorSerivce;
import org.aktie.services.RestHttpClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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
public class CalculatorController {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance page(String name, List<String> arithmeticOperations, String externalText);

        public static native TemplateInstance calculator(BigDecimal result, String name);
    }

    @Inject
    CalculatorSerivce service;

    @Inject
    @RestClient
    RestHttpClient restClient;

    public String exteralText;

    @GET
    public TemplateInstance get(@QueryParam("name") String name) {
        ExternalTextDTO externalTextDTO = restClient.fetchExternalData();
        exteralText = externalTextDTO.getText();

        List<String> arithmeticOperations = List.of(EnumUserOption.values()).stream()
                .map(e -> e.getValue()).collect(Collectors.toList());

        return Templates.page(name, arithmeticOperations, exteralText);
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

        BigDecimal result = service.handleCalculate(EnumUserOption.parseByValue(operation), valueOne, valueTwo);

        return Templates.calculator(result, "Sandrolax");
    }

}
