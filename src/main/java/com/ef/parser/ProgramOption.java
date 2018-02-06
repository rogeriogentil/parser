package com.ef.parser;

/**
 *
 * @author Rogerio J. Gentil
 */
public enum ProgramOption {
    
    START_DATE("startDate", "Start date"),
    DURATION("duration", "Duration"),
    THRESHOLD("threshold", "Threshold"),
    HELP("help", "show help");
    
    private final String name;
    private final String description;

    private ProgramOption(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
