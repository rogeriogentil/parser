package com.ef.parser;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 *
 * @author Rogerio J. Gentil
 */
public class Program {

    private Options options;

    public Program() {
        options = init();
    }

    protected final Options init() {
        Option help = Option.builder("h")
                .longOpt(ProgramOption.HELP.getOption())
                .required(false)
                .desc(ProgramOption.HELP.getDescription())
                .build();

        Option startDate = Option.builder()
                .longOpt(ProgramOption.START_DATE.getOption())
                .hasArg()
                .required(false)
                .desc(ProgramOption.START_DATE.getDescription())
                .build();

        Option duration = Option.builder()
                .longOpt(ProgramOption.DURATION.getOption())
                .hasArg()
                .required(false)
                .desc(ProgramOption.DURATION.getDescription())
                .build();

        Option threshold = Option.builder()
                .longOpt(ProgramOption.THRESHOLD.getOption())
                .hasArg()
                .required(false)
                .desc(ProgramOption.THRESHOLD.getDescription())
                .build();

        options = new Options();
        options.addOption(help);
        options.addOption(startDate);
        options.addOption(duration);
        options.addOption(threshold);

        return options;
    }

    public Options getOptions() {
        return this.options;
    }

    public void help() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("sb server", options);
    }
}
