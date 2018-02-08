package com.ef.parser;

import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.After;

/**
 *
 * @author Rogerio J. Gentil
 */
public class ProcessorTest {

    private File file;
    private Processor processor;

    @Before
    public void beforeEachTest() {
        processor = new DefaultProcessor();

        final String fileName = "access-for-test.log";
        file = new File(FileUtils.getTempDirectoryPath() + File.separator + fileName);
    }

    @After
    public void afterEachTest() throws IOException {
        FileUtils.forceDelete(file);
    }

    @Test
    public void mustFindIpsThatMadeThreeOrMoreThanThreeRequests() throws IOException {
        // Arguments sended
        final String startDate = "2017-01-01.00:00:00";
        final String duration = "hourly";
        final String threshold = "3";
        
        // Create file to test
        final String data
                = "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:21.164|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:23.554|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:40.003|192.168.169.194|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393\"\n"
                + "2017-01-01 00:00:54.583|192.168.169.194|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393\"\n";

        FileUtils.writeStringToFile(file, data, Charset.defaultCharset());

        // Call method
        Map<String, Integer> mapped = processor.findIps(file, startDate, duration, threshold);

        // Assertion
        final ImmutableMap<String, Integer> ipsExpected = ImmutableMap.<String, Integer>builder()
                .put("192.168.234.82", 3)
                .build();

        assertThat(mapped, is(equalTo(ipsExpected)));
    }

    @Test
    public void mustFindIpsThatMadeThreeMoreThanThreeRequestsOnOneHour() throws IOException {
        // Arguments sended
        final String startDate = "2017-01-01.00:00:00";
        final String duration = "hourly";
        final String threshold = "3";
        
        // Create file to test
        final String data
                = "2016-12-31 23:59:00.375|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n" // this line should not be computed 'cause it's been over one hour
                + "2017-01-01 00:00:01.123|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:21.841|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:34.678|192.168.162.248|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:45.001|192.168.162.248|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
                + "2017-01-01 00:00:54.583|192.168.169.194|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393\"\n"
                + "2017-01-01 00:01:02.113|192.168.247.138|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0\"\n"
                + "2017-01-01 00:01:04.033|192.168.54.139|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36\"\n"
                + "2017-01-01 00:01:06.110|192.168.159.178|\"GET / HTTP/1.1\"|200|\"Dalvik/2.1.0 (Linux; U; Android 6.0; vivo 1601 Build/MRA58K)\"\n";
                

        FileUtils.writeStringToFile(file, data, Charset.defaultCharset());
        
        // Call method
        Map<String, Integer> mapped = processor.findIps(file, startDate, duration, threshold);

        // Assertion
        final ImmutableMap<String, Integer> ipsExpected = ImmutableMap.<String, Integer>builder()
                .put("192.168.234.82", 3)                
                .build();

        assertThat(mapped, is(equalTo(ipsExpected)));
    }
}
