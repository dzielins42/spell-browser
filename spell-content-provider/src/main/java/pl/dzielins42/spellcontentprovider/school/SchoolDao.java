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

    public List<SchoolBean> get(@NonNull SchoolSelection selection) {
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

    public void remove(@NonNull SchoolBean bean) {
        remove(new SchoolSelection().id(bean.getId()));
    }

    public void remove(@NonNull SchoolSelection selection) {
        selection.delete(getContentResolver());
    }

    public void save(@NonNull SchoolBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SchoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new SchoolSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
