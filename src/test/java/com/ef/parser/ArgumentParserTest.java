package com.ef.parser;

import java.text.ParseException;
import org.junit.Test;

/**
 *
 * @author Rogerio J. Gentil
 */
public class ArgumentParserTest {

    @Test
    public void mustValidateStartDateArgument() throws java.text.ParseException {
        final String dateTxt = "2017-01-01.13:00:00";
        ArgumentParser.checkStartDateArg(dateTxt);
    }

    @Test(expected = ParseException.class)
    public void mustThrowParseExceptionWhenStartDateArgumentIsNotValid() throws java.text.ParseException {
        final String dateTxt = "2017-01-01 13:00:00";
        ArgumentParser.checkStartDateArg(dateTxt);
    }

    @Test
    public void mustValidateDurationArgument() {
        final String durationArg = "hourly";
        ArgumentParser.checkDurationArg(durationArg);
    }

    @Test(expected = NumberFormatException.class)
    public void mustThrowNumberFormatExceptionWhenThresholdArgumentIsNotValid() {
        final String thresholdArg = "abc";
        ArgumentParser.checkThresholdArg(thresholdArg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenDurationArgumentIsNotValid() {
        final String durationArg = "test";
        ArgumentParser.checkDurationArg(durationArg);
    }

    @Test
    public void mustValidateThresholdArgument() {
        final String thresholdArg = "100";
        ArgumentParser.checkThresholdArg(thresholdArg);
    }

}
