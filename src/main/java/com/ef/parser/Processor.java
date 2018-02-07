package com.ef.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Rogerio J. Gentil
 */
public interface Processor {

    Map<String, Integer> findIps(File file, String startDate, String duration, String threshold) throws IOException;
    
}
