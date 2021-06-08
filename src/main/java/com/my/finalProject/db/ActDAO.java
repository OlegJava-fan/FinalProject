package com.my.finalProject.db;

import com.my.finalProject.Fields;
import com.my.finalProject.db.entity.entityImpl.Act;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.finalProject.Fields.*;

public class ActDAO {
    private static final Logger LOGGER = Logger.getLogger(ActDAO.class.getName());

    private static final String FIND_ACT_BY_ID = "SELECT * FROM `act` WHERE id=?";
    private static final String UPDATE_ACT = "UPDATE `act` SET account_id=?, faculties_id=?, status=?, faculties_name=?"
            + "  WHERE id=?";
    private static final String INSERT_ACT = "INSERT INTO `act` ( account_id, faculties_id, status, faculties_name ) VALUES(?, ?, ?, ?)";
    private static final String DELETE_ACT_BY_ACCOUNT_ID = "DELETE FROM act WHERE account_id =?";
    private static final String FIND_ALL_ACT_BY_ID = "SELECT * FROM act WHERE account_id =?";

    private static ActDAO instance;

    public static synchronized ActDAO getInstance() {
        if (instance == null) {
            instance = new ActDAO();
        }
        return instance;
    }

    public Act findActByID(Long id) throws DBException {
        Act act = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ActMapper orderMapper = new ActMapper();
            preparedStatement = connection.prepareStatement(FIND_ACT_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                act = orderMapper.mapRow(resultSet);
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            DBManager.getInstance().rollback(connection);
            LOGGER.trace("find order by account_id fail", e);
            throw new DBException("find order by account_id fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return act;
    }

    public void updateAct(Act act) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ACT);
            setStatement(act, preparedStatement);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("update order fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("update order fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    public void addAct(Connection connection, Act act) throws DBException {

        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }
            preparedStatement = connectionLocal.prepareStatement(INSERT_ACT,
                    Statement.RETURN_GENERATED_KEYS);
            setStatement(act, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                act.setId(resultSet.getLong(1));
                LOGGER.trace("id act " + act.getId());
            }
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.trace("add act fail", e);
            DBManager.getInstance().rollback(connectionLocal);
            throw new DBException("add act fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
    }

    private void setStatement(Act act, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;

        preparedStatement.setLong(k++, act.getAccount_id());
        preparedStatement.setLong(k++, act.getFaculties_id());
        preparedStatement.setString(k++, act.getStatus());
        preparedStatement.setString(k++, act.getActFacultiesName());
        if (act.getId() != null) {
            preparedStatement.setLong(k, act.getId());
        }
    }


    public void deleteAllActByAccountID(Connection connection, Long id) throws DBException {

        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }
            preparedStatement = connectionLocal.prepareStatement(DELETE_ACT_BY_ACCOUNT_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            preparedStatement.executeUpdate();
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.trace("delete act by id --> " + id + " fail", e);
            DBManager.getInstance().rollback(connectionLocal);
            throw new DBException("delete act by id fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
    }


    public List<Act> findAllActByAccountID(Long id) throws DBException {

        List<Act> listAct = new ArrayList<>();
        ActMapper orderMapper = new ActMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_ACT_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listAct.add(orderMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("find all act fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("find all act fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return listAct;
    }

    private static class ActMapper implements EntityMapper<Act> {

        @Override
        public Act mapRow(ResultSet resultSet) {
            try {
                Act act = new Act();
                act.setId(resultSet.getLong(Fields.ENTITY__ID));
                act.setAccount_id(resultSet.getLong(ACT_ACCOUNT_ID));
                act.setFaculties_id(resultSet.getLong(ACT_FACULTIES_ID));
                act.setStatus(resultSet.getString(ACT_STATUS));
                act.setActFacultiesName(resultSet.getString(ACT_FACULTIES_NAME));
                return act;

            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
