package com.ef.parser.persistence;

import java.util.Map;
import org.sql2o.Connection;
import org.sql2o.Query;

/**
 *
 * @author Rogerio J. Gentil
 */
public class LogDAO extends AbstractPersistence implements LogPersistence {

    @Override
    public void load(Map<String, Integer> ips) {
        final String sql = "INSERT INTO log(ip, comment_block) VALUES (:ip, :comment_block)";

        try (Connection connection = sql2o.beginTransaction()) {
            Query query = connection.createQuery(sql);

            ips.entrySet().forEach(ip -> {
                query.addParameter("ip", ip.getKey())
                        .addParameter("comment_block", "Number of requests: " + ip.getValue())
                        .addToBatch();
            });

            query.executeBatch();
            connection.commit();
        }
    }

}
