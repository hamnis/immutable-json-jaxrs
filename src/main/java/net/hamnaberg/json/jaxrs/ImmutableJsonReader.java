package net.hamnaberg.json.jaxrs;

import net.hamnaberg.json.Json;
import net.hamnaberg.json.jackson.JacksonStreamingParser;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ImmutableJsonReader implements MessageBodyReader<Json.JValue> {
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Json.JValue.class.isAssignableFrom(type) && mediaType.getSubtype().contains("json");
    }

    @Override
    public Json.JValue readFrom(Class<Json.JValue> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        return new JacksonStreamingParser().parse(entityStream);
    }
}
