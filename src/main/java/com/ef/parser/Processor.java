package com.ef.parser;

import com.ef.parser.model.AccessLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Rogerio J. Gentil
 */
public interface Processor {

    Collection<AccessLog> read(File file) throws IOException;    
    
}
