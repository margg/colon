package pl.edu.agh.to.testerka;

public class Runner {

    public static void main(String[] args) {

        JDBCServiceProvider jdbcServiceProvider = new JDBCServiceProvider();
        RunnerService runnerService = new RunnerService(jdbcServiceProvider, jdbcServiceProvider);
        StatusService statusService = new JDBCStatusService();
        TesterAPIService testerAPIService = new TesterAPIService(runnerService, statusService);
        testerAPIService.setupTestersAPI();

        FilerMock filerMock = new FilerMock();
        filerMock.setupAPI();
        DBServiceMock dbServiceMock = new DBServiceMock();
        dbServiceMock.setupAPI();
    }
}