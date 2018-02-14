package pl.dzielins42.spellcontentprovider.spellstocomponents;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

// TODO tests
public class SpellsToComponentsDao
        extends AbsDao<SpellsToComponentsBean, SpellsToComponentsSelection> {

    public SpellsToComponentsDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SpellsToComponentsBean> getInternal(
            @NonNull SpellsToComponentsSelection selection
    ) {
        SpellsToComponentsCursor cursor =
                selection.query(getContentResolver(), SpellsToComponentsColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellsToComponentsBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellsToComponentsBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull SpellsToComponentsBean bean) {
        return removeInternal(new SpellsToComponentsSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull SpellsToComponentsBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SpellsToComponentsContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(
                    getContentResolver(), new SpellsToComponentsSelection().id(bean.getId())
            );
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

    @Override
    protected SpellsToComponentsSelection instantiateSelectAll() {
        return new SpellsToComponentsSelection();
    }

}
