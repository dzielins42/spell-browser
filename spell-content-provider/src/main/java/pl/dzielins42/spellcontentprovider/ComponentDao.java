package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentContentValues;
import pl.dzielins42.spellcontentprovider.component.ComponentSelection;

public class ComponentDao {

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
