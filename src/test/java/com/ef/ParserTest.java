package com.ef;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author rogerio
 */
public class ParserTest {

    private static final String FILE_NAME = "access-for-test.log";
    private static final String ABSOLUTE_FILE_NAME = FileUtils.getTempDirectoryPath() + File.separator + FILE_NAME;
    private static File file;
    private Parser parser;
    private Options options;
    private CommandLineParser commandLineParser;
    private CommandLine commandLine;

    @BeforeClass
    public static void beforeAllTests() throws IOException {
        file = new File(ABSOLUTE_FILE_NAME);
        FileUtils.writeStringToFile(file, "", Charset.defaultCharset());
    }

    @AfterClass
    public static void afterAllTests() throws IOException {
        FileUtils.forceDelete(file);
    }

    @Before
    public void beforeEachTest() {
        parser = new Parser();
        options = parser.getOptions();
        commandLineParser = new DefaultParser();
    }

    @Ignore
    @Test
    public void mustReadOptionsTogetherOfTheArguments() throws ParseException, java.text.ParseException {
        String[] args = {"--accesslog=" + ABSOLUTE_FILE_NAME, "--startDate=2017-01-01.13:00:00", "--duration=hourly", "--threshold=100"};
        Parser.main(args);
        // TODO: use Mockito to verify call?
    }

    @Ignore
    @Test
    public void mustReadOptionsSeparatedOfTheArguments() throws ParseException, java.text.ParseException {
        String[] args = {"--accesslog", ABSOLUTE_FILE_NAME, "--startDate", "2017-01-01.13:00:00", "--duration", "hourly", "--threshold", "100"};
        Parser.main(args);
        // TODO: use Mockito to verify call?
    }

    @Test
    public void mustPrintHelpWhenNoOptions() throws ParseException, java.text.ParseException {
        String[] args = {};
        Parser.main(args);
        // TODO: use Mockito to verify call?
    }

    @Test
    public void mustValidateAccessLogOption() throws ParseException {
        String[] args = {"--accesslog=" + ABSOLUTE_FILE_NAME};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkAccessFileOption(commandLine);

        assertThat(arg, is(equalTo(ABSOLUTE_FILE_NAME)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenAccessLogOptionIsNotPresent() throws ParseException {
        String[] args = {};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkAccessFileOption(commandLine);
    }

    @Test
    public void mustValidateStartDateOption() throws ParseException {
        String[] args = {"--startDate=2017-01-01.13:00:00"};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkStartDateOption(commandLine);

        assertThat(arg, is(equalTo("2017-01-01.13:00:00")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenStartDateOptionIsNotPresent() throws ParseException {
        String[] args = {};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkStartDateOption(commandLine);
    }

    @Test
    public void mustValidateDurationOption() throws ParseException {
        String[] args = {"--duration=hourly"};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkDurationOption(commandLine);

        assertThat(arg, is(equalTo("hourly")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenDurationOptionIsNotPresent() throws ParseException {
        String[] args = {};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkDurationOption(commandLine);
    }
    
        @Test
    public void mustValidateThresholdOption() throws ParseException {
        String[] args = {"--threshold=100"};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkThresholdOption(commandLine);

        assertThat(arg, is(equalTo("100")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenThresholdOptionIsNotPresent() throws ParseException {
        String[] args = {};

        commandLine = commandLineParser.parse(options, args);
        String arg = Parser.checkThresholdOption(commandLine);
    }
}
