package com.my.finalProject.db;

import com.my.finalProject.Fields;
import com.my.finalProject.db.entity.entityImpl.Order;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.finalProject.Fields.PARAMETER_INDEX;

public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    private static final String FIND_ORDER_BY_ID = "SELECT * FROM `order` WHERE id=?";
    private static final String INSERT_ORDER = "INSERT INTO `order` (account_id, status_id, faculties_id ) VALUES (?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE `order` SET account_id=?, status_id=?, faculties_id=?"
            + " WHERE id=?";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE account_id =?";
    private static final String FIND_ALL_ORDER_BY_ACCOUNT_ID = "SELECT * FROM `order` WHERE account_id =?";
    private static final String FIND_ORDER_BY_ACCOUNT_ID_FACULTIES_ID = "SELECT * FROM `order` WHERE account_id =? AND faculties_id =?";
    private static OrderDAO instance;

    private OrderDAO() {
    }

    public static synchronized OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public Order findOrderByID(Long id) throws DBException {
        Order order = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper orderMapper = new OrderMapper();
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = orderMapper.mapRow(resultSet);
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
        return order;
    }

    public Order findOrderByAccountIdAndFacultiesId(Long account_id, Long faculties_id) throws DBException {
        Order order = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper orderMapper = new OrderMapper();
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ACCOUNT_ID_FACULTIES_ID);
            preparedStatement.setLong(PARAMETER_INDEX, account_id);
            preparedStatement.setLong(2, faculties_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = orderMapper.mapRow(resultSet);
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
        return order;
    }

    public void UpdateOrder(Order order) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            setStatement(order, preparedStatement);
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

    public void addOrder(Order order) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER,
                    Statement.RETURN_GENERATED_KEYS);
            setStatement(order, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                LOGGER.trace("id order " + order.getId());
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("add order fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("add order fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    private void setStatement(Order order, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;

        preparedStatement.setLong(k++, order.getAccount_id());
        preparedStatement.setLong(k++, order.getStatus_id());
        preparedStatement.setLong(k++, order.getFaculties_id());
        if (order.getId() != null) {
            preparedStatement.setLong(k, order.getId());
        }
    }


    public void deleteOrderByID(Long id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("delete order by id --> " + id + " fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("delete order by id fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    public List<Order> findAllOrder() throws DBException {

        List<Order> listOrder = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_ORDER_BY_ACCOUNT_ID);
            while (resultSet.next()) {
                listOrder.add(orderMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("find all order fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("find all orders fail", e);
        } finally {
            DBManager.getInstance().closeResource(statement);
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(connection);
        }
        return listOrder;

    }

    public List<Order> findAllOrderByAccountID(Long id) throws DBException {

        List<Order> listOrder = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_ORDER_BY_ACCOUNT_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listOrder.add(orderMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("find all order fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("find all orders fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return listOrder;
    }

    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet resultSet) {
            try {
                Order order = new Order();
                order.setId(resultSet.getLong(Fields.ENTITY__ID));
                order.setAccount_id(resultSet.getLong(Fields.ORDER_ACCOUNT_ID));
                order.setStatus_id(resultSet.getLong(Fields.ORDER_STATUS_ID));
                order.setFaculties_id(resultSet.getLong(Fields.ORDER_FACULTIES_ID));

                return order;

            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
