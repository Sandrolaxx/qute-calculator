package org.aktie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalTextDTO {

    @JsonProperty("texto")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
