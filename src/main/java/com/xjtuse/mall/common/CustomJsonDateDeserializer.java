package com.xjtuse.mall.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {
    private String[] patterns = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        Date date1 = dateParse(date, patterns[0]);
        Date date2 = dateParse(date, patterns[1]);
        if(date1 != null){ return date1; }
        return date2;
    }

    private Date dateParse(String date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            parse = format.parse(date);
        } catch (ParseException e) {
            System.out.println("尝试解析日期");
        }
        return parse;
    }
}
