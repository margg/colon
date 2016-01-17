package pl.edu.agh.to.testerka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.TestResult;

import java.sql.*;

public class JDBCSaveResultService implements SaveResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCSaveResultService.class);

    private DBConnection dbConnection;

    public JDBCSaveResultService(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void save(TestResult result, Integer solutionId) {
        try (Connection connection = dbConnection.getConnection()) {
            String query = "UPDATE solutions SET status = ? , exec_time = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, result.getTestResultStatus().name());
            preparedStatement.setInt(2, (int) result.getExecutionTimeMillis());
            preparedStatement.setInt(3, solutionId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error while saving result to DB.", e);
        }
    }
}
