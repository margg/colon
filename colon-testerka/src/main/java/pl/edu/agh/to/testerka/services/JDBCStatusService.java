package pl.edu.agh.to.testerka.services;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.TaskStatus;
import pl.edu.agh.to.testerka.sandbox.TestResultStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class JDBCStatusService implements StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCStatusService.class);

    private DBConnection dbConnection;
    private static final Map<String, TestResultStatus> statusMap = ImmutableMap.<String, TestResultStatus>builder()
            .put("NOT", TestResultStatus.NOT_TESTED)
            .put("OK", TestResultStatus.OK)
            .put("TLE", TestResultStatus.TIME_LIMIT_EXCEEDED)
            .put("ANS", TestResultStatus.ANSWER)
            .put("RTE", TestResultStatus.RUNTIME_ERROR)
            .put("REJ", TestResultStatus.REJECTED)
            .build();

    public JDBCStatusService(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public TaskStatus getStatusFor(Integer solutionId) {
        TestResultStatus testResultStatus = null;
        try(Connection connection = dbConnection.getConnection()) {
            String query = "SELECT status FROM solutions WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, solutionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return TaskStatus.NON_EXISTING;
            }
            String dbStatus = resultSet.getString("status");
            dbStatus = (dbStatus == null ? "NOT" : dbStatus);
            testResultStatus = statusMap.get(dbStatus);

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error while getting status for solution " + solutionId, e);
        }

        if(testResultStatus == TestResultStatus.NOT_TESTED) {
            return TaskStatus.NOT_TESTED;
        }
        return TaskStatus.TESTED;
    }
}
