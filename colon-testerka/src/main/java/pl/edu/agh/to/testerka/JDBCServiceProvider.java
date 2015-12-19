package pl.edu.agh.to.testerka;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import pl.edu.agh.to.testerka.sandbox.TestResult;

public class JDBCServiceProvider implements FileContentProvider, SaveResultService {

    @Override
    public String getSolutionContent(String solutionId) {
        String fileContent = "";
        try {
            fileContent = Unirest.get("http://localhost:4567/mock/files/" + solutionId).asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    @Override
    public String getInputFileContent(String solutionId) {
        String inputFile = "";
        try {
            inputFile = Unirest.get("http://localhost:4567/mock/solution/" + solutionId + "/in").asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return inputFile;
    }

    @Override
    public String getOutputFileContent(String solutionId) {
        String outputFile = "";
        try {
            outputFile = Unirest.get("http://localhost:4567/mock/solution/" + solutionId + "/out").asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    @Override
    public void save(TestResult result, String solutionId) {
        Unirest.post("http://localhost:4567/mock/solutions/" + solutionId + "/result").field("result", result.toJson());
    }
}
