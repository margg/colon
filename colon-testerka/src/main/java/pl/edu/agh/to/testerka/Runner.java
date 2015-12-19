package pl.edu.agh.to.testerka;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.JythonSandbox;

import static spark.Spark.get;

public class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private RunnerService runnerService;
    private StatusService statusService;

    public Runner(RunnerService runnerService, StatusService statusService) {
        this.runnerService = runnerService;
        this.statusService = statusService;
    }

    public static void main(String[] args) {

        JDBCServiceProvider jdbcServiceProvider = new JDBCServiceProvider();

        RunnerService runnerService = new RunnerService(jdbcServiceProvider, jdbcServiceProvider);
        StatusService statusService = new StatusService(runnerService);
        Runner runner = new Runner(runnerService, statusService);

        runner.setupTestersAPI();
        FilerMock filerMock = new FilerMock();
        filerMock.setupAPI();
        DBServiceMock dbServiceMock = new DBServiceMock();
        dbServiceMock.setupAPI();

    }

    private void setupTestersAPI() {
        Gson gson = new Gson();
        get("/solutions/:solution_id", (req, res) -> {
            String solutionId = req.params(":solution_id");
            LOGGER.info("Received GET request for solution {}.", solutionId);

            TaskStatus status = statusService.get(solutionId);
            if (status.isNotStarted()) {
                runnerService.submitTask(solutionId, new JythonSandbox());
                status = new TaskStatus();
                status.setInProgress();
            }
            return status;
        }, gson::toJson);
    }
}