package pl.dzielins42.spellcontentprovider.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ComponentJsonSerializer implements JsonSerializer<ComponentBean>{

    @Override
    public JsonElement serialize(ComponentBean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
