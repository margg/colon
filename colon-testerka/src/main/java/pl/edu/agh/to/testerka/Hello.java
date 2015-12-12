package pl.edu.agh.to.testerka;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;

public class Hello {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);
    private static RunnerService runnerService = new RunnerService();
    private static StatusService statusService = new StatusService(runnerService);

    public static void main(String[] args) {
        Gson gson = new Gson();
        get("/solutions/:solution_id", (req, res) -> {
            String solutionId = req.params(":solution_id");
            LOGGER.info("Received GET request for solution {}.", solutionId);

            TaskStatus status = statusService.get(solutionId);
            if(status.isNotStarted()){
                runnerService.submitTask(solutionId);
                status = new TaskStatus();
                status.setInProgress();
            }
            return status;
        }, gson::toJson);
    }
}