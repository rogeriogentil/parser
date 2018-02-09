package com.ef.parser.persistence;

import com.ef.parser.model.AccessLog;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Query;

/**
 *
 * @author Rogerio J. Gentil
 */
public class AccessLogDAO extends AbstractPersistence implements AccessLogPersistence {

    @Override
    public void persist(Collection<AccessLog> accessLogs) {
        final String sql
                = "INSERT INTO accesslog(date, ip, request, status, user_agent) "
                + "VALUES (:date, :ip, :request, :status, :userAgent)";

        try (Connection connection = sql2o.beginTransaction()) {
            Query query = connection.createQuery(sql);

            accessLogs.forEach(log -> {
                query.addParameter("date", log.getDate())
                        .addParameter("ip", log.getIp())
                        .addParameter("request", log.getRequest())
                        .addParameter("status", log.getStatus())
                        .addParameter("userAgent", log.getUserAgent())
                        .addToBatch();
            });

            query.executeBatch();
            connection.commit();
        }
    }

    @Override
    public List<String> findIps(LocalDateTime startDate, LocalDateTime finalDate, int threshold) {
        final String sql
                = "SELECT ip AS requests "
                + "FROM accesslog "
                + "WHERE date >= :startDate AND date <= :finalDate "
                + "GROUP BY ip "
                + "HAVING COUNT(request) > :threshold";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("startDate", startDate)
                    .addParameter("finalDate", finalDate)
                    .addParameter("threshold", threshold)
                    .executeScalarList(String.class);
                    
        }
    }

}
