package pl.dzielins42.spellcontentprovider.characterclass;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class CharacterClassDao extends AbsDao<CharacterClassBean, CharacterClassSelection> {

    public CharacterClassDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<CharacterClassBean> getInternal(
            @NonNull final CharacterClassSelection selection
    ) {
        CharacterClassCursor cursor = selection.query(
                getContentResolver(), CharacterClassColumns.ALL_COLUMNS
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

    @Override
    protected boolean removeInternal(@NonNull final CharacterClassBean bean) {
        return removeInternal(new CharacterClassSelection().id(bean.getId())) == 1;
    }

    @Override
    protected boolean saveInternal(@NonNull final CharacterClassBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        CharacterClassContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(
                    getContentResolver(), new CharacterClassSelection().id(bean.getId())
            );
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return true;
    }

}
