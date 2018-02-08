package com.ef.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 *
 * @author Rogerio J. Gentil
 */
public class ArgumentUtilTest {

    @Test
    public void mustValidadeAccessFileArgument() throws IOException {
        final String accessFile = FileUtils.getTempDirectoryPath() + File.separator + "access-for-test.log";
        File file = new File(accessFile);

        FileUtils.writeStringToFile(file, "", Charset.defaultCharset());

        ArgumentUtil.parseAccessFile(accessFile);

        FileUtils.forceDelete(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowParseExceptionWhenAccessFileArgumentIsNotValid() throws java.text.ParseException {
        final String accessFile = FileUtils.getTempDirectoryPath() + File.separator + "access-for-test.log";
        ArgumentUtil.parseAccessFile(accessFile);
    }

    @Test
    public void mustValidateStartDateArgument() throws java.text.ParseException {
        final String dateTxt = "2017-01-01.13:00:00";
        ArgumentUtil.parseStartDate(dateTxt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowParseExceptionWhenStartDateArgumentIsNotValid() throws java.text.ParseException {
        final String dateTxt = "2017-01-01=13:00:00";
        ArgumentUtil.parseStartDate(dateTxt);
    }

    @Test
    public void mustValidateDurationArgument() {
        final String durationArg = "hourly";
        ArgumentUtil.parseDuration(durationArg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowNumberFormatExceptionWhenThresholdArgumentIsNotValid() {
        final String thresholdArg = "abc";
        ArgumentUtil.parseThreshold(thresholdArg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenDurationArgumentIsNotValid() {
        final String durationArg = "test";
        ArgumentUtil.parseDuration(durationArg);
    }

    @Test
    public void mustValidateThresholdArgument() {
        final String thresholdArg = "100";
        ArgumentUtil.parseThreshold(thresholdArg);
    }

}
