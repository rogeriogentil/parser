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

/**
 *
 * @author Rogerio J. Gentil
 */
public class ProcessorTest {

    private Processor processor;

    private String data = 
            "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
            + "2017-01-01 00:00:21.164|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
            + "2017-01-01 00:00:23.003|192.168.169.194|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393\"\n"
            + "2017-01-01 00:00:40.554|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"\n"
            + "2017-01-01 00:00:54.583|192.168.169.194|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393\"\n"
            + "2017-01-01 00:00:54.639|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
    
    @Before
    public void beforeEachTest() {
        processor = new DefaultProcessor();
    }

    @Test
    public void mustProcessLogFile() throws IOException {
        // TODO extract to @Before
        final String fileName = "access-for-test.log";
        File file = new File(FileUtils.getTempDirectoryPath() + File.separator + fileName);
        FileUtils.writeStringToFile(file, data, Charset.defaultCharset());

        final String startDate = "2017-01-01.13:00:00";
        final String duration = "hourly";
        final String threshold = "3";

        System.out.println(file.getAbsoluteFile());
        Map<String, Integer> mapped = processor.findIps(file, startDate, duration, threshold);
        
        final ImmutableMap<String, Integer> ipsExpected = ImmutableMap.<String, Integer>builder()
                .put("192.168.234.82", 4)
                .build();
        
        assertThat(ipsExpected, is(equalTo(mapped)));
        
        // TODO extract to @After
        FileUtils.forceDelete(file);
    }
}
