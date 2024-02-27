package org.example.util.error;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent // serializer를 objectmapper에 등록한다
public class ErrorSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        value.getFieldErrors().forEach(e->{
            try{
                gen.writeStartObject();
                gen.writeStringField("cause", e.getField());
//                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("message", e.getDefaultMessage());
            } catch (IOException e1){
                e1.printStackTrace();
            }
        });
        value.getGlobalErrors().forEach(e->{
            try{
                gen.writeStartObject();
                gen.writeStringField("cause", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("message", e.getDefaultMessage());
            }catch (IOException e2){
                e2.printStackTrace();
            }
        });
        gen.writeEndArray();
    }
}
//https://blog.junu.dev/20 참고
//https://github.com/Be-poz/TIL/blob/master/Spring/ErrorSerializer%EC%97%90%20%EB%8C%80%ED%95%B4.md 참고
