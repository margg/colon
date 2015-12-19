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
            String solutionId = req.params(":solution_id");
            LOGGER.info("Received GET request for solution {}.", solutionId);

            if (runnerService.isInProgress(solutionId)) {
                return TaskStatus.IN_PROGRESS;
            }

            TaskStatus status = statusService.getStatusFor(solutionId);

            if (status == TaskStatus.NOT_TESTED) {
                runnerService.submitTask(solutionId, new JythonSandbox());
                return TaskStatus.IN_PROGRESS;
            }
            return status;
        }, gson::toJson);
    }
}
