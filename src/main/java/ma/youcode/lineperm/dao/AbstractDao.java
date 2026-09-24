package ma.youcode.lineperm.dao;

import java.sql.Connection;

import ma.youcode.lineperm.database.DBConnection;

public abstract class AbstractDao<T> implements Dao<T> {

    protected Connection connect;

    public AbstractDao() {
        this.connect = DBConnection.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connect;
    }
}