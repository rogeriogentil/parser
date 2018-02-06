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
    public void mustReadOptionsTogetherOfTheArguments() throws ParseException {
        String[] args = {"--startDate=2017-01-01.13:00:00", "--duration=hourly", "--threshold=100"};
        Main.main(args);
        // TODO: use Mockito to verify call?
    }
    
    @Test
    public void mustReadOptionsSeparatedOfTheArguments() throws ParseException {
        String[] args = {"--startDate", "2017-01-01.13:00:00", "--duration", "hourly", "--threshold", "100"};
        Main.main(args);
        // TODO: use Mockito to verify call?
    }
    
    @Test
    public void mustPrintHelpWhenNoOptions() throws ParseException {
        String[] args = {};
        Main.main(args);
        // TODO: use Mockito to verify call?
    }
    
}
