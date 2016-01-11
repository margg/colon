package pl.edu.agh.to.app.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.edu.agh.to.app.model.Group;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by sjchmiela on 11.01.2016.
 */
public class GroupDateSerializer extends JsonSerializer<Map<Group, Date>> {

    @Override
    public void serialize(Map<Group, Date> groupDateMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray(); // [
        for(Map.Entry<Group, Date> entry : groupDateMap.entrySet()) {
            jsonGenerator.writeStartObject(); // {
                jsonGenerator.writeNumberField("group", entry.getKey().getId());
                jsonGenerator.writeObjectField("due_date", entry.getValue());
            jsonGenerator.writeEndObject(); // }
        }
        jsonGenerator.writeEndArray(); // ]
    }
}
