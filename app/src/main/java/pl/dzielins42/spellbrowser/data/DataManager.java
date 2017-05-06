package pl.dzielins42.spellbrowser.data;


import android.util.LruCache;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.dzielins42.spellbrowser.data.local.SpellDbService;
import rx.Observable;

@Singleton
public class DataManager {

    private LruCache<Class<?>, Observable<?>> mObservablesCache = new LruCache<>(10);

    private SpellDbService mSpellDbService;

    @Inject
    public DataManager(SpellDbService spellDbService) {
        mSpellDbService = spellDbService;
    }

}
