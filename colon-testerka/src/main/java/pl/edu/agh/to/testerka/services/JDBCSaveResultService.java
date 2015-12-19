package pl.edu.agh.to.testerka.services;

import pl.edu.agh.to.testerka.sandbox.TestResult;

import java.sql.*;

public class JDBCSaveResultService implements SaveResultService {

    private DBConnection dbConnection;

    public JDBCSaveResultService(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void save(TestResult result, String solutionId) {

        try (Connection connection = dbConnection.getConnection()){
            String query = "UPDATE Solutions SET status = ? , execution_time = ? where solution_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, result.getTestResultStatus().ordinal());
            preparedStatement.setInt(2, (int) result.getExecutionTimeMillis());
            preparedStatement.setNString(3, solutionId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
