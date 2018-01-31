package pl.dzielins42.spellcontentprovider.descriptor;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;

public class DescriptorDao extends AbsDao<DescriptorBean, DescriptorSelection> {

    public DescriptorDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<DescriptorBean> getInternal(@NonNull final DescriptorSelection selection) {
        DescriptorCursor cursor = selection.query(
                getContentResolver(), DescriptorColumns.ALL_COLUMNS
        );

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<DescriptorBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(DescriptorBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull final DescriptorBean bean) {
        return removeInternal(new DescriptorSelection().id(bean.getId())) == 1;
    }

    @Override
    protected long saveInternal(@NonNull final DescriptorBean bean) {
        final boolean isUpdate = bean.getId() > 0;

        DescriptorContentValues contentValues = ContentValuesUtils.beanToContentValues(bean);

        if (isUpdate) {
            contentValues.update(getContentResolver(), new DescriptorSelection().id(bean.getId()));
        } else {
            Uri uri = contentValues.insert(getContentResolver());
            bean.setId(ContentUris.parseId(uri));
        }

        return bean.getId();
    }

}
