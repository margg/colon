package pl.edu.agh.to.testerka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.TestResultStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSolutionProvider implements SolutionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCSolutionProvider.class);

    private DBConnection dbConnection;

    public JDBCSolutionProvider(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Integer> getUntestedSolutions() {
        List<Integer> untestedIds = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT id FROM solutions WHERE status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, TestResultStatus.NOT_TESTED.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                untestedIds.add(resultSet.getInt("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error while getting untested solutions from DB.", e);
        }
        return untestedIds;
    }
}
