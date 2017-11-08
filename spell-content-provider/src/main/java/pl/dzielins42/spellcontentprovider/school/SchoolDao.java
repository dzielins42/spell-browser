package pl.dzielins42.spellcontentprovider.school;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pl.dzielins42.spellcontentprovider.ContentValuesUtils;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;
import pl.dzielins42.spellcontentprovider.school.SchoolContentValues;
import pl.dzielins42.spellcontentprovider.school.SchoolCursor;
import pl.dzielins42.spellcontentprovider.school.SchoolSelection;

public class SchoolDao {

    public static List<SchoolBean> get(
            @NonNull Context context,
            @NonNull SchoolSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<SchoolBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull SchoolSelection selection
    ) {
        SchoolCursor cursor = selection.query(
                contentResolver, SchoolColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SchoolBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SchoolBean.copy(cursor));
        }

        return list;
    }

    public static void remove(
            @NonNull Context context,
            @NonNull SchoolBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull SchoolBean bean
    ) {
        remove(contentResolver, new SchoolSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull SchoolSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull SchoolSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull SchoolBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SchoolBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        SchoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new SchoolSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
