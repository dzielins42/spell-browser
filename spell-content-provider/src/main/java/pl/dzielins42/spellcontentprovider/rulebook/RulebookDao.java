package pl.dzielins42.spellcontentprovider.rulebook;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class RulebookDao extends AbsDao<RulebookBean, RulebookSelection> {

    protected RulebookDao(@NonNull Context context) {
        super(context);
    }

    public List<RulebookBean> get(@NonNull RulebookSelection selection) {
        RulebookCursor cursor = selection.query(getContentResolver(), RulebookColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<RulebookBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(RulebookBean.copy(cursor));
        }

        return list;
    }

    public void remove(@NonNull RulebookBean bean) {
        remove(new RulebookSelection().id(bean.getId()));
    }

    public void remove(@NonNull RulebookSelection selection) {
        selection.delete(getContentResolver());
    }

    public void save(@NonNull RulebookBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        RulebookContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new RulebookSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
