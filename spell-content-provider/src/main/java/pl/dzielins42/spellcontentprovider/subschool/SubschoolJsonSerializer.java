package pl.dzielins42.spellcontentprovider.subschool;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SubschoolJsonSerializer implements JsonSerializer<SubschoolBean> {

    @Override
    public JsonElement serialize(SubschoolBean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
