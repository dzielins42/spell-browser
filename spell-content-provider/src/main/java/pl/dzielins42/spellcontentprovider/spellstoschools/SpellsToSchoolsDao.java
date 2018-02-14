package pl.dzielins42.spellcontentprovider.spellstoschools;

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
public class SpellsToSchoolsDao
        extends AbsDao<SpellsToSchoolsBean, SpellsToSchoolsSelection> {

    public SpellsToSchoolsDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SpellsToSchoolsBean> getInternal(
            @NonNull SpellsToSchoolsSelection selection
    ) {
        SpellsToSchoolsCursor cursor =
                selection.query(getContentResolver(), SpellsToSchoolsColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellsToSchoolsBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellsToSchoolsBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull SpellsToSchoolsBean bean) {
        return removeInternal(new SpellsToSchoolsSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull SpellsToSchoolsBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        SpellsToSchoolsContentValues contentValues =
                ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(
                    getContentResolver(), new SpellsToSchoolsSelection().id(bean.getId())
            );
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

    @Override
    protected SpellsToSchoolsSelection instantiateSelectAll() {
        return new SpellsToSchoolsSelection();
    }

}
