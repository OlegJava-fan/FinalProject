package com.my.finalProject.db;

import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());

    private static DBManager instance;
    DataSource dataSource;

    private DBManager() {

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
        } catch (NamingException e) {
            LOGGER.error("can not create connection pool --> ", e);
           throw  new DBException("can not create connection pool -->",e);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection ;
        try {
            connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException ex) {
            LOGGER.error("Can not create connection because: ", ex);
            throw  new DBException("Can not create connection because: ",ex);
        }
        return connection;
    }

    public void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                LOGGER.trace("commit fail", ex);
                throw  new DBException("commit fail",ex);
            }
        }
    }

    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.trace("rollback fail", ex);
                throw  new DBException("rollback fail",ex);
            }
        }
    }

    public void closeResource(AutoCloseable resource) {
        if (resource != null)
            try {
                resource.close();
            } catch (Exception e) {
                LOGGER.trace("close resource fail -> " + resource, e);
            }
    }
}
