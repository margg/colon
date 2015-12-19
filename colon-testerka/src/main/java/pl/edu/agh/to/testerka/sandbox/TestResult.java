package pl.edu.agh.to.testerka.sandbox;

import com.google.gson.JsonObject;

public class TestResult {
    private TestResultStatus testResultStatus;
    private long executionTimeMillis;

    public TestResult(TestResultStatus testResultStatus) {
        this(testResultStatus, 0);
    }

    public TestResult(TestResultStatus testResultStatus, long executionTimeMillis) {
        this.testResultStatus = testResultStatus;
        this.executionTimeMillis = executionTimeMillis;
    }

    public TestResultStatus getTestResultStatus() {
        return testResultStatus;
    }

    public long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public JsonObject toJson() {
        JsonObject testResultJson = new JsonObject();
        testResultJson.addProperty("status", testResultStatus.toString());
        testResultJson.addProperty("time", executionTimeMillis);
        return testResultJson;
    }
}
