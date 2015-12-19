package pl.edu.agh.to.testerka;

import pl.edu.agh.to.testerka.sandbox.TestResult;

public interface SaveResultService {

    void save(TestResult result, String solutionId);
}
