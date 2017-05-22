package net.hamnaberg.json.jaxrs;

import net.hamnaberg.json.Json;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Provider
public class ImmutableJsonWriter implements MessageBodyWriter<Json.JValue> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Json.JValue.class.isAssignableFrom(type) && mediaType.getSubtype().contains("json");
    }

    @Override
    public long getSize(Json.JValue jValue, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Json.JValue jValue, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(entityStream, StandardCharsets.UTF_8));
        writer.write(jValue.nospaces());
        writer.flush();
    }
}
