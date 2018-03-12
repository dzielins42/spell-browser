package pl.dzielins42.spellcontentprovider.spellbase;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;
import pl.dzielins42.spellcontentprovider.component.ComponentColumns;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorColumns;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookColumns;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookSelection;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.school.SchoolSelection;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesColumns;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsColumns;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsColumns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpellBaseDaoTest
        extends SimpleDaoTest<SpellBaseBean, SpellBaseSelection, SpellBaseDao, SpellBaseColumns> {

    private long mRulebookId;
    private long mSchoolId;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected SpellBaseBean[] testBeans() {
        // Spell requires rulebook
        RulebookDao rulebookDao = new RulebookDao(RuntimeEnvironment.application);
        mRulebookId =
                rulebookDao.save(RulebookBean.newBuilder().name("Test1").build()).blockingGet();
        // Spell requires school
        SchoolDao schoolDao = new SchoolDao(RuntimeEnvironment.application);
        mSchoolId =
                schoolDao.save(SchoolBean.newBuilder().name("School1").build()).blockingGet();

        return new SpellBaseBean[]{
                SpellBaseBean.newBuilder()
                        .name("Test1")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .schoolId(mSchoolId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test2")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .schoolId(mSchoolId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test3")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .schoolId(mSchoolId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test4")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .schoolId(mSchoolId)
                        .build(),
                SpellBaseBean.newBuilder()
                        .name("Test5")
                        .castingTime("instantaneous")
                        .range("personal")
                        .target("you")
                        .effect("-")
                        .duration("instantaneous")
                        .rulebookId(mRulebookId)
                        .schoolId(mSchoolId)
                        .build(),
        };
    }

    @Override
    protected void clearAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + RulebookColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SchoolColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellBaseColumns.TABLE_NAME + ";");
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
    public void remove_cascade_rulebook() throws Exception {
        insertTestBeans();
        RulebookDao rulebookDao = new RulebookDao(RuntimeEnvironment.application);
        rulebookDao.remove(new RulebookSelection().id(mRulebookId)).blockingGet();
        List<SpellBaseBean> querryResult = mDao.get(new SpellBaseSelection()).blockingFirst();
        assertTrue(querryResult == null | querryResult.isEmpty());
    }

    @Test
    public void remove_cascade_school() throws Exception {
        insertTestBeans();
        SchoolDao schoolDao = new SchoolDao(RuntimeEnvironment.application);
        schoolDao.remove(new SchoolSelection().id(mSchoolId)).blockingGet();
        List<SpellBaseBean> querryResult = mDao.get(new SpellBaseSelection()).blockingFirst();
        assertTrue(querryResult == null | querryResult.isEmpty());
    }

}
