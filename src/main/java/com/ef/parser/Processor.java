package com.ef.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Rogerio J. Gentil
 */
public interface Processor {

    Map<String, Integer> findIps(File file, LocalDateTime startDate, DurationArg duration, int threshold) throws IOException;
    
}
