package com.ef.parser.persistence;

import org.sql2o.Sql2o;

/**
 *
 * @author Rogerio J. Gentil
 */
public abstract class AbstractPersistence {

    protected final Sql2o sql2o;

    public AbstractPersistence() {
        this.sql2o = new Sql2o("jdbc:mysql://localhost:3306/parser", "root", "root");
    }

}
