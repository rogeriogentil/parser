package com.ef.parser;

/**
 *
 * @author Rogerio J. Gentil
 */
public enum DurationArg {
    
    HOURLY("hourly"),
    DAILY("daily");
    
    final private String value;

    private DurationArg(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }    
}
