package pl.edu.agh.to.testerka.serviceImpl;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import pl.edu.agh.to.testerka.FileContentProvider;

public class HttpFileProvider implements FileContentProvider {

    private String host;

    public HttpFileProvider(String host) {
        this.host = host;
    }

    @Override
    public String getSolutionContent(String solutionId) {
        String fileContent = "";
        try {
            fileContent = Unirest.get(host + "files/" + solutionId).asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    @Override
    public String getInputFileContent(String solutionId) {
        String inputFile = "";
        try {
            inputFile = Unirest.get(host + "solution/" + solutionId + "/in").asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return inputFile;
    }

    @Override
    public String getOutputFileContent(String solutionId) {
        String outputFile = "";
        try {
            outputFile = Unirest.get(host + "solution/" + solutionId + "/out").asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return outputFile;
    }
}
