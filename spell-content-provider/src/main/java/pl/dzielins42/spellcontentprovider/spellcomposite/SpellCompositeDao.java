package pl.dzielins42.spellcontentprovider.spellcomposite;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDao;
import pl.dzielins42.spellcontentprovider.ContentValuesUtils;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseBean;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseColumns;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseContentValues;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseCursor;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseSelection;

public class SpellCompositeDao extends AbsDao<SpellCompositeBean, SpellCompositeSelection> {

    public SpellCompositeDao(@NonNull Context context) {
        super(context);
    }

    @Override
    protected List<SpellCompositeBean> getInternal(@NonNull SpellCompositeSelection selection) {
        SpellCompositeCursor cursor =
                selection.query(getContentResolver(), SpellCompositeColumns.ALL_COLUMNS);

        if (cursor.getCount() <= 0) {
            return Collections.EMPTY_LIST;
        }

        List<SpellCompositeBean> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(SpellCompositeBean.copy(cursor));
        }

        return list;
    }

    @Override
    protected boolean removeInternal(@NonNull SpellCompositeBean bean) {
        // spell_composite is VIEW
        // bean cannot be removed from there directly
        return false;
    }

    @Override
    protected int removeInternal(@NonNull final SpellCompositeSelection selection) {
        // spell_composite is VIEW
        // beans cannot be removed from there directly
        return 0;
    }

    @Override
    protected long saveInternal(@NonNull SpellCompositeBean bean) {
        // spell_composite is VIEW
        // bean cannot be saved there directly
        return 0;
    }

    @Override
    protected SpellCompositeSelection instantiateSelectAll() {
        return new SpellCompositeSelection();
    }

}
