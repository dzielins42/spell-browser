package pl.dzielins42.spellcontentprovider.subschool;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pl.dzielins42.spellcontentprovider.ContentValuesUtils;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolColumns;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolContentValues;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolCursor;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolSelection;

public class SubschoolDao {

    public static List<SubschoolBean> get(
            @NonNull Context context,
            @NonNull SubschoolSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<SubschoolBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull SubschoolSelection selection
    ) {
        SubschoolCursor cursor = selection.query(
                contentResolver, SubschoolColumns.ALL_COLUMNS
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

    public static void remove(
            @NonNull Context context,
            @NonNull SubschoolBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull SubschoolBean bean
    ) {
        remove(contentResolver, new SubschoolSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull SubschoolSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull SubschoolSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull SubschoolBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SubschoolBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        SubschoolContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new SubschoolSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
