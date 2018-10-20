package br.com.acoaapi.helper;

import br.com.acoaapi.model.entity.Account;
import br.com.acoaapi.model.entity.Device;
import br.com.acoaapi.model.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class AccountSerializerHelper extends StdSerializer<Account> {

    protected AccountSerializerHelper(Class<Account> t) {
        super(t);
    }

    public AccountSerializerHelper() {
        this(null);
    }

    @Override
    public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", account.getId());
        jsonGenerator.writeNumberField("price", account.getPrice());

        jsonGenerator.writeArrayFieldStart("deviceList");
        List<Device> deviceList = account.getDeviceList();
        for (Device device : deviceList) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", device.getId());
            jsonGenerator.writeStringField("deviceId", device.getDeviceId());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("userList");
        List<User> userList = account.getUserList();
        for (User user : userList) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", user.getId());
            jsonGenerator.writeStringField("name", user.getUsername());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
