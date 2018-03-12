package pl.dzielins42.spellcontentprovider.spellcomposite;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import pl.dzielins42.spellcontentprovider.AbsDao;

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

    // TODO test this case!
    @Override
    public Single<Integer> count(@NonNull final SpellCompositeSelection selection) {
        // Simple count won't work as spell_composite is a database view and same spell (from
        // spell_base) may occur multiple times in spell_composite
        // We need to count distinct ids
        return get(selection)
                .flatMap(new Function<List<SpellCompositeBean>, ObservableSource<SpellCompositeBean>>() {
                    @Override
                    public ObservableSource<SpellCompositeBean> apply(List<SpellCompositeBean> spellCompositeBeans) throws Exception {
                        return Observable.fromIterable(spellCompositeBeans);
                    }
                })
                .distinct(new Function<SpellCompositeBean, Long>() {
                    @Override
                    public Long apply(SpellCompositeBean spellCompositeBean) throws Exception {
                        return spellCompositeBean.getId();
                    }
                })
                .count().map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return aLong.intValue();
                    }
                });
    }

}
