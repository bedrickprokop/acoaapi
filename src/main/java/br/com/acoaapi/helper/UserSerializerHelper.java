package br.com.acoaapi.helper;

import br.com.acoaapi.model.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UserSerializerHelper extends StdSerializer<User> {

    public UserSerializerHelper() {
        this(null);
    }

    public UserSerializerHelper(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("name", user.getUsername());

        jsonGenerator.writeObjectFieldStart("account");
        jsonGenerator.writeNumberField("id", user.getAccount().getId());
        jsonGenerator.writeNumberField("price", user.getAccount().getPrice());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
