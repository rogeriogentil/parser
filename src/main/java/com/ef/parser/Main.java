package com.ef.parser;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
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
    public static void main(String[] args) throws ParseException {
        Option help = Option.builder("h")
                .longOpt("help")
                .required(false)
                .desc("HELP!")
                .build();

        Options options = new Options();
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("sb server", options);
        }
    }
}
