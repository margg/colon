package pl.edu.agh.to.testerka;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;

public class RequestProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProvider.class);

    public RequestProvider() {
    }

    public void gets() {
        get("/solutions/:solution_id", (req, res) -> {
            LOGGER.info("Received GET request for solution {}.", req.params(":solution_id"));
            JsonObject json = new JsonObject();
            json.addProperty("status", "OK");
            return json.toString();
        });
    }
}
