package pl.dzielins42.spellcontentprovider.characterclass;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CharacterClassJsonSerializer implements JsonSerializer<CharacterClassBean>{

    @Override
    public JsonElement serialize(CharacterClassBean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
