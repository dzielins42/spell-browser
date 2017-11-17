package pl.dzielins42.spellcontentprovider.subschool;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class SubschoolDao extends AbsDao<SubschoolBean, SubschoolSelection> {

    protected SubschoolDao(@NonNull Context context) {
        super(context);
    }

    public List<SubschoolBean> get(@NonNull SubschoolSelection selection) {
        SubschoolCursor cursor = selection.query(
                getContentResolver(), SubschoolColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SubschoolBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SubschoolBean.copy(cursor));
        }

        return list;
    }

    public void remove(@NonNull SubschoolBean bean) {
        remove(new SubschoolSelection().id(bean.getId()));
    }

    public void remove(@NonNull SubschoolSelection selection) {
        selection.delete(getContentResolver());
    }

    public void save(@NonNull SubschoolBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SubschoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new SubschoolSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
