package com.ChangeBUG.model.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CustomDateTineSerialize extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/ShangHai"));
        gen.writeString(sdf.format(value));
    }

}
