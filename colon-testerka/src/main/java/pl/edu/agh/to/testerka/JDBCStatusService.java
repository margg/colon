package pl.edu.agh.to.testerka;

public class JDBCStatusService implements StatusService {

    @Override
    public TaskStatus getStatusFor(String solutionId) {
        TaskStatus status = new TaskStatus();
        // TODO: connect to DB to check status
        // TODO: convert DB status to TaskStatus
        status.setInProgress();
        return status;
    }
}
