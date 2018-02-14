package pl.dzielins42.spellcontentprovider;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import pl.dzielins42.spellcontentprovider.base.AbstractSelection;

public interface Dao<BEAN, SELECTION extends AbstractSelection> {
    //TODO
    //Observable<Boolean> remove(final long id);

    Observable<Boolean> remove(@NonNull final BEAN bean);

    Observable<Integer> remove(@NonNull final SELECTION selection);

    Observable<List<BEAN>> get(@NonNull final SELECTION selection);

    Observable<Long> save(@NonNull final BEAN bean);

    Observable<Integer> count();

    Observable<Integer> count(@NonNull final SELECTION selection);
}
