package pl.edu.agh.to.testerka.services;

import pl.edu.agh.to.testerka.TaskStatus;

public interface StatusService {
    TaskStatus getStatusFor(Integer solutionId);
}
