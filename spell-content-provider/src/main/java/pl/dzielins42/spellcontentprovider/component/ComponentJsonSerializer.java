package pl.dzielins42.spellcontentprovider.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class ComponentJsonSerializer implements JsonSerializer<ComponentBean> {

    @Override
    public JsonElement serialize(ComponentBean src, Type typeOfSrc, JsonSerializationContext context) {
        if (StringUtils.isBlank(src.getExtra())) {
            return new JsonPrimitive(src.getName());
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", src.getName());
            jsonObject.addProperty("extra", src.getExtra());
            return jsonObject;
        }
    }

}
