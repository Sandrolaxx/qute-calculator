package org.aktie.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.aktie.model.EnumUserOption;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculatorSerivce {

    public BigDecimal handleCalculate(EnumUserOption option, BigDecimal firstValue, BigDecimal secondValue) {
        switch (option) {
            case SUM:
                return firstValue.add(secondValue).setScale(2, RoundingMode.HALF_UP);
            case SUBTRACTION:
                return firstValue.subtract(secondValue).setScale(2, RoundingMode.HALF_UP);
            case MULTIPLICATION:
                return firstValue.multiply(secondValue).setScale(2, RoundingMode.HALF_UP);
            case DIVISION:
                return firstValue.divide(secondValue, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            default:
                return BigDecimal.ZERO;
        }
    }

}
