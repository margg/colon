package pl.edu.agh.to.testerka.services;

public interface FileContentProvider {

    String getSolutionContent(String solutionId);

    String getInputFileContent(String solutionId);

    String getOutputFileContent(String solutionId);
}
