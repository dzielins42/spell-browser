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

    @Override
    protected List<ComponentBean> getInternal(@NonNull final ComponentSelection selection) {
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

    @Override
    protected boolean removeInternal(@NonNull final ComponentBean bean) {
        return removeInternal(new ComponentSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull final ComponentBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        ComponentContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new ComponentSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

    @Override
    protected ComponentSelection instantiateSelectAll() {
        return new ComponentSelection();
    }

}
