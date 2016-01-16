package pl.edu.agh.to.testerka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.services.*;

import static spark.Spark.setPort;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private static final String PROPERTIES_FILEPATH = "application.properties";
    private static TimeUnit timeUnit = TimeUnit.MINUTES;

    public static void main(String[] args) {

        String propertiesFilepath;
        if (args.length == 0) {
            propertiesFilepath = PROPERTIES_FILEPATH;
            System.out.println("You can specify a properties file by providing the file path as an argument.");
            System.out.println("Using default filepath for properties file: " + propertiesFilepath);
        } else {
            propertiesFilepath = args[0];
            System.out.println("Using properties file: " + propertiesFilepath);
        }

        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFilepath)) {
            properties.load(in);
            LOGGER.info("Loaded properties from file: " + propertiesFilepath);
        } catch (IOException e) {
            LOGGER.error("Error while loading properties from: " + propertiesFilepath, e);
            System.out.println("Error while loading properties. Check your properties file.");
            System.exit(1);
        }

        String dbHostAddress = properties.getProperty("dbHostAddress");
        String dbPort = properties.getProperty("dbPort");
        String dbName = properties.getProperty("dbName");
        String dbUsername = properties.getProperty("dbUsername");
        String dbPassword = properties.getProperty("dbPassword");

        String filerHostAddress = properties.getProperty("filerHostAddress");
        String filerPort = properties.getProperty("filerPort");

        Integer periodInMinutes = Integer.valueOf(properties.getProperty("schedulerPeriodInMinutes"));
        String testerkaPort = properties.getProperty("testerkaPort");

        setPort(Integer.valueOf(testerkaPort));

        JDBCSaveResultService jdbcSaveResultService =
                new JDBCSaveResultService(new DBConnection(dbHostAddress, dbPort, dbName, dbUsername, dbPassword));
        FileContentProvider httpProvider = new HttpFileProvider(filerHostAddress + ":" + filerPort);
        RunnerService runnerService = new RunnerService(jdbcSaveResultService, httpProvider);
        StatusService statusService =
                new JDBCStatusService(new DBConnection(dbHostAddress, dbPort, dbName, dbUsername, dbPassword));
        TesterHttpHandler testerHttpHandler = new TesterHttpHandler(runnerService, statusService);
        testerHttpHandler.setupTestersAPI();

        TesterScheduledService testerScheduledService = new TesterScheduledService(
                new JDBCSolutionProvider(new DBConnection(dbHostAddress, dbPort, dbName, dbUsername, dbPassword)),
                runnerService, periodInMinutes, timeUnit);
        testerScheduledService.startAsync();
    }
}