package pl.edu.agh.to.testerka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;

public class FileProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileProvider.class);


    public FileProvider() {
    }

    public void gets() {
        get("/mock/files/:solution_id", (req, res) -> {
            LOGGER.info("Received GET request for file {}.", req.params(":solution_id"));
            res.type("text/plain");
            return "content";
        });
    }
}
