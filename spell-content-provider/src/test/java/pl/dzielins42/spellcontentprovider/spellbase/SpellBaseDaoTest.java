package pl.dzielins42.spellcontentprovider.spellbase;

import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookSelection;

import static org.junit.Assert.assertEquals;

public class SpellBaseDaoTest
        extends SimpleDaoTest<SpellBaseBean, SpellBaseSelection, SpellBaseDao, SpellBaseColumns> {

    private long mRulebookId;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Spell requires rulebook
        RulebookDao rulebookDao = new RulebookDao(RuntimeEnvironment.application);
        mRulebookId =
                rulebookDao.save(RulebookBean.newBuilder().name("Test1").build()).blockingFirst();
    }

    @Override
    protected SpellBaseBean[] testBeans() {
        return new SpellBaseBean[]{
                SpellBaseBean.newBuilder()
                        .name("Test1")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test2")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test3")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test4")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test5")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .build(),
        };
    }

    @Override
    protected SpellBaseSelection selectionForSingleTestBean() {
        SpellBaseSelection selection = new SpellBaseSelection();
        selection.name("Test1");

        return selection;
    }

    @Override
    protected SpellBaseSelection selectionForThreeTestBeans() {
        SpellBaseSelection selection = new SpellBaseSelection();
        selection.name("Test1", "Test2", "Test3");

        return selection;
    }

    @Override
    protected String tableName() {
        return SpellBaseColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(SpellBaseBean spellBaseBean) {
        spellBaseBean.setName(spellBaseBean.getName() + "_changed");
    }

    @Override
    protected SpellBaseDao dao() {
        return new SpellBaseDao(RuntimeEnvironment.application);
    }

    @Test
    public void remove_cascade() throws Exception {
        insertTestBeans();
        RulebookDao rulebookDao = new RulebookDao(RuntimeEnvironment.application);
        rulebookDao.remove(new RulebookSelection().id(mRulebookId)).blockingFirst();
        List<SpellBaseBean> querryResult = mDao.get(new SpellBaseSelection()).blockingFirst();
        assertEquals(true, (querryResult == null | querryResult.isEmpty()));
    }

}
