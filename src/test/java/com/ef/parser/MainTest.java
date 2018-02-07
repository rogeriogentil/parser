package com.ef.parser;

import org.apache.commons.cli.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rogerio
 */
public class MainTest {
    
    @Test
    public void mustReadOptionsTogetherOfTheArguments() throws ParseException, java.text.ParseException {
        String[] args = {"--startDate=2017-01-01.13:00:00", "--duration=hourly", "--threshold=100"};
        Main.main(args);
        // TODO: use Mockito to verify call?
    }
    
    @Test
    public void mustReadOptionsSeparatedOfTheArguments() throws ParseException, java.text.ParseException {
        String[] args = {"--startDate", "2017-01-01.13:00:00", "--duration", "hourly", "--threshold", "100"};
        Main.main(args);
        // TODO: use Mockito to verify call?
    }
    
    @Test
    public void mustPrintHelpWhenNoOptions() throws ParseException, java.text.ParseException {
        String[] args = {};
        Main.main(args);
        // TODO: use Mockito to verify call?
    }
 
    @Test
    public void mustValidateStartDateArgument() throws java.text.ParseException {
        final String dateTxt = "2017-01-01.13:00:00";
        Main.checkStartDateArg(dateTxt);
    }
    
    @Test(expected = java.text.ParseException.class)
    public void mustThrowParseExceptionWhenStartDateArgumentIsNotValid() throws java.text.ParseException {
        final String dateTxt = "2017-01-01 13:00:00";
        Main.checkStartDateArg(dateTxt);
    }
    
    @Test
    public void mustValidateDurationArgument() {
        final String durationArg = "hourly";
        Main.checkDurationArg(durationArg);
    }
    
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenDurationArgumentIsNotValid() {
        final String durationArg = "test";
        Main.checkDurationArg(durationArg);
    }
    
    @Test
    public void mustValidateThresholdArgument() {
        final String thresholdArg = "100";
        Main.checkThresholdArg(thresholdArg);
    }
    
    @Test(expected = java.lang.NumberFormatException.class)
    public void mustThrowNumberFormatExceptionWhenThresholdArgumentIsNotValid() {
        final String thresholdArg = "abc";
        Main.checkThresholdArg(thresholdArg);
    }
}
