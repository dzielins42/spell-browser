package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorContentValues;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorSelection;

public class DescriptorDao {

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
