package pl.dzielins42.spells.browser.data.local;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.spell.SpellDao;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolDao;

@Singleton
public class SpellsService {

    @Inject
    public SpellsService(
            CharacterClassDao characterClassDao,
            ComponentDao componentDao,
            DescriptorDao descriptorDao,
            RulebookDao rulebookDao,
            SchoolDao schoolDao,
            SpellDao spellDao,
            SubschoolDao subschoolDao
    ) {
        Log.d(
                "dzielins",
                "SpellsService() called with: characterClassDao = [" + characterClassDao + "], " +
                        "componentDao = [" + componentDao + "], descriptorDao = [" +
                        descriptorDao + "], rulebookDao = [" + rulebookDao + "], schoolDao = [" +
                        schoolDao + "], spellDao = [" + spellDao + "], subschoolDao = [" + subschoolDao + "]"
        );
    }

}
