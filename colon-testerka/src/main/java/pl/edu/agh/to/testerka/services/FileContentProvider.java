package pl.edu.agh.to.testerka.services;

public interface FileContentProvider {

    String getSolutionContent(Integer solutionId);

    String getInputFileContent(Integer solutionId);

    String getOutputFileContent(Integer solutionId);
}
