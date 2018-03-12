package pl.dzielins42.spellcontentprovider;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import pl.dzielins42.spellcontentprovider.base.AbstractSelection;

public interface Dao<BEAN, SELECTION extends AbstractSelection> {
    //TODO
    //Observable<Boolean> remove(final long id);

    Completable remove(@NonNull final BEAN bean);

    Single<Integer> remove(@NonNull final SELECTION selection);

    Observable<List<BEAN>> get(@NonNull final SELECTION selection);

    Single<Long> save(@NonNull final BEAN bean);

    Single<Integer> count();

    Single<Integer> count(@NonNull final SELECTION selection);
}
