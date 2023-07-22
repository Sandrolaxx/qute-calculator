package org.aktie;

import java.math.BigDecimal;

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

@Path("/some-page")
@Consumes(MediaType.TEXT_HTML)
@Produces(MediaType.TEXT_HTML)
public class SomePage {

    @Inject
    Template page;

    @Inject
    Template calculator;

    @GET
    public TemplateInstance get(@QueryParam("name") String name) {
        return page.data("name", name);
    }

    @POST
    @Path("/result")
    @Consumes("application/x-www-form-urlencoded")
    public TemplateInstance get2(@FormParam("number1") String firstNumber,
            @FormParam("number2") String secondNumber) {

        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(firstNumber))
                .multiply(BigDecimal.valueOf(Double.parseDouble(secondNumber)));

        return calculator.data("result", result);
    }

}
