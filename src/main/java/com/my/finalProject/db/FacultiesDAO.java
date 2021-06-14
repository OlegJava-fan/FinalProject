package com.my.finalProject.db;

import com.my.finalProject.Fields;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import static com.my.finalProject.Fields.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultiesDAO {
    private static final Logger LOGGER = Logger.getLogger(FacultiesDAO.class.getName());

    private static final String FIND_FACULTIES_BY_NAME = "SELECT * FROM faculties WHERE name=?";
    private static final String FIND_FACULTIES_BY_ID = "SELECT * FROM faculties WHERE id=?";
    private static final String INSERT_FACULTIES = "INSERT INTO faculties(name, passingScoreFreeForm" +
            ", passingScorePayForm, allPlaces, freeFormPlaces, payFormPlaces) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_FACULTIES = "UPDATE faculties SET name=?, passingScoreFreeForm=?"
            + ", passingScorePayForm=?, allPlaces=?, freeFormPlaces=?, payFormPlaces=?"
            + "  WHERE id=?";
    private static final String FIND_ALL_FACULTIES = "SELECT * FROM faculties";
    private static final String DELETE_FACULTIES_BY_ID = "DELETE FROM faculties WHERE id =?";
    private static final String FIND_LESS_FIVE_FACULTIES = "SELECT * FROM faculties WHERE allPlaces<=5";

    private static FacultiesDAO instance;


    public static synchronized FacultiesDAO getInstance() {
        if (instance == null) {
            instance = new FacultiesDAO();
        }
        return instance;
    }

    public Faculties findFacultiesByID(Long id) throws DBException {
        Faculties faculties = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {

            connection = DBManager.getInstance().getConnection();
            FacultiesMapper facultiesMapper = new FacultiesMapper();
            preparedStatement = connection.prepareStatement(FIND_FACULTIES_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                faculties = facultiesMapper.mapRow(resultSet);
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            DBManager.getInstance().rollback(connection);
            LOGGER.error("find faculties by id fail", e);
            throw new DBException("find faculties by id fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return faculties;
    }

    public Faculties findFacultiesByIdForTransaction(Connection connection, Long id) {

        Faculties faculties = null;
        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }
            FacultiesMapper facultiesMapper = new FacultiesMapper();
            preparedStatement = connectionLocal.prepareStatement(FIND_FACULTIES_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                faculties = facultiesMapper.mapRow(resultSet);
            }
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.trace("find faculties by name fail", e);
            DBManager.getInstance().rollback(connectionLocal);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
        return faculties;
    }

    public void updateFaculties(Connection connection, Faculties faculties) throws DBException {

        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }

            preparedStatement = connectionLocal.prepareStatement(UPDATE_FACULTIES);

            setStatement(faculties, preparedStatement);
            preparedStatement.executeUpdate();
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.trace("update faculties fail", e);
            if (needToClose) {
                DBManager.getInstance().rollback(connectionLocal);
            }
            throw new DBException("update faculties fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
    }

    public void addFaculties(Faculties faculties) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_FACULTIES,
                    Statement.RETURN_GENERATED_KEYS);
            setStatement(faculties, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                faculties.setId(resultSet.getLong(1));
                LOGGER.trace("id faculties " + faculties.getId());
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("add faculties fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("add faculties fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    private void setStatement(Faculties faculties, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;

        preparedStatement.setString(k++, faculties.getName());
        preparedStatement.setLong(k++, faculties.getPassingScoreFreeForm());
        preparedStatement.setLong(k++, faculties.getPassingScorePayForm());
        preparedStatement.setLong(k++, faculties.getAllPlace());
        preparedStatement.setLong(k++, faculties.getFreeFormPlaces());
        preparedStatement.setLong(k++, faculties.getPayFormPlaces());
        if (faculties.getId() != null) {
            preparedStatement.setLong(k, faculties.getId());
        }
    }


    public void deleteFacultiesByName(Long id) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FACULTIES_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("delete faculties by id --> " + id + " fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("delete faculties fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    public List<Faculties> findAllFaculties() throws DBException {

        List<Faculties> listFaculties = new ArrayList<>();
        FacultiesMapper facultiesMapper = new FacultiesMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_FACULTIES);
            while (resultSet.next()) {
                listFaculties.add(facultiesMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("find all faculties fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("delete faculties fail", e);
        } finally {
            DBManager.getInstance().closeResource(statement);
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(connection);
        }
        return listFaculties;
    }

    public List<Faculties> findFacultiesLessFive() throws DBException {

        List<Faculties> listFaculties = new ArrayList<>();
        FacultiesMapper facultiesMapper = new FacultiesMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_LESS_FIVE_FACULTIES);
            while (resultSet.next()) {
                listFaculties.add(facultiesMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("find all faculties fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("delete faculties fail", e);
        } finally {
            DBManager.getInstance().closeResource(statement);
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(connection);
        }
        return listFaculties;
    }

    private static class FacultiesMapper implements EntityMapper<Faculties> {

        @Override
        public Faculties mapRow(ResultSet resultSet) {
            try {
                Faculties faculties = new Faculties();
                faculties.setId(resultSet.getLong(Fields.ENTITY__ID));
                faculties.setName(resultSet.getString(Fields.FACULTIES_NAME));
                faculties.setAllPlace(resultSet.getLong(Fields.FACULTIES_ALL_PLACE));
                faculties.setPassingScoreFreeForm(resultSet.getLong(Fields.FACULTIES_PASSING_SCORE_FREE_FORM));
                faculties.setPassingScorePayForm(resultSet.getLong(Fields.FACULTIES_PASSING_SCORE_PAY_FORM));
                faculties.setFreeFormPlaces(resultSet.getLong(Fields.FACULTIES_FREE_FORM_PLACES));
                faculties.setPayFormPlaces(resultSet.getLong(Fields.FACULTIES_PAY_FORM_PLACES));

                /* faculties.setLocaleName(rs.getString(Fields.FACULTIES__LOCALE_NAME));*/
                return faculties;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
