package pl.edu.agh.to.testerka.services;

import org.eclipse.jetty.util.ConcurrentHashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.Sandbox;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerService.class);

    private static final int THREADS_COUNT = 5;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
    Set<String> jobsInProgress = new ConcurrentHashSet<>();
    private SaveResultService resultService;
    private FileContentProvider contentProvider;

    public RunnerService(SaveResultService resultService, FileContentProvider contentProvider) {
        this.resultService = resultService;
        this.contentProvider = contentProvider;
    }

    public void submitTask(String solutionId, Sandbox sandbox) {
        jobsInProgress.add(solutionId);
        executor.submit(new RunnableSolution(solutionId, sandbox));
        LOGGER.info("Submitted job for solution {}.", solutionId);
    }

    public boolean isInProgress(String solutionId) {
        return jobsInProgress.contains(solutionId);
    }

    private class RunnableSolution implements Runnable {
        private final String solutionId;
        private final Sandbox sandbox;

        private RunnableSolution(String solutionId, Sandbox sandbox) {
            this.solutionId = solutionId;
            this.sandbox = sandbox;
        }

        @Override
        public void run() {
            String fileContent = contentProvider.getSolutionContent(solutionId);
            String inputFile = contentProvider.getInputFileContent(solutionId);
            String outputFile = contentProvider.getOutputFileContent(solutionId);

            resultService.save(sandbox.execute(fileContent, inputFile, outputFile), solutionId);

            jobsInProgress.remove(solutionId);
            LOGGER.info("Finished job for solution {}.", solutionId);
        }
    }
}
