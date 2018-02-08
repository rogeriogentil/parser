package com.ef.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Rogerio J. Gentil
 */
public class Main {

    private static final String CHECK_OPTION_MSG = "The %s option is required.";
    
    /**
     * Expected command-line:
     *
     * <p>
     * java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file
     * --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
     * </p>
     *
     * @param args
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(String[] args) throws ParseException {
        Program program = new Program();
        Options options = program.getOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption(ProgramOption.HELP.getOption()) || commandLine.getOptions().length == 0) {
            program.help();
            return;
        }

        try {
            String accessLogArg = checkAccessFileOption(commandLine);
            String startDateArg = checkStartDateOption(commandLine);
            String durationArg = checkDurationOption(commandLine);
            String thresholdArg = checkThresholdOption(commandLine);

            File file = ArgumentUtil.parseAccessFile(accessLogArg);
            LocalDateTime dateTime = ArgumentUtil.parseStartDate(startDateArg);
            DurationArg duration = ArgumentUtil.parseDuration(durationArg);
            int threshold = ArgumentUtil.parseThreshold(thresholdArg);

            // find any IPs that made more than 100 requests 
            // starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00 (one hour) 
            try {
                Processor processor = new DefaultProcessor();
                Map<String, Integer> ips = processor.findIps(file, dateTime, duration, threshold);
                ips.entrySet().forEach(ip -> System.out.println("IP: " + ip.getKey() + "\tCount: " + ip.getValue()));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            // and print them to console 
//        print();
            // AND also load them to another MySQL table with comments on why it's blocked.
//        Map<String, String> ips = new HashMap<>();
//        load(ips);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public static String checkAccessFileOption(CommandLine commandLine) {
        if (commandLine.hasOption(ProgramOption.ACCESS_LOG.getOption())) {
            return commandLine.getOptionValue(ProgramOption.ACCESS_LOG.getOption());
        } else {
            throw new IllegalArgumentException(String.format(CHECK_OPTION_MSG, ProgramOption.ACCESS_LOG.getOption()));
//        program.help();
        }
    }

    public static String checkStartDateOption(CommandLine commandLine) {
        if (commandLine.hasOption(ProgramOption.START_DATE.getOption())) {
            return commandLine.getOptionValue(ProgramOption.START_DATE.getOption());
        } else {
            throw new IllegalArgumentException(String.format(CHECK_OPTION_MSG, ProgramOption.START_DATE.getOption()));
//        program.help();
        }
    }

    public static String checkDurationOption(CommandLine commandLine) {
        if (commandLine.hasOption(ProgramOption.DURATION.getOption())) {
            return commandLine.getOptionValue(ProgramOption.DURATION.getOption());
        } else {
            throw new IllegalArgumentException(String.format(CHECK_OPTION_MSG, ProgramOption.DURATION.getOption()));
//        program.help();
        }
    }

    public static String checkThresholdOption(CommandLine commandLine) {
        if (commandLine.hasOption(ProgramOption.THRESHOLD.getOption())) {
            return commandLine.getOptionValue(ProgramOption.THRESHOLD.getOption());
        } else {
            throw new IllegalArgumentException(String.format(CHECK_OPTION_MSG, ProgramOption.THRESHOLD.getOption()));
//        program.help();
        }
    }
}
