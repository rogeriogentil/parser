package com.ef.parser.persistence;

import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Query;

/**
 *
 * @author Rogerio J. Gentil
 */
public class LogDAO extends AbstractPersistence implements LogPersistence {

    @Override
    public void load(List<String> ips, String comment) {
        final String sql = "INSERT INTO log(ip, comment_block) VALUES (:ip, :comment_block)";

        try (Connection connection = sql2o.beginTransaction()) {
            Query query = connection.createQuery(sql);

            ips.forEach(ip -> {
                query.addParameter("ip", ip)
                        .addParameter("comment_block", comment)
                        .addToBatch();
            });

            query.executeBatch();
            connection.commit();
        }
    }

}
