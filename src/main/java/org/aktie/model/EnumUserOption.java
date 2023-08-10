package org.aktie.model;

public enum EnumUserOption {

    SUM("1", "Soma"),
    SUBTRACTION("2", "Subtração"),
    MULTIPLICATION("3", "Multiplicação"),
    DIVISION("4", "Divisão");

    private final String key;

    private final String value;

    private EnumUserOption(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static EnumUserOption parseByKey(String key) {

        if (key != null && !key.trim().isEmpty()) {
            for (var value : EnumUserOption.values()) {
                if (value.getKey().equalsIgnoreCase(key)) {
                    return value;
                }
            }
        }

        return null;
    }

    public static EnumUserOption parseByValue(String value) {

        if (value != null && !value.trim().isEmpty()) {
            for (var val : EnumUserOption.values()) {
                if (val.getValue().equalsIgnoreCase(value)) {
                    return val;
                }
            }
        }

        return null;
    }

}
