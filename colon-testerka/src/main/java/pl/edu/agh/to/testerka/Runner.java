package pl.edu.agh.to.testerka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.services.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private static final String PROPERTIES_FILEPATH = "./colon-testerka/src/main/resources/application.properties";

    public static void main(String[] args) {

        Properties properties = new Properties();
        try(FileInputStream in = new FileInputStream(PROPERTIES_FILEPATH)) {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error("Error while loading properties.", e);
        }

        String dbHostAddress = properties.getProperty("dbHostAddress");
        String dbPort = properties.getProperty("dbPort");
        String dbName = properties.getProperty("dbName");
        String dbUsername = properties.getProperty("dbUsername");
        String dbPassword = properties.getProperty("dbPassword");

        String filerHostAddress = properties.getProperty("filerHostAddress");
        String filerPort = properties.getProperty("filerPort");

        JDBCSaveResultService jdbcSaveResultService =
                new JDBCSaveResultService(new DBConnection(dbHostAddress, dbPort, dbName, dbUsername, dbPassword));
        FileContentProvider httpProvider = new HttpFileProvider(filerHostAddress + ":" + filerPort);
        RunnerService runnerService = new RunnerService(jdbcSaveResultService, httpProvider);
        StatusService statusService =
                new JDBCStatusService(new DBConnection(dbHostAddress, dbPort, dbName, dbUsername, dbPassword));
        TesterHttpHandler testerHttpHandler = new TesterHttpHandler(runnerService, statusService);
        testerHttpHandler.setupTestersAPI();

        FilerMock filerMock = new FilerMock();
        filerMock.setupAPI();
        DBServiceMock dbServiceMock = new DBServiceMock();
        dbServiceMock.setupAPI();
    }
}