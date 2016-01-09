package pl.edu.agh.to.testerka.services;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpFileProvider implements FileContentProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpFileProvider.class);

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
            LOGGER.error("Error while getting solution content.", e);
        }
        return fileContent;
    }

    @Override
    public String getInputFileContent(String solutionId) {
        String inputFile = "";
        try {
            inputFile = Unirest.get(host + "solution/" + solutionId + "/in").asJson().getBody().toString();
        } catch (UnirestException e) {
            LOGGER.error("Error while getting input file content.", e);
        }
        return inputFile;
    }

    @Override
    public String getOutputFileContent(String solutionId) {
        String outputFile = "";
        try {
            outputFile = Unirest.get(host + "solution/" + solutionId + "/out").asJson().getBody().toString();
        } catch (UnirestException e) {
            LOGGER.error("Error while getting output file content.", e);
        }
        return outputFile;
    }
}
