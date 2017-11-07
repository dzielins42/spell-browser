package pl.dzielins42.spellcontentprovider.descriptor;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class DescriptorJsonSerializer implements JsonSerializer<DescriptorBean> {

    @Override
    public JsonElement serialize(DescriptorBean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
