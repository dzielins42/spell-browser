package pl.dzielins42.spellcontentprovider.spellbase;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class SpellBaseDao extends AbsDao<SpellBaseBean, SpellBaseSelection> {

    public SpellBaseDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SpellBaseBean> getInternal(@NonNull final SpellBaseSelection selection) {
        SpellBaseCursor cursor = selection.query(getContentResolver(), SpellBaseColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellBaseBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellBaseBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull final SpellBaseBean bean) {
        return removeInternal(new SpellBaseSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull final SpellBaseBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SpellBaseContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new SpellBaseSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

    @Override
    protected SpellBaseSelection instantiateSelectAll() {
        return new SpellBaseSelection();
    }

}
