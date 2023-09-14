package org.aktie.dto;

import java.math.BigDecimal;
import java.util.List;

import org.aktie.model.Result;

public class TemplateDataDTO {

    private String externalText;

    private List<String> arithmeticOperations;

    private List<Result> results;

    private BigDecimal result;

    public String getExternalText() {
        return externalText;
    }

    public void setExternalText(String externalText) {
        this.externalText = externalText;
    }

    public List<String> getArithmeticOperations() {
        return arithmeticOperations;
    }

    public void setArithmeticOperations(List<String> arithmeticOperations) {
        this.arithmeticOperations = arithmeticOperations;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
