package pl.edu.agh.to.app.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.edu.agh.to.app.model.Solution;

import java.io.IOException;


public class SolutionSerializer extends JsonSerializer<Solution> {
    @Override
    public void serialize(Solution solution, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", solution.getId());
        jsonGenerator.writeNumberField("execTime", solution.getExecTime());
        jsonGenerator.writeStringField("status", solution.getStatus());
        jsonGenerator.writeObjectField("author", solution.getAuthor());
        jsonGenerator.writeNumberField("rank", solution.getRankPosition());
        jsonGenerator.writeEndObject();
    }
}