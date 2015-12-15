package pl.edu.agh.to.testerka.sandbox;

public enum TestResultStatus {
    OK("OK"),
    TimeLimitExceeded("TLE"),
    Answer("ANS"),
    RuntimeError("RTE");


    private final String status;

    TestResultStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
