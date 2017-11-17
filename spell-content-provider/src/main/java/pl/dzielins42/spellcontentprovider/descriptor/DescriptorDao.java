package pl.dzielins42.spellcontentprovider.descriptor;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class DescriptorDao extends AbsDao<DescriptorBean, DescriptorSelection> {

    protected DescriptorDao(@NonNull Context context) {
        super(context);
    }

    public List<DescriptorBean> get(@NonNull DescriptorSelection selection) {
        DescriptorCursor cursor = selection.query(
                getContentResolver(), DescriptorColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<DescriptorBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(DescriptorBean.copy(cursor));
        }

        return list;
    }

    public void remove(@NonNull DescriptorBean bean) {
        remove(new DescriptorSelection().id(bean.getId()));
    }

    public void remove(@NonNull DescriptorSelection selection) {
        selection.delete(getContentResolver());
    }

    public void save(@NonNull DescriptorBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        DescriptorContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new DescriptorSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
