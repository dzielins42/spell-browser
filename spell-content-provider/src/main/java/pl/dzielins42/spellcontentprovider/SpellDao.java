package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.spell.SpellBean;
import pl.dzielins42.spellcontentprovider.spell.SpellColumns;
import pl.dzielins42.spellcontentprovider.spell.SpellContentValues;
import pl.dzielins42.spellcontentprovider.spell.SpellCursor;
import pl.dzielins42.spellcontentprovider.spell.SpellSelection;

public class SpellDao {

    public static List<SpellBean> get(
            @NonNull Context context,
            @NonNull SpellSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<SpellBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellSelection selection
    ) {
        SpellCursor cursor = selection.query(
                contentResolver, SpellColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellBean.copy(cursor));
        }

        return list;
    }

    public static void remove(
            @NonNull Context context,
            @NonNull SpellBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellBean bean
    ) {
        remove(contentResolver, new SpellSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull SpellSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull SpellBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull SpellBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        SpellContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new SpellSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
