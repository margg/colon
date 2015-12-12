package pl.edu.agh.to.testerka;

public class StatusService {

    private final RunnerService runnerService;

    public StatusService(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    public TaskStatus get(String solutionId) {
        TaskStatus status = new TaskStatus();
        if (runnerService.isInProgress(solutionId)){
            status.setInProgress();
        } else {
            // TODO: send GET to StatusService in DB to check status
        }
        return status;
    }
}
