package pl.edu.agh.to.testerka;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;

public class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private static RunnerService runnerService = new RunnerService();
    private static StatusService statusService = new StatusService(runnerService);

    public static void main(String[] args) {

        setupTestersAPI();
        FilerMock filerMock = new FilerMock();
        filerMock.setupAPI();
        DBServiceMock dbServiceMock = new DBServiceMock();
        dbServiceMock.setupAPI();

    }

    private static void setupTestersAPI() {
        Gson gson = new Gson();
        get("/solutions/:solution_id", (req, res) -> {
            String solutionId = req.params(":solution_id");
            LOGGER.info("Received GET request for solution {}.", solutionId);

            TaskStatus status = statusService.get(solutionId);
            if (status.isNotStarted()) {
                String taskId = "";
                try {
                    taskId = Unirest.get("http://localhost:4567/solutions/" + solutionId + "/task_id").asJson().getBody().toString();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                runnerService.submitTask(solutionId, taskId);
                status = new TaskStatus();
                status.setInProgress();
            }
            return status;
        }, gson::toJson);
    }
}