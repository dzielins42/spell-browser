package pl.dzielins42.spellcontentprovider.spell.composite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentJsonSerializer;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorJsonSerializer;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookJsonSerializer;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;

import static org.junit.Assert.*;

public class GsonExportTest {

    private Gson mGson;

    @Before
    public void setUp() throws Exception {
        mGson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(DescriptorBean.class, new DescriptorJsonSerializer())
                .registerTypeAdapter(RulebookBean.class, new RulebookJsonSerializer())
                .registerTypeAdapter(ComponentBean.class, new ComponentJsonSerializer())
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
                "#flavourTextFormatted#"
        );

        Set<CharacterClassLevelExtraBean> classesLevels = new HashSet<>();
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

        Set<SchoolSubschoolBean> schools = new HashSet<>();
        schools.add(SchoolSubschoolBean.newInstance(
                SchoolBean.newInstance(0L, "evocation"),
                null
        ));
        bean.setSchools(schools);

        Set<ComponentBean> components = new HashSet<>();
        components.add(ComponentBean.newInstance(0L, "verbal"));
        components.add(ComponentBean.newInstance(1L, "somatic"));
        components.add(ComponentBean.newInstance(2L, "material"));
        bean.setComponents(components);

        bean.setDescriptors(Collections.EMPTY_SET);

        String json = mGson.toJson(bean);

        System.out.println(json);
    }

}