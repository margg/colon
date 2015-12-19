package pl.edu.agh.to.testerka;

import pl.edu.agh.to.testerka.serviceImpl.DBConnection;
import pl.edu.agh.to.testerka.serviceImpl.HttpFileProvider;
import pl.edu.agh.to.testerka.serviceImpl.JDBCSaveResultService;
import pl.edu.agh.to.testerka.serviceImpl.JDBCStatusService;

public class Runner {

    public static void main(String[] args) {

        JDBCSaveResultService jdbcSaveResultService = new JDBCSaveResultService(new DBConnection());
        FileContentProvider httpProvider = new HttpFileProvider("http://localhost:4567/mock/");
        RunnerService runnerService = new RunnerService(jdbcSaveResultService, httpProvider);
        StatusService statusService = new JDBCStatusService(new DBConnection());
        TesterAPIService testerAPIService = new TesterAPIService(runnerService, statusService);
        testerAPIService.setupTestersAPI();

        FilerMock filerMock = new FilerMock();
        filerMock.setupAPI();
        DBServiceMock dbServiceMock = new DBServiceMock();
        dbServiceMock.setupAPI();
    }
}