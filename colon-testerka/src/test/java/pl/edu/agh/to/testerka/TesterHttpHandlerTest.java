package pl.edu.agh.to.testerka;


import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.to.testerka.sandbox.Sandbox;
import pl.edu.agh.to.testerka.services.RunnerService;
import pl.edu.agh.to.testerka.services.StatusService;

import static com.jayway.restassured.RestAssured.get;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static spark.SparkBase.setPort;

public class TesterHttpHandlerTest {

    private static final int PORT = 1111;
    private static final String HOST_ADDRESS = "http://localhost";
    private static final String FULL_ADDRESS = HOST_ADDRESS + ":" + PORT;

    private static RunnerService runnerServiceMock = mock(RunnerService.class);
    private static StatusService statusServiceMock = mock(StatusService.class);

    private static TesterHttpHandler testerHttpHandler = new TesterHttpHandler(runnerServiceMock, statusServiceMock);

    @BeforeClass
    public static void setUpClass() {
        setPort(PORT);
        testerHttpHandler.setupTestersAPI();
    }

    @Test
    public void testGetRequestOfTaskInProgress() throws Exception {
        int id = 1;
        when(runnerServiceMock.isInProgress(id)).thenReturn(true);

        Response resp = get(FULL_ADDRESS + "/solutions/" + id);

        assertThat(resp.asString()).isEqualTo("\"" + TaskStatus.IN_PROGRESS.toString() + "\"");
        assertThat(resp.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testGetRequestOfNotTestedTask() throws Exception {
        int id = 2;
        when(runnerServiceMock.isInProgress(id)).thenReturn(false);
        when(statusServiceMock.getStatusFor(id)).thenReturn(TaskStatus.NOT_TESTED);

        Response resp = get(FULL_ADDRESS + "/solutions/" + id);

        verify(runnerServiceMock, times(1)).submitTask(eq(id), any(Sandbox.class));

        assertThat(resp.asString()).isEqualTo("\"" + TaskStatus.IN_PROGRESS.toString() + "\"");
        assertThat(resp.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testGetRequestOfTestedTask() throws Exception {
        int id = 3;
        when(runnerServiceMock.isInProgress(id)).thenReturn(false);
        when(statusServiceMock.getStatusFor(id)).thenReturn(TaskStatus.TESTED);

        Response resp = get(FULL_ADDRESS + "/solutions/" + id);

        verify(runnerServiceMock, never()).submitTask(eq(id), any(Sandbox.class));

        assertThat(resp.asString()).isEqualTo("\"" + TaskStatus.TESTED.toString() + "\"");
        assertThat(resp.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testGetRequestOfNonExistingTask() throws Exception {
        int id = 3;
        when(runnerServiceMock.isInProgress(id)).thenReturn(false);
        when(statusServiceMock.getStatusFor(id)).thenReturn(TaskStatus.NON_EXISTING);

        Response resp = get(FULL_ADDRESS + "/solutions/" + id);

        verify(runnerServiceMock, never()).submitTask(eq(id), any(Sandbox.class));

        assertThat(resp.asString()).isEqualTo("\"" + TaskStatus.NON_EXISTING.toString() + "\"");
        assertThat(resp.getStatusCode()).isEqualTo(200);
    }
}
