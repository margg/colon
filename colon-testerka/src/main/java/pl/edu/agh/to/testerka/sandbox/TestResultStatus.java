package pl.edu.agh.to.testerka.sandbox;

public enum TestResultStatus {
    NOT_TESTED("NOT"),
    OK("OK"),
    TIME_LIMIT_EXCEEDED("TLE"),
    ANSWER("ANS"),
    RUNTIME_ERROR("RTE"),
    REJECTED("REJ");

    private final String status;

    TestResultStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
