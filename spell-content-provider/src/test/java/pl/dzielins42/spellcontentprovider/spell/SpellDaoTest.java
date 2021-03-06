package pl.dzielins42.spellcontentprovider.spell;

import android.database.sqlite.SQLiteDatabase;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import pl.dzielins42.spellcontentprovider.AbsDaoTest;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassWithLevelBean;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentColumns;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.component.ComponentWithExtraBean;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SpellDaoTest extends AbsDaoTest {

    private SpellDao mDao;

    private CharacterClassBean[] mTestCharacterClassBeans;
    private ComponentBean[] mTestComponentBeans;
    private DescriptorBean[] mTestDescriptorBeans;
    private RulebookBean[] mTestRulebookBeans;
    private SchoolBean[] mTestSchoolBeans;
    private SpellBaseBean[] mTestSpellBaseBeans;

    private Multimap<Integer, Integer> mSpells2Descriptors;
    private Multimap<Integer, Pair<Integer, String>> mSpells2Components;
    private Multimap<Integer, Triple<Integer, Integer, String>> mSpells2CharacterClasses;

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
        SpellsToCharacterClassesDao spellsToCharacterClassesDao = new SpellsToCharacterClassesDao(RuntimeEnvironment.application);
        SpellsToComponentsDao spellsToComponentsDao = new SpellsToComponentsDao(RuntimeEnvironment.application);
        SpellsToDescriptorsDao spellsToDescriptorsDao = new SpellsToDescriptorsDao(RuntimeEnvironment.application);
        mDao = new SpellDao(
                RuntimeEnvironment.application,
                characterClassDao,
                componentDao,
                descriptorDao,
                rulebookDao,
                schoolDao,
                spellBaseDao,
                spellCompositeDao,
                spellsToCharacterClassesDao,
                spellsToComponentsDao,
                spellsToDescriptorsDao
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
        SpellBean bean = getPresetBuilder().build();

        Long saveResult = mDao.save(bean).blockingGet();

        assertNotNull(saveResult);
        assertEquals(mTestSpellBaseBeans.length + 1, mDao.count().blockingGet().intValue());
        assertEquals(mTestSpellBaseBeans.length + 1, mDao.mSpellBaseDao.count().blockingGet().intValue());

        // Assert that no school rulebook etc. has been created
        assertEquals(mTestSchoolBeans.length, mDao.mSchoolDao.count().blockingGet().intValue());
        assertEquals(mTestRulebookBeans.length, mDao.mRulebookDao.count().blockingGet().intValue());
        assertEquals(mTestCharacterClassBeans.length, mDao.mCharacterClassDao.count().blockingGet().intValue());
        assertEquals(mTestDescriptorBeans.length, mDao.mDescriptorDao.count().blockingGet().intValue());
        assertEquals(mTestComponentBeans.length, mDao.mComponentDao.count().blockingGet().intValue());
    }

    @Override
    public void save_update() throws Exception {
        SpellBean bean = getPresetBuilder().build();

        Long insertId = mDao.save(bean).blockingGet();

        bean.setName(bean.getName()+"_modified");

        Long updateId = mDao.save(bean).blockingGet();

        assertEquals(insertId, updateId);

        SpellSelection selection = new SpellSelection();
        selection.id(insertId);
        SpellBean retrievedBean = mDao.get(selection).blockingFirst().get(0);

        assertNotNull(retrievedBean);
        assertEquals(bean, retrievedBean);
    }

    @Test
    public void save_new_descriptor() throws Exception {
        DescriptorBean descriptorBean = DescriptorBean.newBuilder().name("NewDescriptor1").build();
        SpellBean bean = getPresetBuilder()
                .descriptors(Arrays.asList(descriptorBean))
                .build();

        mDao.save(bean).blockingGet();

        assertEquals(mTestDescriptorBeans.length + 1, mDao.mDescriptorDao.count().blockingGet().intValue());
        assertNotEquals(0, bean.getDescriptors().get(0).getId());
    }

    @Test
    public void save_new_component() throws Exception {
        ComponentWithExtraBean componentWithExtraBean = ComponentWithExtraBean.builder()
                .name("NewComponent1")
                .extra(null)
                .build();
        SpellBean bean = getPresetBuilder()
                .components(Arrays.asList(componentWithExtraBean))
                .build();

        mDao.save(bean).blockingGet();

        assertEquals(mTestComponentBeans.length + 1, mDao.mComponentDao.count().blockingGet().intValue());
        assertNotEquals(0, bean.getComponents().get(0).getId());
    }

    @Test
    public void save_new_class() throws Exception {
        CharacterClassWithLevelBean characterClassWithLevelBean =
                CharacterClassWithLevelBean.builder()
                        .name("NewCharacterClass1")
                        .level(1)
                        .extra(null)
                        .build();
        SpellBean bean = getPresetBuilder()
                .characterClasses(Arrays.asList(characterClassWithLevelBean))
                .build();

        mDao.save(bean).blockingGet();

        assertEquals(mTestCharacterClassBeans.length + 1, mDao.mCharacterClassDao.count().blockingGet().intValue());
        assertNotEquals(0, bean.getCharacterClasses().get(0).getId());
    }

    @Override
    public void get_1() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.spellName("Spell1");
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        assertEquals(1, getResult.size());
    }

    @Test
    public void get_1_compare_with_SpellBaseBean() throws Exception {
        SpellBaseBean baseBean = mTestSpellBaseBeans[0];

        SpellSelection selection = new SpellSelection();
        selection.id(baseBean.getId());
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        SpellBean resultBean = getResult.get(0);

        assertEquals(baseBean.getId(), resultBean.getId());
        assertEquals(baseBean.getName(), resultBean.getName());
        assertEquals(baseBean.getSchoolId(), resultBean.getSchoolId());
        assertEquals(baseBean.getRulebookId(), resultBean.getRulebookId());
        assertEquals(baseBean.getPage(), resultBean.getPage());
        assertEquals(baseBean.getCastingTime(), resultBean.getCastingTime());
        assertEquals(baseBean.getRange(), resultBean.getRange());
        assertEquals(baseBean.getTarget(), resultBean.getTarget());
        assertEquals(baseBean.getEffect(), resultBean.getEffect());
        assertEquals(baseBean.getArea(), resultBean.getArea());
        assertEquals(baseBean.getDuration(), resultBean.getDuration());
        assertEquals(baseBean.getSavingThrow(), resultBean.getSavingThrow());
        assertEquals(baseBean.getSpellResistance(), resultBean.getSpellResistance());
        assertEquals(baseBean.getDescriptionPlain(), resultBean.getDescriptionPlain());
        assertEquals(baseBean.getDescriptionFormatted(), resultBean.getDescriptionFormatted());
        assertEquals(baseBean.getShortDescriptionPlain(), resultBean.getShortDescriptionPlain());
        assertEquals(baseBean.getShortDescriptionFormatted(), resultBean.getShortDescriptionFormatted());
        assertEquals(baseBean.getFlavourTextPlain(), resultBean.getFlavourTextPlain());
        assertEquals(baseBean.getFlavourTextFormatted(), resultBean.getFlavourTextFormatted());
        assertEquals(baseBean.getIsRitual(), resultBean.isRitual());
    }

    @Test
    public void get_1_compare_descriptors() throws Exception {
        SpellBaseBean baseBean = mTestSpellBaseBeans[0];
        Collection<Integer> descriptorIndices = mSpells2Descriptors.get(0);

        SpellSelection selection = new SpellSelection();
        selection.id(baseBean.getId());
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        SpellBean resultBean = getResult.get(0);

        assertEquals(descriptorIndices.size(), resultBean.getDescriptors().size());

        for (Integer descriptorIndex : descriptorIndices) {
            final DescriptorBean descriptorBean = mTestDescriptorBeans[descriptorIndex];
            assertTrue(resultBean.getDescriptors().contains(descriptorBean));
        }
    }

    @Test
    public void get_1_compare_components() throws Exception {
        SpellBaseBean baseBean = mTestSpellBaseBeans[0];
        Collection<Pair<Integer, String>> componentData = mSpells2Components.get(0);

        SpellSelection selection = new SpellSelection();
        selection.id(baseBean.getId());
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        SpellBean resultBean = getResult.get(0);

        assertEquals(componentData.size(), resultBean.getDescriptors().size());

        for (Pair<Integer, String> dataItem : componentData) {
            final ComponentWithExtraBean componentBean = ComponentWithExtraBean.builder()
                    .id(mTestComponentBeans[dataItem.getLeft()].getId())
                    .name(mTestComponentBeans[dataItem.getLeft()].getName())
                    .extra(dataItem.getRight())
                    .build();
            assertTrue(resultBean.getComponents().contains(componentBean));
        }
    }

    @Test
    public void get_1_compare_character_classes() throws Exception {
        SpellBaseBean baseBean = mTestSpellBaseBeans[0];
        Collection<Triple<Integer, Integer, String>> ccData = mSpells2CharacterClasses.get(0);

        SpellSelection selection = new SpellSelection();
        selection.id(baseBean.getId());
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        SpellBean resultBean = getResult.get(0);

        assertEquals(ccData.size(), resultBean.getCharacterClasses().size());

        for (Triple<Integer, Integer, String> dataItem : ccData) {
            final CharacterClassBean ccBean = mTestCharacterClassBeans[dataItem.getLeft()];
            final CharacterClassWithLevelBean ccwlBean = CharacterClassWithLevelBean.builder()
                    .id(ccBean.getId())
                    .name(ccBean.getName())
                    .level(dataItem.getMiddle())
                    .extra(dataItem.getRight())
                    .build();
            assertTrue(resultBean.getCharacterClasses().contains(ccwlBean));
        }
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
        int removeResult = mDao.remove(selection).blockingGet();

        assertEquals(3, removeResult);
        assertEquals(mTestSpellBaseBeans.length - 3, (int) mDao.count().blockingGet());
    }

    @Override
    public void remove_bean() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.id(mTestSpellBaseBeans[0].getId());
        List<SpellBean> getResult = mDao.get(selection).blockingFirst();
        SpellBean resultBean = getResult.get(0);

        Throwable exception = mDao.remove(resultBean).blockingGet();
        assertNull(exception);
        assertEquals(mTestSpellBaseBeans.length - 1, (int) mDao.count().blockingGet());
    }

    @Override
    public void count_all() throws Exception {
        assertEquals(
                mTestSpellBaseBeans.length, (int) mDao.count().blockingGet()
        );
        assertEquals(
                mTestSpellBaseBeans.length, (int) mDao.count(new SpellSelection()).blockingGet()
        );
    }

    @Override
    public void count_selection() throws Exception {
        SpellSelection selection = new SpellSelection();
        selection.spellName("Spell1", "Spell2", "Spell3");
        assertEquals(3, (int) mDao.count(selection).blockingGet());
    }

    @Override
    protected void clearAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + CharacterClassColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + ComponentColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + DescriptorColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + RulebookColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SchoolColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellBaseColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellsToCharacterClassesColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellsToComponentsColumns.TABLE_NAME + ";");
        db.execSQL("DELETE FROM " + SpellsToDescriptorsColumns.TABLE_NAME + ";");
    }

    private SpellBean.SpellBeanBuilder getPresetBuilder() {
        CharacterClassWithLevelBean characterClassWithLevelBean =
                CharacterClassWithLevelBean.builder()
                        .id(mTestCharacterClassBeans[0].getId())
                        .name(mTestCharacterClassBeans[0].getName())
                        .level(1)
                        .extra(null)
                        .build();
        ComponentWithExtraBean componentWithExtraBean =
                ComponentWithExtraBean.builder()
                        .id(mTestComponentBeans[0].getId())
                        .name(mTestComponentBeans[0].getName())
                        .extra(null)
                        .build();

        return SpellBean.builder()
                .name("Fireball of Sudden Death")
                .rulebookId(mTestRulebookBeans[0].getId())
                .rulebookName(mTestRulebookBeans[0].getName())
                .page(42)
                .castingTime("1 minute")
                .range("100 ft.")
                .target("-")
                .effect("Death")
                .area("20 ft.")
                .duration("-")
                .savingThrow("-")
                .spellResistance("-")
                .schoolId(mTestSchoolBeans[0].getId())
                .schoolMainTypeId(mTestSchoolBeans[0].getId())
                .schoolMainTypeName(mTestSchoolBeans[0].getName())
                .descriptors(Arrays.asList(mTestDescriptorBeans[0]))
                .components(Arrays.asList(componentWithExtraBean))
                .characterClasses(Arrays.asList(characterClassWithLevelBean));
    }

    private void initDatabase() {
        mTestCharacterClassBeans = new CharacterClassBean[]{
                CharacterClassBean.newBuilder().name("CharacterClass1").build(),
                CharacterClassBean.newBuilder().name("CharacterClass2").build(),
                CharacterClassBean.newBuilder().name("CharacterClass3").build(),
        };
        for (CharacterClassBean bean : mTestCharacterClassBeans) {
            mDao.mCharacterClassDao.save(bean).blockingGet();
        }

        mTestComponentBeans = new ComponentBean[]{
                ComponentBean.newBuilder().name("Component1").build(),
                ComponentBean.newBuilder().name("Component2").build(),
                ComponentBean.newBuilder().name("Component3").build(),
        };
        for (ComponentBean bean : mTestComponentBeans) {
            mDao.mComponentDao.save(bean).blockingGet();
        }

        mTestDescriptorBeans = new DescriptorBean[]{
                DescriptorBean.newBuilder().name("Descriptor1").build(),
                DescriptorBean.newBuilder().name("Descriptor2").build(),
                DescriptorBean.newBuilder().name("Descriptor3").build(),
        };
        for (DescriptorBean bean : mTestDescriptorBeans) {
            mDao.mDescriptorDao.save(bean).blockingGet();
        }

        mTestRulebookBeans = new RulebookBean[]{
                RulebookBean.newBuilder().name("Rulebook1").build(),
                RulebookBean.newBuilder().name("Rulebook2").build(),
                RulebookBean.newBuilder().name("Rulebook3").build(),
        };
        for (RulebookBean bean : mTestRulebookBeans) {
            mDao.mRulebookDao.save(bean).blockingGet();
        }

        mTestSchoolBeans = new SchoolBean[]{
                SchoolBean.newBuilder().name("School1").build(),
                SchoolBean.newBuilder().name("School2").build(),
                null,
        };
        mDao.mSchoolDao.save(mTestSchoolBeans[0]).blockingGet();
        mDao.mSchoolDao.save(mTestSchoolBeans[1]).blockingGet();
        mTestSchoolBeans[2] = SchoolBean.newBuilder().name("School3").parentId(mTestSchoolBeans[0].getId()).build();
        mDao.mSchoolDao.save(mTestSchoolBeans[2]).blockingGet();

        mTestSpellBaseBeans = new SpellBaseBean[]{
                SpellBaseBean.newBuilder()
                        .rulebookId(mTestRulebookBeans[0].getId())
                        .schoolId(mTestSchoolBeans[0].getId())
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
                        .schoolId(mTestSchoolBeans[1].getId())
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
                        .schoolId(mTestSchoolBeans[2].getId())
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
                        .schoolId(mTestSchoolBeans[2].getId())
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
                        .schoolId(mTestSchoolBeans[0].getId())
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
            mDao.mSpellBaseDao.save(bean).blockingGet();
        }

        mSpells2CharacterClasses  = MultimapBuilder.hashKeys().hashSetValues().build();
        mSpells2CharacterClasses.put(0, Triple.<Integer, Integer, String>of(0, 1, null));
        mSpells2CharacterClasses.put(0, Triple.<Integer, Integer, String>of(1, 1, "Extra1"));
        mSpells2CharacterClasses.put(0, Triple.<Integer, Integer, String>of(2, 5, null));
        mSpells2CharacterClasses.put(1, Triple.<Integer, Integer, String>of(0, 1, null));
        mSpells2CharacterClasses.put(2, Triple.<Integer, Integer, String>of(0, 3, null));
        mSpells2CharacterClasses.put(3, Triple.<Integer, Integer, String>of(0, 5, null));
        mSpells2CharacterClasses.put(4, Triple.<Integer, Integer, String>of(2, 5, null));
        for (Map.Entry<Integer, Triple<Integer, Integer, String>> entry : mSpells2CharacterClasses.entries()) {
            SpellsToCharacterClassesBean bean = SpellsToCharacterClassesBean.newBuilder()
                    .spellId(mTestSpellBaseBeans[entry.getKey()].getId())
                    .characterClassId(mTestCharacterClassBeans[entry.getValue().getLeft()].getId())
                    .level(entry.getValue().getMiddle())
                    .extra(entry.getValue().getRight())
                    .build();
            mDao.mSpellsToCharacterClassesDao.save(bean).blockingGet();
        }

        mSpells2Components = MultimapBuilder.hashKeys().hashSetValues().build();
        mSpells2Components.put(0, Pair.<Integer, String>of(0, null));
        mSpells2Components.put(0, Pair.of(1, "Extra1"));
        mSpells2Components.put(1, Pair.<Integer, String>of(2, null));
        mSpells2Components.put(2, Pair.<Integer, String>of(0, null));
        mSpells2Components.put(2, Pair.of(1, "Extra2"));
        mSpells2Components.put(2, Pair.<Integer, String>of(2, null));
        mSpells2Components.put(3, Pair.of(1, "Extra1"));
        mSpells2Components.put(4, Pair.<Integer, String>of(2, null));
        for (Map.Entry<Integer, Pair<Integer,String>> entry : mSpells2Components.entries()) {
            SpellsToComponentsBean bean = SpellsToComponentsBean.newBuilder()
                    .spellId(mTestSpellBaseBeans[entry.getKey()].getId())
                    .componentId(mTestComponentBeans[entry.getValue().getLeft()].getId())
                    .extra(entry.getValue().getRight())
                    .build();
            mDao.mSpellsToComponentsDao.save(bean).blockingGet();
        }

        mSpells2Descriptors = MultimapBuilder.hashKeys().hashSetValues().build();
        mSpells2Descriptors.put(0, 0);
        mSpells2Descriptors.put(0, 1);
        mSpells2Descriptors.put(1, 0);
        mSpells2Descriptors.put(2, 1);
        mSpells2Descriptors.put(3, 2);
        for (Map.Entry<Integer, Integer> entry : mSpells2Descriptors.entries()) {
            SpellsToDescriptorsBean bean = SpellsToDescriptorsBean.newBuilder()
                    .spellId(mTestSpellBaseBeans[entry.getKey()].getId())
                    .descriptorId(mTestDescriptorBeans[entry.getValue()].getId())
                    .build();
            mDao.mSpellsToDescriptorsDao.save(bean).blockingGet();
        }
    }

}
