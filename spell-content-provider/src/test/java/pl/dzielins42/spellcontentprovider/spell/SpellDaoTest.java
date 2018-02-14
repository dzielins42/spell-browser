package pl.dzielins42.spellcontentprovider.spell;

import android.database.sqlite.SQLiteDatabase;

import org.robolectric.RuntimeEnvironment;

import java.util.List;

import pl.dzielins42.spellcontentprovider.AbsDaoTest;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentColumns;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorColumns;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookColumns;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseBean;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseColumns;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseDao;
import pl.dzielins42.spellcontentprovider.spellcomposite.SpellCompositeDao;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesBean;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesColumns;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesDao;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsBean;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsColumns;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsDao;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsBean;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsColumns;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsDao;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsBean;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsColumns;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsDao;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolBean;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolColumns;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolDao;

import static org.junit.Assert.assertEquals;

public class SpellDaoTest extends AbsDaoTest {

    private SpellDao mDao;

    private CharacterClassBean[] mTestCharacterClassBeans;
    private ComponentBean[] mTestComponentBeans;
    private DescriptorBean[] mTestDescriptorBeans;
    private RulebookBean[] mTestRulebookBeans;
    private SchoolBean[] mTestSchoolBeans;
    private SpellBaseBean[] mTestSpellBaseBeans;
    private SubschoolBean[] mTestSubschoolBeans;

    private SpellBean[] mTestSpellBeans;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        CharacterClassDao characterClassDao = new CharacterClassDao(RuntimeEnvironment.application);
        ComponentDao componentDao = new ComponentDao(RuntimeEnvironment.application);
        DescriptorDao descriptorDao = new DescriptorDao(RuntimeEnvironment.application);
        RulebookDao rulebookDao = new RulebookDao(RuntimeEnvironment.application);
        SchoolDao schoolDao = new SchoolDao(RuntimeEnvironment.application);
        SpellBaseDao spellBaseDao = new SpellBaseDao(RuntimeEnvironment.application);
        SpellCompositeDao spellCompositeDao = new SpellCompositeDao(RuntimeEnvironment.application);
        SubschoolDao subschoolDao = new SubschoolDao(RuntimeEnvironment.application);
        SpellsToCharacterClassesDao spellsToCharacterClassesDao = new SpellsToCharacterClassesDao(RuntimeEnvironment.application);
        SpellsToComponentsDao spellsToComponentsDao = new SpellsToComponentsDao(RuntimeEnvironment.application);
        SpellsToDescriptorsDao spellsToDescriptorsDao = new SpellsToDescriptorsDao(RuntimeEnvironment.application);
        SpellsToSchoolsDao spellsToSchoolsDao = new SpellsToSchoolsDao(RuntimeEnvironment.application);
        mDao = new SpellDao(
                RuntimeEnvironment.application,
                characterClassDao,
                componentDao,
                descriptorDao,
                rulebookDao,
                schoolDao,
                spellBaseDao,
                spellCompositeDao,
                subschoolDao,
                spellsToCharacterClassesDao,
                spellsToComponentsDao,
                spellsToDescriptorsDao,
                spellsToSchoolsDao
        );

        initDatabase();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        mDao = null;
    }

    @Override
    public void save_insert() throws Exception {

    }

    @Override
    public void save_update() throws Exception {

    }

    @Override
    public void get_1() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.spellName("Spell1");
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        assertEquals(1, getResult.size());
    }

    @Override
    public void get_3() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.spellName("Spell1", "Spell2", "Spell3");
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        assertEquals(3, getResult.size());
    }

    @Override
    public void remove_selection() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.spellName("Spell1", "Spell2", "Spell3");
        int removeResult = mDao.remove(selection).blockingFirst();
        assertEquals(3, removeResult);
        assertEquals(mTestSpellBaseBeans.length - 3, (int) mDao.count().blockingFirst());
    }

    @Override
    public void remove_bean() throws Exception {

    }

    @Override
    public void count_all() throws Exception {
        assertEquals(
                mTestSpellBaseBeans.length, (int) mDao.count().blockingFirst()
        );
        assertEquals(
                mTestSpellBaseBeans.length, (int) mDao.count(new SpellSelection()).blockingFirst()
        );
    }

    @Override
    public void count_selection() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.spellName("Spell1", "Spell2", "Spell3");
        assertEquals(3, (int) mDao.count(selection).blockingFirst());
    }

    @Override
    protected void clearAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + CharacterClassColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + ComponentColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + DescriptorColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + RulebookColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SchoolColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellBaseColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SubschoolColumns.TABLE_NAME + ";");

        db.execSQL("DELETE FROM " + SpellsToCharacterClassesColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellsToComponentsColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellsToDescriptorsColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellsToSchoolsColumns.TABLE_NAME + ";");
    }

    private void initDatabase() {
        mTestCharacterClassBeans = new CharacterClassBean[]{
                CharacterClassBean.newBuilder().name("CharacterClass1").build(),
                CharacterClassBean.newBuilder().name("CharacterClass2").build(),
                CharacterClassBean.newBuilder().name("CharacterClass3").build(),
        };
        for (CharacterClassBean bean : mTestCharacterClassBeans) {
            mDao.mCharacterClassDao.save(bean).blockingFirst();
        }
        mTestComponentBeans = new ComponentBean[]{
                ComponentBean.newBuilder().name("Component1").build(),
                ComponentBean.newBuilder().name("Component2").extra("Extra2").build(),
                ComponentBean.newBuilder().name("Component3").build(),
        };
        for (ComponentBean bean : mTestComponentBeans) {
            mDao.mComponentDao.save(bean).blockingFirst();
        }
        mTestDescriptorBeans = new DescriptorBean[]{
                DescriptorBean.newBuilder().name("Descriptor1").build(),
                DescriptorBean.newBuilder().name("Descriptor2").build(),
                DescriptorBean.newBuilder().name("Descriptor3").build(),
        };
        for (DescriptorBean bean : mTestDescriptorBeans) {
            mDao.mDescriptorDao.save(bean).blockingFirst();
        }
        mTestRulebookBeans = new RulebookBean[]{
                RulebookBean.newBuilder().name("Rulebook1").build(),
                RulebookBean.newBuilder().name("Rulebook2").build(),
                RulebookBean.newBuilder().name("Rulebook3").build(),
        };
        for (RulebookBean bean : mTestRulebookBeans) {
            mDao.mRulebookDao.save(bean).blockingFirst();
        }
        mTestSchoolBeans = new SchoolBean[]{
                SchoolBean.newBuilder().name("School1").build(),
                SchoolBean.newBuilder().name("School2").build(),
                SchoolBean.newBuilder().name("School3").build(),
        };
        for (SchoolBean bean : mTestSchoolBeans) {
            mDao.mSchoolDao.save(bean).blockingFirst();
        }
        mTestSubschoolBeans = new SubschoolBean[]{
                SubschoolBean.newBuilder().name("Subschool1").schoolId(mTestSchoolBeans[0].getId
                        ()).build(),
                SubschoolBean.newBuilder().name("Subschool2").schoolId(mTestSchoolBeans[0].getId
                        ()).build(),
                SubschoolBean.newBuilder().name("Subschool3").schoolId(mTestSchoolBeans[1].getId
                        ()).build(),
                SubschoolBean.newBuilder().name("Subschool4").schoolId(mTestSchoolBeans[0].getId
                        ()).build(),
                SubschoolBean.newBuilder().name("Subschool5").schoolId(mTestSchoolBeans[2].getId
                        ()).build(),
        };
        for (SubschoolBean bean : mTestSubschoolBeans) {
            mDao.mSubschoolDao.save(bean).blockingFirst();
        }

        mTestSpellBaseBeans = new SpellBaseBean[]{
                SpellBaseBean.newBuilder()
                        .rulebookId(mTestRulebookBeans[0].getId())
                        .duration("duration")
                        .effect("effect")
                        .target("target")
                        .castingTime("castingTime")
                        .name("Spell1")
                        .page(42)
                        .range("range")
                        .build(),
                SpellBaseBean.newBuilder()
                        .rulebookId(mTestRulebookBeans[0].getId())
                        .duration("duration")
                        .effect("effect")
                        .target("target")
                        .castingTime("castingTime")
                        .name("Spell2")
                        .page(42)
                        .range("range")
                        .build(),
                SpellBaseBean.newBuilder()
                        .rulebookId(mTestRulebookBeans[1].getId())
                        .duration("duration")
                        .effect("effect")
                        .target("target")
                        .castingTime("castingTime")
                        .name("Spell3")
                        .page(1247)
                        .range("range")
                        .build(),
                SpellBaseBean.newBuilder()
                        .rulebookId(mTestRulebookBeans[1].getId())
                        .duration("duration")
                        .effect("effect")
                        .target("target")
                        .castingTime("castingTime")
                        .name("Spell4")
                        .page(42)
                        .range("range")
                        .build(),
                SpellBaseBean.newBuilder()
                        .rulebookId(mTestRulebookBeans[2].getId())
                        .duration("duration")
                        .effect("effect")
                        .target("target")
                        .castingTime("castingTime")
                        .name("Spell5")
                        .page(23)
                        .range("range")
                        .build(),
        };
        for (SpellBaseBean bean : mTestSpellBaseBeans) {
            mDao.mSpellBaseDao.save(bean).blockingFirst();
        }

        SpellsToCharacterClassesBean[] s2ccBeans = new SpellsToCharacterClassesBean[] {
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .characterClassId(mTestCharacterClassBeans[0].getId())
                        .level(1)
                        .build(),
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .characterClassId(mTestCharacterClassBeans[1].getId())
                        .extra("Extra1")
                        .level(1)
                        .build(),
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .characterClassId(mTestCharacterClassBeans[2].getId())
                        .level(5)
                        .build(),
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[1].getId())
                        .characterClassId(mTestCharacterClassBeans[0].getId())
                        .level(1)
                        .build(),
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[2].getId())
                        .characterClassId(mTestCharacterClassBeans[0].getId())
                        .level(3)
                        .build(),
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[3].getId())
                        .characterClassId(mTestCharacterClassBeans[0].getId())
                        .level(5)
                        .build(),
                SpellsToCharacterClassesBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[4].getId())
                        .characterClassId(mTestCharacterClassBeans[2].getId())
                        .level(5)
                        .build(),
        };
        for (SpellsToCharacterClassesBean bean : s2ccBeans) {
            mDao.mSpellsToCharacterClassesDao.save(bean).blockingFirst();
        }

        SpellsToComponentsBean[] s2c = new SpellsToComponentsBean[] {
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .componentId(mTestComponentBeans[0].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .componentId(mTestComponentBeans[1].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[1].getId())
                        .componentId(mTestComponentBeans[2].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[2].getId())
                        .componentId(mTestComponentBeans[0].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[2].getId())
                        .componentId(mTestComponentBeans[1].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[2].getId())
                        .componentId(mTestComponentBeans[2].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[3].getId())
                        .componentId(mTestComponentBeans[1].getId())
                        .build(),
                SpellsToComponentsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[4].getId())
                        .componentId(mTestComponentBeans[2].getId())
                        .build(),
        };
        for (SpellsToComponentsBean bean : s2c) {
            mDao.mSpellsToComponentsDao.save(bean).blockingFirst();
        }

        SpellsToDescriptorsBean[] s2d = new SpellsToDescriptorsBean[] {
                SpellsToDescriptorsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .descriptorId(mTestDescriptorBeans[0].getId())
                        .build(),
                SpellsToDescriptorsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[0].getId())
                        .descriptorId(mTestDescriptorBeans[1].getId())
                        .build(),
                SpellsToDescriptorsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[1].getId())
                        .descriptorId(mTestDescriptorBeans[0].getId())
                        .build(),
                SpellsToDescriptorsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[2].getId())
                        .descriptorId(mTestDescriptorBeans[1].getId())
                        .build(),
                SpellsToDescriptorsBean.newBuilder()
                        .spellId(mTestSpellBaseBeans[3].getId())
                        .descriptorId(mTestDescriptorBeans[2].getId())
                        .build(),
        };
        for (SpellsToDescriptorsBean bean : s2d) {
            mDao.mSpellsToDescriptorsDao.save(bean).blockingFirst();
        }

        SpellsToSchoolsBean[] s2s = new SpellsToSchoolsBean[] {
                SpellsToSchoolsBean.newBuilder()
                        .frSpellId(mTestSpellBaseBeans[0].getId())
                        .frSchoolId(mTestSchoolBeans[0].getId())
                        .build(),
                SpellsToSchoolsBean.newBuilder()
                        .frSpellId(mTestSpellBaseBeans[1].getId())
                        .frSchoolId(mTestSubschoolBeans[0].getSchoolId())
                        .frSubschoolId(mTestSubschoolBeans[0].getId())
                        .build(),
                SpellsToSchoolsBean.newBuilder()
                        .frSpellId(mTestSpellBaseBeans[2].getId())
                        .frSchoolId(mTestSchoolBeans[1].getId())
                        .build(),
                SpellsToSchoolsBean.newBuilder()
                        .frSpellId(mTestSpellBaseBeans[3].getId())
                        .frSchoolId(mTestSchoolBeans[2].getId())
                        .build(),
                SpellsToSchoolsBean.newBuilder()
                        .frSpellId(mTestSpellBaseBeans[4].getId())
                        .frSchoolId(mTestSubschoolBeans[2].getSchoolId())
                        .frSubschoolId(mTestSubschoolBeans[2].getId())
                        .build(),
        };
        for (SpellsToSchoolsBean bean : s2s) {
            mDao.mSpellsToSchoolsDao.save(bean).blockingFirst();
        }
    }

}
