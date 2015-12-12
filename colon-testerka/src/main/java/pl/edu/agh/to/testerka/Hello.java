package pl.edu.agh.to.testerka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;

public class Hello {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    public static void main(String[] args) {
        get("/", (req, res) -> {
            LOGGER.info("Received GET request.");
            return "hello from sparkjava.com";
        });
    }
}