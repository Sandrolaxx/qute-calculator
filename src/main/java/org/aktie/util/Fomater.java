package org.aktie.util;

import java.math.BigDecimal;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class Fomater {
    
    public static String formatSign(BigDecimal value) {
        return "R$ ".concat(value.toString().replace(".", ","));
    }

}
