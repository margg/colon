package pl.edu.agh.to.testerka.services;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.edu.agh.to.testerka.sandbox.Sandbox;
import pl.edu.agh.to.testerka.sandbox.TestResult;
import pl.edu.agh.to.testerka.sandbox.TestResultStatus;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunnerServiceStepDefinition {

    private RunnerService runnerService;

    @Given("^Runner Service is started$")
    public void runnerServiceIsStarted() throws Throwable {
        SaveResultService resultServiceMock = mock(SaveResultService.class);
        FileContentProvider fileContentProviderMock = mock(FileContentProvider.class);

        when(fileContentProviderMock.getInputFileContent(anyInt())).thenReturn("");
        when(fileContentProviderMock.getOutputFileContent(anyInt())).thenReturn("");
        when(fileContentProviderMock.getSolutionContent(anyInt())).thenReturn("");

        this.runnerService = new RunnerService(resultServiceMock, fileContentProviderMock);
    }

    @When("^I submit task of id (\\d+) to the Runner Service$")
    public void iSubmitTaskOfIdToTheRunnerService(int id) throws Throwable {
        Sandbox sandbox = mock(Sandbox.class);
        when(sandbox.execute("", "", "")).thenAnswer(invocation -> {
            Thread.sleep(100);
            return new TestResult(TestResultStatus.OK, 100L);
        });
        runnerService.submitTask(id, sandbox);
    }

    @Then("^Task (\\d+) should be in progress\\.$")
    public void taskShouldBeInProgress(int id) throws Throwable {
        assertTrue(runnerService.isInProgress(id));
    }
}
