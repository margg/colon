package pl.edu.agh.to.testerka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            String query = "SELECT id FROM solutions WHERE status IS NULL";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                untestedIds.add(resultSet.getInt("id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error while getting untested solutions from DB.", e);
        }
        return untestedIds;
    }
}
