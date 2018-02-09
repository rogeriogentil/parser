package com.ef.parser.persistence;

import com.ef.parser.model.AccessLog;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Rogerio J. Gentil
 */
public interface AccessLogPersistence {
 
    void persist(Collection<AccessLog> accessLogs);
    
    List<String> findIps(LocalDateTime startDate, LocalDateTime finalDate, int threshold);
}
