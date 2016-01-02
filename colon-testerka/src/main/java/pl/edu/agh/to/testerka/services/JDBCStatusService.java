package pl.edu.agh.to.testerka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.TaskStatus;
import pl.edu.agh.to.testerka.sandbox.TestResultStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JDBCStatusService implements StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCStatusService.class);

    private DBConnection dbConnection;
    private Map<Integer, TestResultStatus> statusMap = new HashMap<>();

    public JDBCStatusService(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        statusMap.put(0, TestResultStatus.NOT_TESTED);
        statusMap.put(1, TestResultStatus.OK);
        statusMap.put(2, TestResultStatus.TIME_LIMIT_EXCEEDED);
        statusMap.put(3, TestResultStatus.ANSWER);
        statusMap.put(4, TestResultStatus.RUNTIME_ERROR);
        statusMap.put(5, TestResultStatus.REJECTED);

    }

    @Override
    public TaskStatus getStatusFor(String solutionId) {
        TestResultStatus testResultStatus = null;
        try(Connection connection = dbConnection.getConnection()) {
            String query = "SELECT status from Solutions where solution_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setNString(1, solutionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int dbStatus = resultSet.getInt("status");
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
