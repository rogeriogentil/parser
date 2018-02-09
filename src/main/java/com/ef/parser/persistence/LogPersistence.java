package com.ef.parser.persistence;

import java.util.Map;

/**
 *
 * @author Rogerio J. Gentil
 */
public interface LogPersistence {

    public void load(Map<String, Integer> ips);
    
}
