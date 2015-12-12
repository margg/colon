package pl.edu.agh.to.testerka;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerService.class);

    private static final int THREADS_COUNT = 5;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
    Set<String> jobsInProgress = new HashSet<>();

    public RunnerService() {
    }

    public void submitTask(String solutionId, String taskId) {
        jobsInProgress.add(solutionId);
        executor.submit(new RunnableSolution(solutionId, taskId));
        LOGGER.info("Submitted job for solution {}.", solutionId);
    }

    public boolean isInProgress(String solutionId) {
        return jobsInProgress.contains(solutionId);
    }

    private class RunnableSolution implements Runnable {
        private final String solutionId;
        private String taskId;

        private RunnableSolution(String solutionId, String taskId) {
            this.solutionId = solutionId;
            this.taskId = taskId;
        }

        @Override
        public void run() {
            // TODO: get file contents via Filer
            String fileContent = "";
            String inputFile = "";
            String outputFile = "";
            try {
                fileContent = Unirest.get("http://localhost:4567/mock/files/" + solutionId).asJson().getBody().toString();
                inputFile = Unirest.get("http://localhost:4567/mock/tasks/" + taskId + "/in").asJson().getBody().toString();
                outputFile = Unirest.get("http://localhost:4567/mock/tasks/" + taskId + "/out").asJson().getBody().toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

//             TODO: run in sandbox
//             TODO: compare outputs, return result
//            TestResult result = mockSandbox.execute(fileContent, inputFile, outputFile);

//             TODO: save result to DB
//            Unirest.post("http://localhost:4567/mock/solutions/" + solutionId + "/result").field("result", result.toJson());

            jobsInProgress.remove(this.solutionId);
            LOGGER.info("Finished job for solution {}.", solutionId);
        }
    }
}
