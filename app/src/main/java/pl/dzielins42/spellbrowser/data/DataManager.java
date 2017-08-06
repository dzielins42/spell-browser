package pl.dzielins42.spellbrowser.data;


import android.util.LruCache;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.dzielins42.spellbrowser.data.local.SpellDbService;
import pl.dzielins42.spellbrowser.data.model.Spell;
import rx.Observable;

@Singleton
public class DataManager {

    private LruCache<Class<?>, Observable<?>> mObservablesCache = new LruCache<>(10);

    private SpellDbService mSpellDbService;

    @Inject
    public DataManager(SpellDbService spellDbService) {
        mSpellDbService = spellDbService;
    }

    public Observable<List<Spell>> getSpells() {
        return mSpellDbService.getSpells().toList();
    }

    public Observable<Spell> getSpell(long id) {
        return mSpellDbService.getSpell(id);
    }

    public Observable<Spell> saveSpell(final Spell spell) {
        return mSpellDbService.saveSpell(spell);
    }
}
