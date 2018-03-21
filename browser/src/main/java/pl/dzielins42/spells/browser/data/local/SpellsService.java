package pl.dzielins42.spells.browser.data.local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.spell.SpellBean;
import pl.dzielins42.spellcontentprovider.spell.SpellDao;

@Singleton
public class SpellsService {

    private final CharacterClassDao mCharacterClassDao;
    private final ComponentDao mComponentDao;
    private final DescriptorDao mDescriptorDao;
    private final RulebookDao mRulebookDao;
    private final SchoolDao mSchoolDao;
    private final SpellDao mSpellDao;


    @Inject
    public SpellsService(
            CharacterClassDao characterClassDao,
            ComponentDao componentDao,
            DescriptorDao descriptorDao,
            RulebookDao rulebookDao,
            SchoolDao schoolDao,
            SpellDao spellDao
    ) {
        mCharacterClassDao = characterClassDao;
        mComponentDao = componentDao;
        mDescriptorDao = descriptorDao;
        mRulebookDao = rulebookDao;
        mSchoolDao = schoolDao;
        mSpellDao = spellDao;
    }

    public Observable<List<CharacterClassBean>> getAllCharacterClasses(){
        return Observable.never();
    }

    public Observable<List<ComponentBean>> getAllComponents(){
        return Observable.never();
    }

    public Observable<List<DescriptorBean>> getAllDescriptors(){
        return Observable.never();
    }

    public Observable<List<RulebookBean>> getAllRulebooks(){
        return Observable.never();
    }

    public Observable<List<SchoolBean>> getAllSchools(){
        return Observable.never();
    }

    public Observable<List<SpellBean>> getAllSpells(){
        return Observable.never();
    }

}
