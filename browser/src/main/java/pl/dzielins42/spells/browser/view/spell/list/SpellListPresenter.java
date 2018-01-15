package pl.dzielins42.spells.browser.view.spell.list;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.dzielins42.spells.browser.data.local.SpellsService;
import timber.log.Timber;

@Singleton
public class SpellListPresenter extends MviBasePresenter<SpellListView, SpellListState> {

    private SpellsService mSpellsService;

    @Inject
    public SpellListPresenter(SpellsService spellsService) {
        mSpellsService = spellsService;
    }

    @Override
    protected void bindIntents() {
        Observable<SpellListState> intentsObservable = intent(SpellListView::loadIntent)
                .doOnNext(
                        b -> Timber.d("intent: loadIntent")
                )
                .map(
                        b -> new SpellListState.Loading()
                )
                .cast(SpellListState.class)
                .observeOn(AndroidSchedulers.mainThread());

        subscribeViewState(intentsObservable, SpellListView::render);
    }

}
