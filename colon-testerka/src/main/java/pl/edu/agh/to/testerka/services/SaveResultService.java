package pl.edu.agh.to.testerka.services;

import pl.edu.agh.to.testerka.sandbox.TestResult;

public interface SaveResultService {

    void save(TestResult result, Integer solutionId);
}
