package pl.edu.agh.to.testerka;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.JythonSandbox;
import pl.edu.agh.to.testerka.services.RunnerService;
import pl.edu.agh.to.testerka.services.StatusService;

import static spark.Spark.get;

public class TesterHttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesterHttpHandler.class);

    private RunnerService runnerService;
    private StatusService statusService;

    public TesterHttpHandler(RunnerService runnerService, StatusService statusService) {
        this.runnerService = runnerService;
        this.statusService = statusService;
    }

    public void setupTestersAPI() {

        Gson gson = new Gson();
        get("/solutions/:solution_id", (req, res) -> {
            Integer solutionId = Integer.valueOf(req.params(":solution_id"));
            LOGGER.info("Received GET request for solution {}.", solutionId);


            if (runnerService.isInProgress(solutionId)) {
                LOGGER.info("Solution {} already in progress.", solutionId);
                return TaskStatus.IN_PROGRESS;
            }
            LOGGER.info("Using {}", statusService.getClass());
            TaskStatus status = statusService.getStatusFor(solutionId);

            if (status == TaskStatus.NOT_TESTED) {
                runnerService.submitTask(solutionId, new JythonSandbox());
                LOGGER.info("Submitted job for solution {}.", solutionId);
                return TaskStatus.IN_PROGRESS;
            }
            LOGGER.info("Returning " + status.toString() + " for solution {}.", solutionId);
            return status;
        }, gson::toJson);
    }
}
