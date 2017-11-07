package pl.dzielins42.spellcontentprovider.rulebook;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class RulebookJsonSerializer implements JsonSerializer<RulebookBean> {

    @Override
    public JsonElement serialize(RulebookBean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
