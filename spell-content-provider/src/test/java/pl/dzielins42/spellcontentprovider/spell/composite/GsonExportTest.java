package pl.dzielins42.spellcontentprovider.spell.composite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;

public class GsonExportTest {

    private Gson mGson;

    @Before
    public void setUp() throws Exception {
        mGson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(
                        CompositeSpellBean.class, new CompositeSpellJsonSerializerDeserializer()
                )
                .create();
    }

    @After
    public void tearDown() throws Exception {
        mGson = null;
    }

    @Test
    public void exportSingle() throws Exception {
        CompositeSpellBean bean = CompositeSpellBean.newInstance(
                0L,
                "Fireball",
                RulebookBean.newInstance(0, "5e SRD"),
                142,
                "1 action",
                "150 feet",
                "#no-target#",
                "#no-effect#",
                "20-foot-radius sphere",
                "instantaneous",
                "DEX",
                "NO",
                "#descriptionPlain#",
                "#descriptionFormatted#",
                "#shortDescriptionPlain#",
                "#shortDescriptionFormatted#",
                "#flavourTextPlain#",
                "#flavourTextFormatted#",
                false
        );

        List<CharacterClassLevelExtraBean> classesLevels = new ArrayList<>();
        classesLevels.add(CharacterClassLevelExtraBean.newInstance(
                CharacterClassBean.newInstance(0L, "sorcerer"),
                null,
                3
        ));
        classesLevels.add(CharacterClassLevelExtraBean.newInstance(
                CharacterClassBean.newInstance(1L, "wizard"),
                null,
                3
        ));
        bean.setClassesLevels(classesLevels);

        List<SchoolSubschoolBean> schools = new ArrayList<>();
        schools.add(SchoolSubschoolBean.newInstance(
                SchoolBean.newInstance(0L, "evocation"),
                null
        ));
        bean.setSchools(schools);

        List<ComponentBean> components = new ArrayList<>();
        components.add(ComponentBean.newInstance(0L, "verbal", null));
        components.add(ComponentBean.newInstance(1L, "somatic", null));
        components.add(ComponentBean.newInstance(2L, "material", "a tiny ball of bat guano and sulfur"));
        bean.setComponents(components);

        bean.setDescriptors(Collections.EMPTY_LIST);

        String json = mGson.toJson(bean);

        System.out.println(json);

        System.out.println(mGson.fromJson(json, CompositeSpellBean.class));
    }

}