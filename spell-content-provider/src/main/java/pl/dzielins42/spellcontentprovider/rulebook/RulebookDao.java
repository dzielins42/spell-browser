package pl.dzielins42.spellcontentprovider.rulebook;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pl.dzielins42.spellcontentprovider.ContentValuesUtils;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookColumns;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookContentValues;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookCursor;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookSelection;

public class RulebookDao {

    public static List<RulebookBean> get(
            @NonNull Context context,
            @NonNull RulebookSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<RulebookBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull RulebookSelection selection
    ) {
        RulebookCursor cursor = selection.query(
                contentResolver, RulebookColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<RulebookBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(RulebookBean.copy(cursor));
        }

        return list;
    }

    public static void remove(
            @NonNull Context context,
            @NonNull RulebookBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull RulebookBean bean
    ) {
        remove(contentResolver, new RulebookSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull RulebookSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull RulebookSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull RulebookBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull RulebookBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        RulebookContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new RulebookSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
