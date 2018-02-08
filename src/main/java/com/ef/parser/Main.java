package com.ef.parser;

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
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws org.apache.commons.cli.ParseException, java.text.ParseException  {
        Program program = new Program();
        Options options = program.getOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption(ProgramOption.HELP.getOption()) || commandLine.getOptions().length == 0) {
            program.help();
            return;
        }
        
        String accessLogArg;
        if (commandLine.hasOption(ProgramOption.ACCESS_LOG.getOption())) {
            accessLogArg = commandLine.getOptionValue(ProgramOption.ACCESS_LOG.getOption());
        } else {
            System.out.println("The " + ProgramOption.ACCESS_LOG.getOption() + " is required.");
            program.help();
        }
        
        String startDateArg;
        if (commandLine.hasOption(ProgramOption.START_DATE.getOption())) {
            startDateArg = commandLine.getOptionValue(ProgramOption.START_DATE.getOption());
            ArgumentParser.checkStartDateArg(startDateArg);
        } else {
            System.out.println("The " + ProgramOption.START_DATE.getOption() + " is required.");
            program.help();
        }

        String durationArg;
        if (commandLine.hasOption(ProgramOption.DURATION.getOption())) {
            durationArg = commandLine.getOptionValue(ProgramOption.DURATION.getOption());
            ArgumentParser.checkDurationArg(durationArg);
        } else {
            System.out.println("The " + ProgramOption.DURATION.getOption() + " is required.");
            program.help();
        }
        
        String thresholdArg;
        if (commandLine.hasOption(ProgramOption.THRESHOLD.getOption())) {
            thresholdArg = commandLine.getOptionValue(ProgramOption.THRESHOLD.getOption());
            ArgumentParser.checkThresholdArg(thresholdArg);
        } else {
            System.out.println("The " + ProgramOption.THRESHOLD.getOption() + " is required.");
            program.help();
        }
        
        // find any IPs that made more than 100 requests 
        // starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00 (one hour) 
//        Processor processor = new DefaultProcessor();
//        processor.findIps(new File(""), startDateArg, durationArg, thresholdArg);
        
        // and print them to console 
//        print();

        // AND also load them to another MySQL table with comments on why it's blocked.
//        Map<String, String> ips = new HashMap<>();
//        load(ips);
    }
}
