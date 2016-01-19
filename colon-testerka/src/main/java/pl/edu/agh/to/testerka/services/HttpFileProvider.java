package pl.edu.agh.to.testerka.services;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpFileProvider implements FileContentProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpFileProvider.class);

    private String host;

    public HttpFileProvider(String host) {
        this.host = host;
    }

    @Override
    public synchronized String getSolutionContent(Integer solutionId) {
        String fileContent = "";
        String url = "http://" + host + "/solutions/" + solutionId;
        LOGGER.info("Getting content from {}...", url);
        try {
            fileContent = Unirest.get(url).asString().getBody();
        } catch (UnirestException e) {
            LOGGER.error("Error while getting file content.", e);
        }
        return fileContent;
    }

    @Override
    public synchronized String getInputFileContent(Integer solutionId) {
        String inputFile = "";
        String url = "http://" + host + "/solutions/" + solutionId + "/in";
        try {
            inputFile = Unirest.get(url).asString().getBody();
        } catch (UnirestException e) {
            LOGGER.error("Error while getting input file content.", e);
        }
        return inputFile;
    }

    @Override
    public synchronized String getOutputFileContent(Integer solutionId) {
        String outputFile = "";
        String url = "http://" + host + "/solutions/" + solutionId + "/out";
        try {
            outputFile = Unirest.get(url).asString().getBody();
        } catch (UnirestException e) {
            LOGGER.error("Error while getting output file content.", e);
        }
        return outputFile;
    }
}
