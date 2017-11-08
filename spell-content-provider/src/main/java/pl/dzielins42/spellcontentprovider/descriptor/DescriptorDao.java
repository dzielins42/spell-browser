package pl.dzielins42.spellcontentprovider.descriptor;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pl.dzielins42.spellcontentprovider.ContentValuesUtils;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorColumns;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorContentValues;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorCursor;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorSelection;

public class DescriptorDao {

    public static List<DescriptorBean> get(
            @NonNull Context context,
            @NonNull DescriptorSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<DescriptorBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull DescriptorSelection selection
    ) {
        DescriptorCursor cursor = selection.query(
                contentResolver, DescriptorColumns.ALL_COLUMNS
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

    public static void remove(
            @NonNull Context context,
            @NonNull DescriptorBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull DescriptorBean bean
    ) {
        remove(contentResolver, new DescriptorSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull DescriptorSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull DescriptorSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull DescriptorBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull DescriptorBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        DescriptorContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new DescriptorSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
