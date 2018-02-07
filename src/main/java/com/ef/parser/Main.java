package com.ef.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 *
 * @author Rogerio J. Gentil
 */
public class Main {

    /**
     * Expected command-line:
     *
     * <p>
     * java -cp "parser.jar" com.ef.Parser --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
     * </p>
     *
     * @param args
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(String[] args) throws ParseException, java.text.ParseException {
        Program program = new Program();
        Options options = program.getOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption(ProgramOption.HELP.getOption()) || commandLine.getOptions().length == 0) {
            program.help();
            return;
        }

        if (commandLine.hasOption(ProgramOption.START_DATE.getOption())) {
            String startDateTxt = commandLine.getOptionValue(ProgramOption.START_DATE.getOption());
            checkStartDateArg(startDateTxt);
        } else {
            throw new IllegalArgumentException();
        }

        if (commandLine.hasOption(ProgramOption.DURATION.getOption())) {
            String durationArg = commandLine.getOptionValue(ProgramOption.DURATION.getOption());
            checkDurationArg(durationArg);
        } else {
            throw new IllegalArgumentException();
        }
        
        if (commandLine.hasOption(ProgramOption.THRESHOLD.getOption())) {
            String thresholdArg = commandLine.getOptionValue(ProgramOption.THRESHOLD.getOption());
            checkThresholdArg(thresholdArg);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void checkStartDateArg(String startDateTxt) throws java.text.ParseException {
        final String dateFormat = "yyyy-MM-dd.HH:mm:ss";

        DateFormat format = new SimpleDateFormat(dateFormat);
        format.parse(startDateTxt);
    }

    public static void checkDurationArg(String durationArg) {
        DurationArg.valueOf(durationArg.toUpperCase());
    }
    
    public static void checkThresholdArg(String thresholdArg) {
        Integer.parseInt(thresholdArg);
    }
}
