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
    
    private final String option;
    private final String description;

    private ProgramOption(String option, String description) {
        this.option = option;
        this.description = description;
    }

    public String getOption() {
        return option;
    }

    public String getDescription() {
        return description;
    }
}
