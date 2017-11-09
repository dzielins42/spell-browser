package pl.dzielins42.spellcontentprovider.spell.composite;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;

public class CompositeSpellJsonSerializerDeserializer
        implements JsonSerializer<CompositeSpellBean>, JsonDeserializer<CompositeSpellBean> {

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
    private static final String SCHOOLS = "schools";
    private static final String SCHOOL = "school";
    private static final String SUBSCHOOL = "subschool";
    private static final String CLASS = "class";
    private static final String LEVEL = "level";
    private static final String EXTRA = "extra";
    private static final String COMPONENTS = "components";
    private static final String DESCRIPTORS = "descriptors";

    @Override
    public CompositeSpellBean deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        CompositeSpellBean bean = new CompositeSpellBean();

        bean.setName(jsonObject.get(NAME).getAsString());
        bean.setRulebook(deserializeRulebook(jsonObject.get(RULEBOOK)));
        bean.setPage(jsonObject.get(PAGE).getAsInt());
        bean.setCastingTime(jsonObject.get(CASTING_TIME).getAsString());
        bean.setRange(jsonObject.get(RANGE).getAsString());
        bean.setTarget(jsonObject.get(TARGET).getAsString());
        bean.setEffect(jsonObject.get(EFFECT).getAsString());
        bean.setArea(jsonObject.get(AREA).getAsString());
        bean.setDuration(jsonObject.get(DURATION).getAsString());
        bean.setSavingThrow(jsonObject.get(SAVING_THROW).getAsString());
        bean.setSpellResistance(jsonObject.get(SPELL_RESISTANCE).getAsString());
        bean.setDescriptionPlain(jsonObject.get(DESCRIPTION_PLAIN).getAsString());
        bean.setDescriptionFormatted(jsonObject.get(DESCRIPTION_FORMATTED).getAsString());
        bean.setShortDescriptionPlain(jsonObject.get(SHORT_DESCRIPTION_PLAIN).getAsString());
        bean.setShortDescriptionFormatted(jsonObject.get(SHORT_DESCRIPTION_FORMATTED).getAsString());
        bean.setFlavourTextPlain(jsonObject.get(FLAVOUR_TEXT_PLAIN).getAsString());
        bean.setFlavourTextFormatted(jsonObject.get(FLAVOUR_TEXT_FORMATTED).getAsString());
        bean.setIsRitual(jsonObject.get(IS_RITUAL).getAsBoolean());

        JsonArray jsonArray;

        List<CharacterClassLevelExtraBean> classes = new ArrayList<>();
        jsonArray = jsonObject.get(CLASSES).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            classes.add(deserializeClass(jsonArray.get(i)));
        }
        bean.setClassesLevels(classes);

        List<SchoolSubschoolBean> schools = new ArrayList<>();
        jsonArray = jsonObject.get(SCHOOLS).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            schools.add(deserializeSchool(jsonArray.get(i)));
        }
        bean.setSchools(schools);

        List<ComponentBean> components = new ArrayList<>();
        jsonArray = jsonObject.get(COMPONENTS).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            components.add(deserializeComponent(jsonArray.get(i)));
        }
        bean.setComponents(components);

        List<DescriptorBean> descriptors = new ArrayList<>();
        jsonArray = jsonObject.get(DESCRIPTORS).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            descriptors.add(deserializeDescriptor(jsonArray.get(i)));
        }
        bean.setDescriptors(descriptors);

        return bean;
    }

    @Override
    public JsonElement serialize(
            CompositeSpellBean src, Type typeOfSrc, JsonSerializationContext context
    ) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(NAME, src.getName());
        jsonObject.add(RULEBOOK, serializeRulebook(src.getRulebook()));
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
        jsonObject.addProperty(IS_RITUAL, src.getIsRitual());

        JsonArray jsonArray;

        if (src.getClassesLevels() != null && !src.getClassesLevels().isEmpty()) {
            jsonArray = new JsonArray();
            for (CharacterClassLevelExtraBean ccleBean : src.getClassesLevels()) {
                jsonArray.add(serializeClass(ccleBean));
            }
            jsonObject.add(CLASSES, jsonArray);
        } else {
            jsonObject.add(CLASSES, new JsonArray());
        }

        if (src.getSchools() != null && !src.getSchools().isEmpty()) {
            jsonArray = new JsonArray();
            for (SchoolSubschoolBean ssBean : src.getSchools()) {
                jsonArray.add(serializeSchool(ssBean));
            }
            jsonObject.add(SCHOOLS, jsonArray);
        } else {
            jsonObject.add(SCHOOLS, new JsonArray());
        }

        if (src.getComponents() != null && !src.getComponents().isEmpty()) {
            jsonArray = new JsonArray();
            for (ComponentBean cBean : src.getComponents()) {
                jsonArray.add(serializeComponent(cBean));
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

    private RulebookBean deserializeRulebook(JsonElement json) throws JsonParseException {
        return RulebookBean.newInstance(0L, json.getAsString());
    }

    private CharacterClassLevelExtraBean deserializeClass(JsonElement json) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return CharacterClassLevelExtraBean.newInstance(
                CharacterClassBean.newInstance(0L, jsonObject.get(CLASS).getAsString()),
                jsonObject.has(EXTRA) ? jsonObject.get(EXTRA).getAsString() : null,
                jsonObject.get(LEVEL).getAsInt()
        );
    }

    private SchoolSubschoolBean deserializeSchool(JsonElement json) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return SchoolSubschoolBean.newInstance(
                SchoolBean.newInstance(0L, jsonObject.get(SCHOOL).getAsString()),
                jsonObject.has(SUBSCHOOL) ? SubschoolBean.newInstance(0L, jsonObject.get(SUBSCHOOL).getAsString(), 0L) : null
        );
    }

    private ComponentBean deserializeComponent(JsonElement json) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return ComponentBean.newInstance(0L, json.getAsString(), null);
        } else {
            JsonObject jsonObject = json.getAsJsonObject();
            return ComponentBean.newInstance(
                    0L, jsonObject.get(NAME).getAsString(), jsonObject.get(EXTRA).getAsString()
            );
        }
    }

    private DescriptorBean deserializeDescriptor(JsonElement json) throws JsonParseException {
        return DescriptorBean.newInstance(0L, json.getAsString());
    }

    private JsonElement serializeRulebook(RulebookBean src) {
        return new JsonPrimitive(src.getName());
    }

    private JsonElement serializeClass(CharacterClassLevelExtraBean src) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(CLASS, src.getCharacterClass().getName());
        jsonObject.addProperty(LEVEL, src.getLevel());
        jsonObject.addProperty(EXTRA, src.getExtra());

        return jsonObject;
    }

    private JsonElement serializeSchool(SchoolSubschoolBean src) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(SCHOOL, src.getSchool().getName());
        if (src.getSubschool() != null) {
            jsonObject.addProperty(SUBSCHOOL, src.getSubschool().getName());
        } else {
            jsonObject.add(SUBSCHOOL, JsonNull.INSTANCE);
        }

        return jsonObject;
    }

    private JsonElement serializeComponent(ComponentBean src) {
        if (StringUtils.isBlank(src.getExtra())) {
            return new JsonPrimitive(src.getName());
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(NAME, src.getName());
            jsonObject.addProperty(EXTRA, src.getExtra());
            return jsonObject;
        }
    }

    private JsonElement serializeDescriptor(DescriptorBean src) {
        return new JsonPrimitive(src.getName());
    }

}
