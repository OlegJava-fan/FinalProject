package com.my.finalProject.db;

import com.my.finalProject.Fields;
import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.finalProject.Fields.*;

public class CertificateDAO {
    private static final Logger LOGGER = Logger.getLogger(CertificateDAO.class.getName());

    private static final String FIND_CERTIFICATE_BY_ID = "SELECT * FROM certificate WHERE id=?";
    private static final String INSERT_CERTIFICATE = "INSERT INTO certificate(mathematics, chemistry" +
            ", physics, english, ukrainian, informatics" +
            ", geography, biology, history, gym, averageScore) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_CERTIFICATE = "UPDATE certificate SET mathematics=?, chemistry=?"
            + ", physics=?, english=?, ukrainian=?, informatics=?"
            + ", geography=?, biology=?, history=?, gym=?, averageScore=?"
            + "  WHERE id=?";

    private static final String DELETE_CERTIFICATE_BY_ID = "DELETE FROM certificate WHERE id =?";
    private static final String FIND_ALL_CERTIFICATE = "SELECT * FROM certificate";

    private static CertificateDAO instance;

    public static synchronized CertificateDAO getInstance() {
        if (instance == null) {
            instance = new CertificateDAO();
        }
        return instance;
    }

    public Certificate findCertificateByID(Long id) throws DBException {

        Certificate certificate = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            CertificateMapper certificateMapper = new CertificateMapper();
            preparedStatement = connection.prepareStatement(FIND_CERTIFICATE_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                certificate = certificateMapper.mapRow(resultSet);
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("find certificate by id fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("find certificate by id fail", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
        return certificate;
    }


    public void UpdateCertificate(Certificate certificate) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CERTIFICATE);

            setStatement(certificate, preparedStatement);
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.trace("update certificate fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("update certificate fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    public void addCertificate(Connection connection, Certificate certificate) throws DBException {

        Connection connectionLocal = connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean needToClose = false;
        try {
            if (connectionLocal == null) {
                connectionLocal = DBManager.getInstance().getConnection();
                needToClose = true;
            }

            preparedStatement = connectionLocal.prepareStatement(INSERT_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            setStatement(certificate, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                certificate.setId(resultSet.getLong(1));
                LOGGER.trace("id certificate " + certificate.getId());
            }
            if (needToClose) {
                DBManager.getInstance().commit(connectionLocal);
            }
        } catch (SQLException e) {
            LOGGER.error("add certificate fail", e);
            DBManager.getInstance().rollback(connectionLocal);
            throw new DBException("can not add account", e);
        } finally {
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(preparedStatement);
            if (needToClose) {
                DBManager.getInstance().closeResource(connectionLocal);
            }
        }
    }

    private void setStatement(Certificate certificate, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setLong(k++, certificate.getMathematics());
        preparedStatement.setLong(k++, certificate.getChemistry());
        preparedStatement.setLong(k++, certificate.getPhysics());
        preparedStatement.setLong(k++, certificate.getEnglish());
        preparedStatement.setLong(k++, certificate.getUkrainian());
        preparedStatement.setLong(k++, certificate.getInformatics());
        preparedStatement.setLong(k++, certificate.getGeography());
        preparedStatement.setLong(k++, certificate.getBiology());
        preparedStatement.setLong(k++, certificate.getHistory());
        preparedStatement.setLong(k++, certificate.getGym());
        preparedStatement.setLong(k++, certificate.getAverageScore());
        if (certificate.getId() != null) {
            preparedStatement.setLong(k, certificate.getId());
        }

    }


    public void deleteCertificateById(Long id) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CERTIFICATE_BY_ID);
            preparedStatement.setLong(PARAMETER_INDEX, id);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("delete certificate by id --> " + id + " fail", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("delete certificate by id --> " + id + " fail", e);
        } finally {
            DBManager.getInstance().closeResource(preparedStatement);
            DBManager.getInstance().closeResource(connection);
        }
    }

    public List<Certificate> findAllCertificate() throws DBException {

        List<Certificate> listCertificate = new ArrayList<>();
        CertificateMapper certificateMapper = new CertificateMapper();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_CERTIFICATE);
            while (resultSet.next()) {
                listCertificate.add(certificateMapper.mapRow(resultSet));
            }
            DBManager.getInstance().commit(connection);
        } catch (SQLException e) {
            LOGGER.error("find all certificate fail -->", e);
            DBManager.getInstance().rollback(connection);
            throw new DBException("find all certificate fail -->", e);
        } finally {
            DBManager.getInstance().closeResource(statement);
            DBManager.getInstance().closeResource(resultSet);
            DBManager.getInstance().closeResource(connection);

        }
        return listCertificate;
    }

    private static class CertificateMapper implements EntityMapper<Certificate> {

        @Override
        public Certificate mapRow(ResultSet resultSet) {
            try {
                Certificate certificate = new Certificate();
                certificate.setId(resultSet.getLong(Fields.ENTITY__ID));
                certificate.setMathematics(resultSet.getLong(Fields.CERTIFICATE_MATH));
                certificate.setChemistry(resultSet.getLong(Fields.CERTIFICATE_CHEMISTRY));
                certificate.setPhysics(resultSet.getLong(Fields.CERTIFICATE_PHYSICS));
                certificate.setEnglish(resultSet.getLong(Fields.CERTIFICATE_ENGLISH));
                certificate.setUkrainian(resultSet.getLong(Fields.CERTIFICATE_UKRAINIAN));
                certificate.setInformatics(resultSet.getLong(Fields.CERTIFICATE_INFORMATICS));
                certificate.setGeography(resultSet.getLong(Fields.CERTIFICATE_GEOGRAPHY));
                certificate.setBiology(resultSet.getLong(Fields.CERTIFICATE_BIOLOGY));
                certificate.setHistory(resultSet.getLong(Fields.CERTIFICATE_HISTORY));
                certificate.setGym(resultSet.getLong(Fields.CERTIFICATE_GYM));
                certificate.setAverageScore(resultSet.getLong(Fields.CERTIFICATE_AVERAGE_SCORE));

                return certificate;

            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
