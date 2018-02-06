package com.ef.parser;

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
     * java -cp "parser.jar" com.ef.Parser --startDate=2017-01-01.13:00:00
     * --duration=hourly --threshold=100
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

        if (commandLine.hasOption(ProgramOption.HELP.getName()) || commandLine.getOptions().length == 0) {
            program.help();
            return;
        }

        if (commandLine.hasOption(ProgramOption.START_DATE.getName())) {
            System.out.println(commandLine.getOptionValue(ProgramOption.START_DATE.getName()));
        }
    }
}
