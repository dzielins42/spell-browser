package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassContentValues;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassCursor;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassSelection;

public class CharacterClassDao {

    public static List<CharacterClassBean> get(
            @NonNull Context context,
            @NonNull CharacterClassSelection selection
    ) {
        return get(context.getContentResolver(), selection);
    }

    public static List<CharacterClassBean> get(
            @NonNull ContentResolver contentResolver,
            @NonNull CharacterClassSelection selection
    ) {
        CharacterClassCursor cursor = selection.query(
                contentResolver, CharacterClassColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<CharacterClassBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(CharacterClassBean.copy(cursor));
        }

        return list;
    }

    public static void remove(
            @NonNull Context context,
            @NonNull CharacterClassBean bean
    ) {
        remove(context.getContentResolver(), bean);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull CharacterClassBean bean
    ) {
        remove(contentResolver, new CharacterClassSelection().id(bean.getId()));
    }

    public static void remove(
            @NonNull Context context,
            @NonNull CharacterClassSelection selection
    ) {
        remove(context.getContentResolver(), selection);
    }

    public static void remove(
            @NonNull ContentResolver contentResolver,
            @NonNull CharacterClassSelection selection
    ) {
        selection.delete(contentResolver);
    }

    public static void save(
            @NonNull Context context,
            @NonNull CharacterClassBean bean
    ) {
        save(context.getContentResolver(), bean);
    }

    public static void save(
            @NonNull ContentResolver contentResolver,
            @NonNull CharacterClassBean bean
    ) {
        final boolean isUpdate = bean.getId() > 0;

        CharacterClassContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(contentResolver, new CharacterClassSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(contentResolver);
            bean.setId(ContentUris.parseId(uri));
        }
    }

}
