package com.example.encrypt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProtectDataSerializer extends JsonSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
//        String pii = value.toString().replaceAll("\\w(?=\\w{4})", "x");
        String pii = mask(value.toString());
        gen.writeString(pii);
    }

    private static String mask(String input) {
        int maskLen = input.length() - 4;
        if (maskLen <= 0)
            return input;

        return "X".repeat(maskLen) + input.substring(maskLen);
    }
}