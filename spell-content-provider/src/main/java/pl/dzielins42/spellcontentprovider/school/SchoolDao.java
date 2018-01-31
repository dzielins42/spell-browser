package pl.dzielins42.spellcontentprovider.school;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class SchoolDao extends AbsDao<SchoolBean, SchoolSelection> {

    public SchoolDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SchoolBean> getInternal(@NonNull final SchoolSelection selection) {
        SchoolCursor cursor = selection.query(getContentResolver(), SchoolColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SchoolBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SchoolBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull final SchoolBean bean) {
        return removeInternal(new SchoolSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull final SchoolBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SchoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new SchoolSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

}
