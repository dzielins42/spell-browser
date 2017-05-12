package pl.dzielins42.spellbrowser.ui.browser;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pl.dzielins42.spellbrowser.data.DataManager;
import pl.dzielins42.spellbrowser.data.model.Spell;
import pl.dzielins42.spellbrowser.injection.scope.ConfigPersistent;
import pl.dzielins42.spellbrowser.ui.base.BasePresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@ConfigPersistent
public class SpellListPresenter extends BasePresenter<Browser.ListView> implements
        Browser.ListPresenter {

    private DataManager mDataManager;

    @Inject
    public SpellListPresenter(DataManager dataManager) {
        super();

        mDataManager = dataManager;
    }

    @Override
    public void loadSpells() {
        // Show loading on View
        getView().showLoading();
        // Get data
        mDataManager.getSpells()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Spell>>() {
                    @Override
                    public void onCompleted() {
                        // Display data
                        getView().showContent();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Spell> spells) {
                        // Set data
                        getView().setData(spells);
                    }
                });
    }
}
