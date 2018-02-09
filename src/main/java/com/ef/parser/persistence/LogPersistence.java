package com.ef.parser.persistence;

import java.util.List;

/**
 *
 * @author Rogerio J. Gentil
 */
public interface LogPersistence {

    public void load(List<String> ips, String comment);
    
}
