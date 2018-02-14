package pl.dzielins42.spellcontentprovider.spellstodescriptors;

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
public class SpellsToDescriptorsDao
        extends AbsDao<SpellsToDescriptorsBean, SpellsToDescriptorsSelection> {

    public SpellsToDescriptorsDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SpellsToDescriptorsBean> getInternal(
            @NonNull SpellsToDescriptorsSelection selection
    ) {
        SpellsToDescriptorsCursor cursor =
                selection.query(getContentResolver(), SpellsToDescriptorsColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellsToDescriptorsBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellsToDescriptorsBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull SpellsToDescriptorsBean bean) {
        return removeInternal(new SpellsToDescriptorsSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull SpellsToDescriptorsBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SpellsToDescriptorsContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(
                    getContentResolver(), new SpellsToDescriptorsSelection().id(bean.getId())
            );
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

    @Override
    protected SpellsToDescriptorsSelection instantiateSelectAll() {
        return new SpellsToDescriptorsSelection();
    }

}
