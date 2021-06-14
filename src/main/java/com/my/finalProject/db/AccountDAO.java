package com.my.finalProject.db;

import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.finalProject.Fields.*;

public class AccountDAO {
    private static final Logger LOGGER = Logger.getLogger(AccountDAO.class.getName());

    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM account WHERE login=?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM account WHERE id=?";
    private static final String UPDATE_ACCOUNT = "UPDATE account SET login=?, password=?"
            + ", firstName=?, lastName=?, middleName=?, email=?, city=?, region=?"
            + ", certificate_id=?, roles_id=?, studyForm_id=?, faculties_name=?"
            + "  WHERE id=?";
    private static final String INSERT_ACCOUNT = "INSERT INTO account(login, password" +
            ", firstName, lastName, middleName, email, city, region" +
            ", certificate_id, roles_id, studyForm_id, faculties_name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_ACCOUNT_BY_LOGIN = "DELETE FROM account WHERE login =?";
    private static final String FIND_ALL_ACCOUNT = "SELECT * FROM account";

    private static AccountDAO instance;
    private AccountDAO(){}

    public static synchronized AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public Account findAccountByID(Long id) throws DBException {
        Account account = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            AccountMapper accountMapper = new AccountMapper();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = accountMapper.mapRow(resultSet);

            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("find account by id fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("can not find account by id ", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return account;
    }



    public Account findAccountByLogin(String login) throws DBException {
        Account account = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            AccountMapper accountMapper = new AccountMapper();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(PARAMETER_INDEX, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = accountMapper.mapRow(resultSet);
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("find account by login fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("can not find account -->", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return account;
    }

    public void updateAccount(Connection connection, Account account) throws DBException {
        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }

            preparedStatement = connectionLocal.prepareStatement(UPDATE_ACCOUNT);
            setStatement(account, preparedStatement);
            preparedStatement.executeUpdate();
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.error("update account fail" + e.getMessage(), e);
            if (needToClose) {
                DBManager.getInstance().rollback(connectionLocal);
            }
            throw new DBException("update account fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
    }

    private void setStatement(Account account, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setString(k++, account.getLogin());
        preparedStatement.setString(k++, account.getPassword());
        preparedStatement.setString(k++, account.getFirstName());
        preparedStatement.setString(k++, account.getLastName());
        preparedStatement.setString(k++, account.getMiddleName());
        preparedStatement.setString(k++, account.getEMail());
        preparedStatement.setString(k++, account.getCity());
        preparedStatement.setString(k++, account.getRegion());
        preparedStatement.setLong(k++, account.getCertificate_id());
        preparedStatement.setLong(k++, account.getRole_id());
        preparedStatement.setLong(k++, account.getStudyForm_id());
        preparedStatement.setString(k++, account.getFaculties_name());
        if (account.getId() != null) {
            preparedStatement.setLong(k, account.getId());
        }

    }

    public void addAccount(Connection connection, Account account) throws DBException {

        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }
            preparedStatement = connectionLocal.prepareStatement(INSERT_ACCOUNT,
                    Statement.RETURN_GENERATED_KEYS);
            setStatement(account, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getLong(1));
                LOGGER.trace("id certificate " + account.getId());
            }
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.error("add account fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("add account fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(resultSet);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
    }


    public void deleteAccountByLogin(String login) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_BY_LOGIN);
            preparedStatement.setString(PARAMETER_INDEX, login);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("delete account by login --> " + login + " fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("delete account by login fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    public List<Account> findAllAccount() throws DBException {

        List<Account> listAccount = new ArrayList<>();
        AccountMapper accountMapper = new AccountMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_ACCOUNT);
            while (resultSet.next()) {
                listAccount.add(accountMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("find all account fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("add account fail", e);
        } finally {
            DBManager.getInstance().closeResource(statement);
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(connection);
        }
        return listAccount;
    }

    private static class AccountMapper implements EntityMapper<Account> {

        @Override
        public Account mapRow(ResultSet resultSet) {
            try {
                Account account = new Account();
                account.setId(resultSet.getLong(ENTITY__ID));
                account.setLogin(resultSet.getString(ACCOUNT_LOGIN));
                account.setPassword(resultSet.getString(ACCOUNT_PASSWORD));
                account.setFirstName(resultSet.getString(ACCOUNT__FIRST_NAME));
                account.setLastName(resultSet.getString(ACCOUNT__LAST_NAME));
                account.setMiddleName(resultSet.getString(ACCOUNT__MIDDLE_NAME));
                account.setMail(resultSet.getString(ACCOUNT_EMAIL));
                account.setCity(resultSet.getString(ACCOUNT__CITY));
                account.setRegion(resultSet.getString(ACCOUNT__REGION));
                account.setRole_id(resultSet.getInt(ACCOUNT__ROLE_ID));
                account.setCertificate_id(resultSet.getLong(ACCOUNT__CERTIFICATE_ID));
                account.setStudyForm_id(resultSet.getLong(ACCOUNT__STUDY_FORM_ID));
                account.setFaculties_name(resultSet.getString(ACCOUNT__FACULTIES_NAME));

                return account;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}