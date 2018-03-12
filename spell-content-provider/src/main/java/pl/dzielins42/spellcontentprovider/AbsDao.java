package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import pl.dzielins42.spellcontentprovider.base.AbstractSelection;

public abstract class AbsDao<BEAN, SELECTION extends AbstractSelection>
        implements Dao<BEAN, SELECTION> {

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

    @NonNull
    protected ContentResolver getContentResolver() {
        return mContext.getContentResolver();
    }

    @Override
    public Single<Integer> remove(@NonNull final SELECTION selection) {
        return Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return removeInternal(selection);
            }
        });
    }

    @Override
    public Observable<List<BEAN>> get(@NonNull final SELECTION selection) {
        return Observable.fromCallable(new Callable<List<BEAN>>() {
            @Override
            public List<BEAN> call() throws Exception {
                return getInternal(selection);
            }
        });
    }

    @Override
    public Completable remove(@NonNull final BEAN bean) {
        return Completable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                final boolean result = removeInternal(bean);
                if (!result) {
                    throw new IllegalStateException(
                            "removing of " + String.valueOf(bean) + " failed"
                    );
                }

                return result;
            }
        });
    }

    @Override
    public Single<Long> save(@NonNull final BEAN bean) {
        return Single.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return saveInternal(bean);
            }
        });
    }

    @Override
    public Single<Integer> count() {
        SELECTION selection = instantiateSelectAll();
        return count(selection);
    }

    @Override
    public Single<Integer> count(@NonNull final SELECTION selection) {
        return Single.fromCallable(new Callable<Integer>() {
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
