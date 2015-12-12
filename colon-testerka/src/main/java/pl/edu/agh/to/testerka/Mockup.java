package pl.edu.agh.to.testerka;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static spark.Spark.get;
import static spark.Spark.post;

public class Mockup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mockup.class);

    public Mockup() {
    }

    public void createRestAPI() {

        get("/mock/files/:solution_id", (req, res) -> {
            LOGGER.info("Received GET request for file {}.", req.params(":solution_id"));
            res.type("text/plain");
            return "content";
        });

        get("/mock/solutions/not_started", (req, res) -> {
            LOGGER.info("Received GET request for not started solutions.");

            List<String> objList = Lists.newArrayList("9", "8", "7", "6", "5");
            return new Gson().toJson(objList);
        });

        get("/mock/solutions/:solution_id/status", (req, res) -> {
            LOGGER.info("Received GET request for solution {} status.", req.params(":solution_id"));
            String[] list = {"NOT_STARTED", "QUEUED"};
            Random r = new Random();
            return list[r.nextInt(list.length)];
        });

        post("/mock/solutions/:solution_id/status", (req, res) -> {
            LOGGER.info("Received POST request for solution {} status.", req.params(":solution_id"));
            return "OK";
        });
    }
}
