package pl.edu.agh.to.testerka;

import pl.edu.agh.to.testerka.services.*;

public class Runner {

    public static void main(String[] args) {

        JDBCSaveResultService jdbcSaveResultService = new JDBCSaveResultService(new DBConnection());
        // TODO: provide host address
        FileContentProvider httpProvider = new HttpFileProvider("http://localhost:4567/mock/");
        RunnerService runnerService = new RunnerService(jdbcSaveResultService, httpProvider);
        StatusService statusService = new JDBCStatusService(new DBConnection());
        TesterHttpHandler testerHttpHandler = new TesterHttpHandler(runnerService, statusService);
        testerHttpHandler.setupTestersAPI();

        FilerMock filerMock = new FilerMock();
        filerMock.setupAPI();
        DBServiceMock dbServiceMock = new DBServiceMock();
        dbServiceMock.setupAPI();
    }
}