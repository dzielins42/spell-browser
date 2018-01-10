package pl.dzielins42.spellcontentprovider.spell;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class SpellDao extends AbsDao<SpellBean, SpellSelection> {

    public SpellDao(@NonNull Context context) {
        super(context);
    }

    @Override
    public List<SpellBean> get(@NonNull SpellSelection selection) {
        SpellCursor cursor = selection.query(getContentResolver(), SpellColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellBean.copy(cursor));
        }

        return list;
    }

    @Override
    public void remove(@NonNull SpellBean bean) {
        remove(new SpellSelection().id(bean.getId()));
    }

    @Override
    public void save(@NonNull SpellBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SpellContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new SpellSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
