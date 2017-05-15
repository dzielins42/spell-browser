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
public class SpellDetailPresenter extends BasePresenter<Browser.DetailView> implements
        Browser.DetailPresenter {

    private DataManager mDataManager;

    @Inject
    public SpellDetailPresenter(DataManager dataManager) {
        super();

        mDataManager = dataManager;
    }

    @Override
    public void loadSpell(Long id) {
        // Show loading on View
        getView().showLoading();
        // Get data
        mDataManager.getSpell(id)
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Spell>() {
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
                    public void onNext(Spell spell) {
                        // Set data
                        getView().setData(spell);
                    }
                });
    }
}
