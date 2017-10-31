package pl.dzielins42.spellcontentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolContentValues;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolSelection;

public class SubschoolDao {

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
            contentValues.insert(contentResolver);
        }
    }

}
