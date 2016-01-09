package pl.edu.agh.to.testerka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;

public class FilerMock {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilerMock.class);

    public void setupAPI() {

        get("/mock/solutions/:solution_id", (req, res) -> {
            LOGGER.info("Received GET request for file {}.", req.params(":solution_id"));
            res.type("text/plain");
            return "This is the file content.";
        });

        get("/mock/tasks/:task_id/in", (req, res) -> {
            LOGGER.info("Received GET request for input file for task {}.", req.params(":task_id"));
            res.type("text/plain");
            return "This is the input file content, against which the file will be tested. (Task INPUT file)";
        });

        get("/mock/tasks/:task_id/out", (req, res) -> {
            LOGGER.info("Received GET request for output file for task {}.", req.params(":task_id"));
            res.type("text/plain");
            return "This is the output file content. (Task OUTPUT file)";
        });
    }
}
