package org.aktie;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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

    @GET
    public TemplateInstance get(@QueryParam("name") String name) {
        List<String> arithmeticOperations = List.of("Soma", "Subtração", "Multiplicação", "Divisão");

        return page.data("name", name, "arithmeticOperations", arithmeticOperations);
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public TemplateInstance calculate(@FormParam("number1") String firstNumber,
            @FormParam("number2") String secondNumber, @FormParam("operation") String operation) {

        BigDecimal result = BigDecimal.ZERO;

        if (operation.equals("Soma")) {
            result = BigDecimal.valueOf(Double.parseDouble(firstNumber))
                    .multiply(BigDecimal.valueOf(Double.parseDouble(secondNumber)));
        }

        return calculator.data("result", result.setScale(2, RoundingMode.HALF_UP));
    }

}
