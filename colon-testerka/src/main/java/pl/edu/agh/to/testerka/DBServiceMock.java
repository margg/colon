package pl.edu.agh.to.testerka;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static spark.Spark.get;
import static spark.Spark.post;

public class DBServiceMock {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBServiceMock.class);

    public void setupAPI() {

        get("/mock/solutions/not_started", (req, res) -> {
            LOGGER.info("Received GET request for not started solutions.");

            List<String> objList = Lists.newArrayList("9", "8", "7", "6", "5");
            return new Gson().toJson(objList);
        });

        get("/mock/solutions/:solution_id/task_id", (req, res) -> {
            LOGGER.info("Received GET request for the taskID of solution {}.", req.params(":solution_id"));
            return "12345";
        });

        get("/mock/solutions/:solution_id/status", (req, res) -> {
            LOGGER.info("Received GET request for solution {} status.", req.params(":solution_id"));
            String[] list = {"NOT_TESTED", "QUEUED"};
            return list[new Random().nextInt(list.length)];
        });

        post("/mock/solutions/:solution_id/status", (req, res) -> {
            LOGGER.info("Received POST request for solution {} status.", req.params(":solution_id"));
            return "OK";
        });
    }
}
