package pl.edu.agh.to.testerka;

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
    Set<String> tasksInProgress = new HashSet<>();

    public RunnerService() {
    }

    public void submitTask(String solutionId) {
        tasksInProgress.add(solutionId);
        executor.submit(new RunnableSolution(solutionId));
        LOGGER.info("Submitted task for solution {}.", solutionId);
    }

    public boolean isInProgress(String solutionId) {
        return tasksInProgress.contains(solutionId);
    }

    private class RunnableSolution implements Runnable {
        private final String solutionId;

        private RunnableSolution(String solutionId) {
            this.solutionId = solutionId;
        }

        @Override
        public void run() {
            // TODO: get content via Filer

            // TODO: run in sandbox
            // TODO: save output!

            // TODO: get expected output file via Filer
            // TODO: compare outputs

            // TODO: save result to DB

            tasksInProgress.remove(this.solutionId);
            LOGGER.info("Finished task for solution {}.", solutionId);
        }
    }
}
