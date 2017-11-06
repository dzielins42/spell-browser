package pl.dzielins42.spellcontentprovider.descriptor;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class DescriptorJsonSerializer implements JsonSerializer<DescriptorBean> {

    @Override
    public JsonElement serialize(DescriptorBean src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        serializeId(src, typeOfSrc, context, object);
        serializeName(src, typeOfSrc, context, object);

        return object;
    }

    @NonNull
    private JsonElement serializeId(DescriptorBean src, Type typeOfSrc, JsonSerializationContext context, @NonNull JsonObject object) {
        return object;
    }

    @NonNull
    private JsonElement serializeName(DescriptorBean src, Type typeOfSrc, JsonSerializationContext context, @NonNull JsonObject object) {
        object.addProperty("name", src.getName());

        return object;
    }

}
