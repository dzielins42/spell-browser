package pl.dzielins42.spellcontentprovider.school;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SchoolJsonSerializer implements JsonSerializer<SchoolBean> {

    @Override
    public JsonElement serialize(SchoolBean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
