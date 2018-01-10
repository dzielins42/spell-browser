package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

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
    public Context getContext() {
        return mContext;
    }

    public ContentResolver getContentResolver() {
        return mContext.getContentResolver();
    }

    public void remove(@NonNull SELECTION selection) {
        if (selection!=null){
            selection.delete(getContentResolver());
        }
    }

    public abstract List<BEAN> get(@NonNull SELECTION selection);

    public abstract void remove(@NonNull BEAN bean);

    public abstract void save(@NonNull BEAN bean);

}
