package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import pl.dzielins42.spellcontentprovider.base.AbstractSelection;

public abstract class AbsDao<BEAN, SELECTION extends AbstractSelection> {

    private final Context mContext;

    public AbsDao(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        mContext = context;
    }

    @NonNull
    protected Context getContext() {
        return mContext;
    }

    public ContentResolver getContentResolver() {
        return mContext.getContentResolver();
    }

    public Observable<Integer> remove(@NonNull final SELECTION selection) {
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return removeInternal(selection);
            }
        });
    }

    public Observable<List<BEAN>> get(@NonNull final SELECTION selection) {
        return Observable.fromCallable(new Callable<List<BEAN>>() {
            @Override
            public List<BEAN> call() throws Exception {
                return getInternal(selection);
            }
        });
    }

    public Observable<Boolean> remove(@NonNull final BEAN bean) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return removeInternal(bean);
            }
        });
    }

    public Observable<Long> save(@NonNull final BEAN bean) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return saveInternal(bean);
            }
        });
    }

    public Observable<Integer> count() {
        SELECTION selection = instantiateSelectAll();
        return count(selection);
    }

    public Observable<Integer> count(@NonNull final SELECTION selection) {
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return selection.count(getContentResolver());
            }
        });
    }

    protected int removeInternal(@NonNull final SELECTION selection) {
        return selection.delete(getContentResolver());
    }

    protected abstract List<BEAN> getInternal(@NonNull final SELECTION selection);

    protected abstract boolean removeInternal(@NonNull final BEAN bean);

    protected abstract long saveInternal(@NonNull final BEAN bean);

    protected abstract SELECTION instantiateSelectAll();

}
