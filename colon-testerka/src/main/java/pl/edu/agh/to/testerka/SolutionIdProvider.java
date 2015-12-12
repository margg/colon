package pl.edu.agh.to.testerka;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static spark.Spark.get;

public class SolutionIdProvider {
    public SolutionIdProvider() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SolutionIdProvider.class);


    public void gets() {
        get("/mock/solutions/not_started", (req, res) -> {
            LOGGER.info("Received GET request for not started solutions.");

        List<String> objList = Lists.newArrayList("9","8","7","6", "5");
            return new Gson().toJson(objList);
        });

        get("/mock/solutions/:solution_id/status", (req, res) -> {
            LOGGER.info("Received GET request for solution {} status.", req.params(":solution_id"));
            String[] list = {"NOT_STARTED", "QUEUED", "IN_PROGRESS"};
            Random r = new Random();
            return list[r.nextInt(list.length)];
        });
    }
}
