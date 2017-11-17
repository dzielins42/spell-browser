package pl.dzielins42.spellcontentprovider.component;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class ComponentDao extends AbsDao<ComponentBean, ComponentSelection> {

    public ComponentDao(@NonNull Context context) {
        super(context);
    }

    public List<ComponentBean> get(@NonNull ComponentSelection selection) {
        ComponentCursor cursor = selection.query(
                getContentResolver(), ComponentColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<ComponentBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(ComponentBean.copy(cursor));
        }

        return list;
    }

    public void remove(@NonNull ComponentBean bean) {
        remove(new ComponentSelection().id(bean.getId()));
    }

    public void remove(@NonNull ComponentSelection selection) {
        selection.delete(getContentResolver());
    }

    public void save(@NonNull ComponentBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        ComponentContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new ComponentSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
