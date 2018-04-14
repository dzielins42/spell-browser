package pl.dzielins42.spellcontentprovider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassWithLevelBean;
import pl.dzielins42.spellcontentprovider.component.ComponentWithExtraBean;
import pl.dzielins42.spellcontentprovider.spell.SpellBean;
import pl.dzielins42.spellcontentprovider.spell.SpellJsonSerializerDeserializer;

public class GsonTest {

    Gson mGson;

    @Before
    public void setUp() throws Exception {
        mGson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(SpellBean.class, new SpellJsonSerializerDeserializer())
                .create();
    }

    @After
    public void tearDown() throws Exception {
        mGson = null;
    }

    @Test
    public void exportSingle() throws Exception {
        // Checks only if there is no Exception thrown during export
        String spellJson = mGson.toJson(constructFireball5e());
        Assert.assertTrue(!StringUtils.isBlank(spellJson));
    }

    @Test
    public void importSingle() throws Exception {
        SpellBean spellBean = mGson.fromJson(
                "{\"name\":\"Fireball\",\"rulebook\":\"SRD\",\"page\":144,\"casting_time\":\"1 action\",\"range\":\"150 feet\",\"duration\":\"Instantaneous\",\"description_plain\":\"A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot‑radius sphere centered on that point  must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one. The fire spreads around corners. It ignites flammable objects in the area that aren\\u0027t being worn or carried. At Higher Levels. When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.\",\"description_formatted\":\"A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot‑radius sphere centered on that point  must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.\\u003cp\\u003eThe fire spreads around corners. It ignites flammable objects in the area that aren\\u0027t being worn or carried.\\u003cp\\u003e\\u003ci\\u003e\\u003cb\\u003eAt Higher Levels.\\u003c/b\\u003e\\u003c/i\\u003e When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.\",\"is_ritual\":false,\"classes\":[{\"class\":\"Wizard\",\"level\":3},{\"class\":\"Sorcerer\",\"level\":3},{\"class\":\"Warlock\",\"level\":3,\"extra\":\"Fiend Otherworldly Patron\"}],\"components\":[\"Verbal\",\"Somatic\",{\"name\":\"Material\",\"extra\":\"a tiny ball of bat guano and sulfur\"}],\"descriptors\":[]}",
                SpellBean.class
        );
        Assert.assertNotNull(spellBean);
    }

    private SpellBean constructFireball5e() {
        return SpellBean.builder().name("Fireball")
                .schoolMainTypeName("Evocation")
                .rulebookName("SRD5")
                .page(144)
                .castingTime("1 action")
                .range("150 feet")
                .components(Arrays.asList(
                        ComponentWithExtraBean.newInstance(0L, "Verbal", null),
                        ComponentWithExtraBean.newInstance(0L, "Somatic", null),
                        ComponentWithExtraBean.newInstance(
                                0L,
                                "Material",
                                "a tiny ball of bat guano and sulfur"
                        )
                ))
                .duration("Instantaneous")
                .characterClasses(Arrays.asList(
                        CharacterClassWithLevelBean.newInstance(0L, "Wizard", 3, null),
                        CharacterClassWithLevelBean.newInstance(0L, "Sorcerer", 3, null),
                        CharacterClassWithLevelBean.newInstance(0L, "Warlock", 3, "Fiend Otherworldly Patron")
                ))
                .descriptionPlain("A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot‑radius sphere centered on that point  must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one. The fire spreads around corners. It ignites flammable objects in the area that aren't being worn or carried. At Higher Levels. When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.")
                .descriptionFormatted("A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot‑radius sphere centered on that point  must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.<p>The fire spreads around corners. It ignites flammable objects in the area that aren't being worn or carried.<p><i><b>At Higher Levels.</b></i> When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.")
                .build();
    }
}