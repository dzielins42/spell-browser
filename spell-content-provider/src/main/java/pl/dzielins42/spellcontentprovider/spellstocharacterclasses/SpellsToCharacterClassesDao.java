package pl.dzielins42.spellcontentprovider.spellstocharacterclasses;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

// TODO tests
public class SpellsToCharacterClassesDao
        extends AbsDao<SpellsToCharacterClassesBean, SpellsToCharacterClassesSelection> {

    public SpellsToCharacterClassesDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SpellsToCharacterClassesBean> getInternal(
            @NonNull SpellsToCharacterClassesSelection selection
    ) {
        SpellsToCharacterClassesCursor cursor =
                selection.query(getContentResolver(), SpellsToCharacterClassesColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellsToCharacterClassesBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellsToCharacterClassesBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull SpellsToCharacterClassesBean bean) {
        return removeInternal(new SpellsToCharacterClassesSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull SpellsToCharacterClassesBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SpellsToCharacterClassesContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(
                    getContentResolver(), new SpellsToCharacterClassesSelection().id(bean.getId())
            );
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

    @Override
    protected SpellsToCharacterClassesSelection instantiateSelectAll() {
        return new SpellsToCharacterClassesSelection();
    }

}
