package pl.dzielins42.spellcontentprovider.component;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pl.dzielins42.spellcontentprovider.ContentValuesUtils;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentColumns;
import pl.dzielins42.spellcontentprovider.component.ComponentContentValues;
import pl.dzielins42.spellcontentprovider.component.ComponentCursor;
import pl.dzielins42.spellcontentprovider.component.ComponentSelection;

public class ComponentDao {

    public static List<ComponentBean> get(
            @NonNull Context context,
            @NonNull ComponentSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<ComponentBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull ComponentSelection selection
    ) {
        ComponentCursor cursor = selection.query(
                contentResolver, ComponentColumns.ALL_COLUMNS
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

    public static void remove(
            @NonNull Context context,
            @NonNull ComponentBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull ComponentBean bean
    ) {
        remove(contentResolver, new ComponentSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull ComponentSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull ComponentSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull ComponentBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull ComponentBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        ComponentContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new ComponentSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
