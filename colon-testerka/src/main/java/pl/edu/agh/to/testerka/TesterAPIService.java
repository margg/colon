package pl.edu.agh.to.testerka;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.JythonSandbox;

import static spark.Spark.get;

public class TesterAPIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesterAPIService.class);
    private RunnerService runnerService;
    private StatusService statusService;

    public TesterAPIService(RunnerService runnerService, StatusService statusService) {
        this.runnerService = runnerService;
        this.statusService = statusService;
    }

    public void setupTestersAPI() {
        Gson gson = new Gson();
        get("/solutions/:solution_id", (req, res) -> {
            String solutionId = req.params(":solution_id");
            LOGGER.info("Received GET request for solution {}.", solutionId);
            TaskStatus status = new TaskStatus();

            if (runnerService.isInProgress(solutionId)) {
                status.setInProgress();
            } else {
                status = statusService.getStatusFor(solutionId);
            }

            if (status.isNotStarted()) {
                runnerService.submitTask(solutionId, new JythonSandbox());
                status = new TaskStatus();
                status.setInProgress();
            }
            return status;
        }, gson::toJson);
    }
}
