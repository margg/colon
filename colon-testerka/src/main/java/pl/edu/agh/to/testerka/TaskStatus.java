package pl.edu.agh.to.testerka;

public class TaskStatus {

    private Status status;

    public TaskStatus() {
        this.status = Status.NOT_STARTED;
    }

    private enum Status {
        NOT_STARTED,
        IN_PROGRESS
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setInProgress() {
        status = Status.IN_PROGRESS;
    }

    public boolean isNotStarted() {
        return status == Status.NOT_STARTED;
    }
}
