package pl.dzielins42.spellcontentprovider.spell;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassWithLevelBean;
import pl.dzielins42.spellcontentprovider.component.ComponentWithExtraBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;

public class SpellJsonSerializerDeserializer
        implements JsonSerializer<SpellBean>, JsonDeserializer<SpellBean> {

    private static final String NAME = "name";
    private static final String RULEBOOK = "rulebook";
    private static final String PAGE = "page";
    private static final String CASTING_TIME = "casting_time";
    private static final String RANGE = "range";
    private static final String TARGET = "target";
    private static final String EFFECT = "effect";
    private static final String AREA = "area";
    private static final String DURATION = "duration";
    private static final String SAVING_THROW = "saving_throw";
    private static final String SPELL_RESISTANCE = "spell_resistance";
    private static final String DESCRIPTION_PLAIN = "description_plain";
    private static final String DESCRIPTION_FORMATTED = "description_formatted";
    private static final String SHORT_DESCRIPTION_PLAIN = "short_description_plain";
    private static final String SHORT_DESCRIPTION_FORMATTED = "short_description_formatted";
    private static final String FLAVOUR_TEXT_PLAIN = "flavour_text_plain";
    private static final String FLAVOUR_TEXT_FORMATTED = "flavour_text_formatted";
    private static final String IS_RITUAL = "is_ritual";
    private static final String CLASSES = "classes";
    private static final String SCHOOL = "school";
    private static final String SUBSCHOOL = "subschool";
    private static final String CLASS = "class";
    private static final String LEVEL = "level";
    private static final String EXTRA = "extra";
    private static final String COMPONENTS = "components";
    private static final String DESCRIPTORS = "descriptors";

    @Override
    public SpellBean deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        SpellBean.SpellBeanBuilder builder = SpellBean.builder()
                .name(jsonObject.has(NAME) ? jsonObject.get(NAME).getAsString() : null)
                .rulebookId(0L)
                .rulebookName(jsonObject.has(RULEBOOK) ? jsonObject.get(RULEBOOK).getAsString() : null)
                .page(jsonObject.get(PAGE).getAsInt())
                .castingTime(jsonObject.has(CASTING_TIME) ? jsonObject.get(CASTING_TIME).getAsString() : null)
                .range(jsonObject.has(RANGE) ? jsonObject.get(RANGE).getAsString() : null)
                .target(jsonObject.has(TARGET) ? jsonObject.get(TARGET).getAsString() : null)
                .effect(jsonObject.has(EFFECT) ? jsonObject.get(EFFECT).getAsString() : null)
                .area(jsonObject.has(AREA) ? jsonObject.get(AREA).getAsString() : null)
                .duration(jsonObject.has(DURATION) ? jsonObject.get(DURATION).getAsString() : null)
                .savingThrow(jsonObject.has(SAVING_THROW) ? jsonObject.get(SAVING_THROW).getAsString() : null)
                .spellResistance(jsonObject.has(SPELL_RESISTANCE) ? jsonObject.get(SPELL_RESISTANCE).getAsString() : null)
                .descriptionPlain(jsonObject.has(DESCRIPTION_PLAIN) ? jsonObject.get(DESCRIPTION_PLAIN).getAsString() : null)
                .descriptionFormatted(jsonObject.has(DESCRIPTION_FORMATTED) ? jsonObject.get(DESCRIPTION_FORMATTED).getAsString() : null)
                .shortDescriptionPlain(jsonObject.has(SHORT_DESCRIPTION_PLAIN) ? jsonObject.get(SHORT_DESCRIPTION_PLAIN).getAsString() : null)
                .shortDescriptionFormatted(jsonObject.has(SHORT_DESCRIPTION_FORMATTED) ? jsonObject.get(SHORT_DESCRIPTION_FORMATTED).getAsString() : null)
                .flavourTextPlain(jsonObject.has(FLAVOUR_TEXT_PLAIN) ? jsonObject.get(FLAVOUR_TEXT_PLAIN).getAsString() : null)
                .flavourTextFormatted(jsonObject.has(FLAVOUR_TEXT_FORMATTED) ? jsonObject.get(FLAVOUR_TEXT_FORMATTED).getAsString() : null)
                .isRitual(jsonObject.get(IS_RITUAL).getAsBoolean())
                .schoolId(0L)
                .schoolMainTypeId(0L)
                .schoolMainTypeName(jsonObject.has(SCHOOL) ? jsonObject.get(SCHOOL).getAsString() : null)
                .schoolSubTypeId(0L)
                .schoolSubTypeName(jsonObject.has(SUBSCHOOL) ? jsonObject.get(SUBSCHOOL).getAsString() : null);

        JsonArray jsonArray;

        List<CharacterClassWithLevelBean> classes = new ArrayList<>();
        jsonArray = jsonObject.get(CLASSES).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            classes.add(deserializeClass(jsonArray.get(i)));
        }
        builder.characterClasses(classes);

        List<ComponentWithExtraBean> components = new ArrayList<>();
        jsonArray = jsonObject.get(COMPONENTS).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            components.add(deserializeComponent(jsonArray.get(i)));
        }
        builder.components(components);

        List<DescriptorBean> descriptors = new ArrayList<>();
        jsonArray = jsonObject.get(DESCRIPTORS).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            descriptors.add(deserializeDescriptor(jsonArray.get(i)));
        }
        builder.descriptors(descriptors);

        return builder.build();
    }

    @Override
    public JsonElement serialize(
            SpellBean src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(NAME, src.getName());
        jsonObject.addProperty(RULEBOOK, src.getRulebookName());
        jsonObject.addProperty(PAGE, src.getPage());
        jsonObject.addProperty(CASTING_TIME, src.getCastingTime());
        jsonObject.addProperty(RANGE, src.getRange());
        jsonObject.addProperty(TARGET, src.getTarget());
        jsonObject.addProperty(EFFECT, src.getEffect());
        jsonObject.addProperty(AREA, src.getArea());
        jsonObject.addProperty(DURATION, src.getDuration());
        jsonObject.addProperty(SAVING_THROW, src.getSavingThrow());
        jsonObject.addProperty(SPELL_RESISTANCE, src.getSpellResistance());
        jsonObject.addProperty(DESCRIPTION_PLAIN, src.getDescriptionPlain());
        jsonObject.addProperty(DESCRIPTION_FORMATTED, src.getDescriptionFormatted());
        jsonObject.addProperty(SHORT_DESCRIPTION_PLAIN, src.getShortDescriptionPlain());
        jsonObject.addProperty(SHORT_DESCRIPTION_FORMATTED, src.getShortDescriptionFormatted());
        jsonObject.addProperty(FLAVOUR_TEXT_PLAIN, src.getFlavourTextPlain());
        jsonObject.addProperty(FLAVOUR_TEXT_FORMATTED, src.getFlavourTextFormatted());
        jsonObject.addProperty(IS_RITUAL, src.isRitual());
        jsonObject.addProperty(SCHOOL, src.getSchoolMainTypeName());
        jsonObject.addProperty(SUBSCHOOL, src.getSchoolSubTypeName());

        JsonArray jsonArray;

        if (src.getCharacterClasses() != null
                && !src.getCharacterClasses().isEmpty()) {
            jsonArray = new JsonArray();
            for (CharacterClassWithLevelBean ccwlBean : src.getCharacterClasses()) {
                jsonArray.add(serializeClass(ccwlBean));
            }
            jsonObject.add(CLASSES, jsonArray);
        } else {
            jsonObject.add(CLASSES, new JsonArray());
        }

        if (src.getComponents() != null && !src.getComponents().isEmpty()) {
            jsonArray = new JsonArray();
            for (ComponentWithExtraBean cweBean : src.getComponents()) {
                jsonArray.add(serializeComponent(cweBean));
            }
            jsonObject.add(COMPONENTS, jsonArray);
        } else {
            jsonObject.add(COMPONENTS, new JsonArray());
        }

        if (src.getDescriptors() != null && !src.getDescriptors().isEmpty()) {
            jsonArray = new JsonArray();
            for (DescriptorBean dBean : src.getDescriptors()) {
                jsonArray.add(serializeDescriptor(dBean));
            }
            jsonObject.add(DESCRIPTORS, jsonArray);
        } else {
            jsonObject.add(DESCRIPTORS, new JsonArray());
        }

        return jsonObject;
    }

    private CharacterClassWithLevelBean deserializeClass(JsonElement json) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return CharacterClassWithLevelBean.newInstance(
                0L,
                jsonObject.get(CLASS).getAsString(),
                jsonObject.get(LEVEL).getAsInt(),
                jsonObject.has(EXTRA) ? jsonObject.get(EXTRA).getAsString() : null
        );
    }

    private JsonElement serializeClass(CharacterClassWithLevelBean src) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(CLASS, src.getName());
        jsonObject.addProperty(LEVEL, src.getLevel());
        jsonObject.addProperty(EXTRA, src.getExtra());

        return jsonObject;
    }

    private ComponentWithExtraBean deserializeComponent(JsonElement json) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return ComponentWithExtraBean.newInstance(0L, json.getAsString(), null);
        } else {
            JsonObject jsonObject = json.getAsJsonObject();
            return ComponentWithExtraBean.newInstance(
                    0L, jsonObject.get(NAME).getAsString(), jsonObject.get(EXTRA).getAsString()
            );
        }
    }

    private JsonElement serializeComponent(ComponentWithExtraBean src) {
        if (StringUtils.isBlank(src.getExtra())) {
            return new JsonPrimitive(src.getName());
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(NAME, src.getName());
            jsonObject.addProperty(EXTRA, src.getExtra());
            return jsonObject;
        }
    }

    private DescriptorBean deserializeDescriptor(JsonElement json) throws JsonParseException {
        return DescriptorBean.newInstance(0L, json.getAsString());
    }

    private JsonElement serializeDescriptor(DescriptorBean src) {
        return new JsonPrimitive(src.getName());
    }
}
