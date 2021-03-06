package pl.edu.agh.to.testerka.services;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.to.testerka.sandbox.JythonSandbox;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TesterScheduledService extends AbstractScheduledService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterScheduledService.class);

    private SolutionProvider solutionProvider;
    private RunnerService runnerService;
    private int period;
    private TimeUnit timeUnit;

    public TesterScheduledService(SolutionProvider solutionProvider, RunnerService runnerService, int period,
                                  TimeUnit timeUnit) {
        this.solutionProvider = solutionProvider;
        this.runnerService = runnerService;
        this.period = period;
        this.timeUnit = timeUnit;
    }

    @Override
    protected void runOneIteration() throws Exception {
        LOGGER.info("Running scheduled testing...");
        runUntestedSolutions();
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, period, timeUnit);
    }

    private void runUntestedSolutions() {
        List<Integer> untestedSolutions = getUntestedSolutions();
        LOGGER.info("Running scheduled testing for solutions: " + untestedSolutions.toString());
        for (Integer solutionId : untestedSolutions) {
            runnerService.submitTask(solutionId, new JythonSandbox());
        }
    }

    private List<Integer> getUntestedSolutions() {
        return solutionProvider.getUntestedSolutions();
    }
}
