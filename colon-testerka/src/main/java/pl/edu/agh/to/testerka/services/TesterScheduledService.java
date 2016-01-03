package pl.edu.agh.to.testerka.services;

import com.google.common.util.concurrent.AbstractScheduledService;
import pl.edu.agh.to.testerka.sandbox.JythonSandbox;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TesterScheduledService extends AbstractScheduledService {

    private SolutionProvider solutionProvider;
    private RunnerService runnerService;

    public TesterScheduledService(SolutionProvider solutionProvider, RunnerService runnerService) {
        this.solutionProvider = solutionProvider;
        this.runnerService = runnerService;
    }

    @Override
    protected void runOneIteration() throws Exception {
        runUntestedSolutions();
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 30, TimeUnit.MINUTES);
    }

    private void runUntestedSolutions() {
        List<Integer> untestedSolutions = getUntestedSolutions();
        for (Integer solutionId : untestedSolutions) {
            runnerService.submitTask(solutionId, new JythonSandbox());
        }
    }

    private List<Integer> getUntestedSolutions() {
        return solutionProvider.getUntestedSolutions();
    }
}
