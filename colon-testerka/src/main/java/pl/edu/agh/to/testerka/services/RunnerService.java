package pl.edu.agh.to.testerka.services;

import org.eclipse.jetty.util.ConcurrentHashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.Sandbox;
import pl.edu.agh.to.testerka.sandbox.TestResult;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerService.class);

    private static final int THREADS_COUNT = 5;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
    Set<Integer> jobsInProgress = new ConcurrentHashSet<>();
    private SaveResultService resultService;
    private FileContentProvider contentProvider;

    public RunnerService(SaveResultService resultService, FileContentProvider contentProvider) {
        this.resultService = resultService;
        this.contentProvider = contentProvider;
    }

    public synchronized void submitTask(Integer solutionId, Sandbox sandbox) {
        jobsInProgress.add(solutionId);
        executor.submit(new RunnableSolution(solutionId, sandbox));
        LOGGER.info("Submitted job for solution {}.", solutionId);
    }

    public boolean isInProgress(Integer solutionId) {
        return jobsInProgress.contains(solutionId);
    }

    private class RunnableSolution implements Runnable {
        private final Integer solutionId;
        private final Sandbox sandbox;

        private RunnableSolution(Integer solutionId, Sandbox sandbox) {
            this.solutionId = solutionId;
            this.sandbox = sandbox;
        }

        @Override
        public void run() {
            LOGGER.info("Started runnable for solution {}.", solutionId);

            String fileContent = contentProvider.getSolutionContent(solutionId);
            LOGGER.info("File content for solution {}:\n{}", solutionId, fileContent);
            String inputFile = contentProvider.getInputFileContent(solutionId);
            LOGGER.info("Input file content for solution {}:\n{}", solutionId, inputFile);
            String outputFile = contentProvider.getOutputFileContent(solutionId);
            LOGGER.info("Output file content for solution {}:\n{}", solutionId, outputFile);

            LOGGER.info("Starting execution of solution {}.", solutionId);
            TestResult result = sandbox.execute(fileContent, inputFile, outputFile);
            LOGGER.info("Finished executing solution {}.", solutionId);

            resultService.save(result, solutionId);
            LOGGER.info("Saved result of solution {}: {} - {} ms.", solutionId, result.getTestResultStatus().name(), result.getExecutionTimeMillis());

            jobsInProgress.remove(solutionId);
            LOGGER.info("Finished job for solution {}.", solutionId);
        }
    }
}
