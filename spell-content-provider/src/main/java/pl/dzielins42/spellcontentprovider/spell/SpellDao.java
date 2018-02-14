package pl.dzielins42.spellcontentprovider.spell;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import pl.dzielins42.spellcontentprovider.Dao;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.component.ComponentBean;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseDao;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseSelection;
import pl.dzielins42.spellcontentprovider.spellcomposite.SpellCompositeBean;
import pl.dzielins42.spellcontentprovider.spellcomposite.SpellCompositeDao;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesDao;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsDao;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsDao;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsDao;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolDao;

public class SpellDao implements Dao<SpellBean, SpellSelection> {

    @NonNull
    private final Context mContext;

    @NonNull
    protected final CharacterClassDao mCharacterClassDao;
    @NonNull
    protected final ComponentDao mComponentDao;
    @NonNull
    protected final DescriptorDao mDescriptorDao;
    @NonNull
    protected final RulebookDao mRulebookDao;
    @NonNull
    protected final SchoolDao mSchoolDao;
    @NonNull
    protected final SpellBaseDao mSpellBaseDao;
    @NonNull
    protected final SpellCompositeDao mSpellCompositeDao;
    @NonNull
    protected final SubschoolDao mSubschoolDao;
    @NonNull
    protected final SpellsToCharacterClassesDao mSpellsToCharacterClassesDao;
    @NonNull
    protected final SpellsToComponentsDao mSpellsToComponentsDao;
    @NonNull
    protected final SpellsToDescriptorsDao mSpellsToDescriptorsDao;
    @NonNull
    protected final SpellsToSchoolsDao mSpellsToSchoolsDao;

    public SpellDao(
            @NonNull Context context,
            @NonNull CharacterClassDao characterClassDao,
            @NonNull ComponentDao componentDao,
            @NonNull DescriptorDao descriptorDao,
            @NonNull RulebookDao rulebookDao,
            @NonNull SchoolDao schoolDao,
            @NonNull SpellBaseDao spellBaseDao,
            @NonNull SpellCompositeDao spellCompositeDao,
            @NonNull SubschoolDao subschoolDao,
            @NonNull SpellsToCharacterClassesDao spellsToCharacterClassesDao,
            @NonNull SpellsToComponentsDao spellsToComponentsDao,
            @NonNull SpellsToDescriptorsDao spellsToDescriptorsDao,
            @NonNull SpellsToSchoolsDao spellsToSchoolsDao
    ) {
        mContext = context;
        mCharacterClassDao = characterClassDao;
        mComponentDao = componentDao;
        mDescriptorDao = descriptorDao;
        mRulebookDao = rulebookDao;
        mSchoolDao = schoolDao;
        mSpellBaseDao = spellBaseDao;
        mSpellCompositeDao = spellCompositeDao;
        mSubschoolDao = subschoolDao;
        mSpellsToCharacterClassesDao = spellsToCharacterClassesDao;
        mSpellsToComponentsDao = spellsToComponentsDao;
        mSpellsToDescriptorsDao = spellsToDescriptorsDao;
        mSpellsToSchoolsDao = spellsToSchoolsDao;
    }

    @NonNull
    protected Context getContext() {
        return mContext;
    }

    @NonNull
    protected ContentResolver getContentResolver() {
        return mContext.getContentResolver();
    }

    @Override
    public Observable<Boolean> remove(@NonNull SpellBean spellBean) {
        // Simply remove from spell_base via SpellBaseDao
        return mSpellBaseDao.remove(new SpellBaseSelection().id(spellBean.getId())).map(new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) throws Exception {
                return integer == 1;
            }
        });
    }

    @Override
    public Observable<Integer> remove(@NonNull SpellSelection selection) {
        // Execute selection on SpellCompositeDao to get ids of spells to remove via SpellBaseDao
        return mSpellCompositeDao.get(selection)
                .flatMap(new Function<List<SpellCompositeBean>, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(List<SpellCompositeBean> spellCompositeBeans) throws Exception {
                        return Observable.fromIterable(spellCompositeBeans).map(new Function<SpellCompositeBean, Long>() {
                            @Override
                            public Long apply(SpellCompositeBean spellCompositeBean) throws Exception {
                                return spellCompositeBean.getId();
                            }
                        });
                    }
                })
                .distinct()
                .toList()
                .toObservable()
                .flatMap(new Function<List<Long>, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(List<Long> idsList) throws Exception {
                        long[] idsArray = new long[idsList.size()];
                        SpellBaseSelection inSelection = new SpellBaseSelection();
                        for (int i = 0; i < idsList.size(); i++) {
                            idsArray[i] = idsList.get(i);
                        }
                        inSelection.id(idsArray);
                        return mSpellBaseDao.remove(inSelection);
                    }
                });
    }

    @Override
    public Observable<List<SpellBean>> get(@NonNull SpellSelection selection) {
        // TODO implement
        // This operation requires multiple steps:
        // - get data from spell_composite
        // - split data by id (spell_composite may contain multiple rows for single spell_base row)
        // - merge each group into SpellBean
        return mSpellCompositeDao.get(selection)
                .flatMap(new Function<List<SpellCompositeBean>, ObservableSource<List<SpellBean>>>() {
                    @Override
                    public ObservableSource<List<SpellBean>> apply(List<SpellCompositeBean> spellCompositeBeans) throws Exception {
                        Multimap<Long, SpellCompositeBean> mapById =
                                MultimapBuilder.treeKeys().hashSetValues().build();

                        for (SpellCompositeBean spellCompositeBean : spellCompositeBeans) {
                            mapById.put(spellCompositeBean.getId(), spellCompositeBean);
                        }

                        return Observable.fromIterable(mapById.asMap().values())
                                .map(new Function<Collection<SpellCompositeBean>, SpellBean>() {
                                    @Override
                                    public SpellBean apply(Collection<SpellCompositeBean> spellCompositeBeans) throws Exception {
                                        return merge(spellCompositeBeans);
                                    }
                                })
                                .toList()
                                .toObservable();
                    }
                });
    }

    protected SpellBean merge(Collection<SpellCompositeBean> beans) {
        SpellBean.Builder spellBeanBuilder = SpellBean.newBuilder();

        Long id=null;
        Long spellId=null;
        String spellName=null;
        Long rulebookId=null;
        String rulebookName=null;
        Integer spellPage=null;
        String spellCastingTime=null;
        String spellRange=null;
        String spellTarget=null;
        String spellEffect=null;
        String spellArea=null;
        String spellDuration=null;
        String spellSavingThrow=null;
        String spellSpellResistance=null;
        String spellDescriptionPlain=null;
        String spellDescriptionFormatted=null;
        String spellShortDescriptionPlain=null;
        String spellShortDescriptionFormatted=null;
        String spellFlavourTextPlain=null;
        String spellFlavourTextFormatted=null;
        Boolean spellIsRitual=null;

        Long characterClassId=null;
        String characterClassName=null;
        Integer characterClassLevel=null;
        String characterClassExtra=null;
        Set<ComponentBean> components = new HashSet<>();
        Set<DescriptorBean> descriptors = new HashSet<>();
        Long shoolId=null;
        String schoolName=null;
        Long subschoolId=null;
        String subschoolName=null;
        
        for (SpellCompositeBean bean : beans) {
            // Simple fields

            if (id == null) {
                id = bean.getId();
            } else {
                if (bean.getId() != id) {
                    throw new IllegalStateException("'id' inconsistency");
                }
            }
            if (spellId == null) {
                spellId = bean.getSpellId();
            } else {
                if (bean.getSpellId() != spellId) {
                    throw new IllegalStateException("'spellId' inconsistency");
                }
            }
            if (spellName == null) {
                spellName = bean.getSpellName();
            } else {
                if (!spellName.equals(bean.getSpellName())) {
                    throw new IllegalStateException("'spellName' inconsistency");
                }
            }
            if (rulebookId == null) {
                rulebookId = bean.getRulebookId();
            } else {
                if (bean.getRulebookId() != rulebookId) {
                    throw new IllegalStateException("'rulebookId' inconsistency");
                }
            }
            if (rulebookName == null) {
                rulebookName = bean.getRulebookName();
            } else {
                if (!rulebookName.equals(bean.getRulebookName())) {
                    throw new IllegalStateException("'rulebookName' inconsistency");
                }
            }
            if (spellPage == null) {
                spellPage = bean.getSpellPage();
            } else {
                if (bean.getSpellPage() != spellPage) {
                    throw new IllegalStateException("'spellPage' inconsistency");
                }
            }
            if (spellCastingTime == null) {
                spellCastingTime = bean.getSpellCastingTime();
            } else {
                if (!spellCastingTime.equals(bean.getSpellCastingTime())) {
                    throw new IllegalStateException("'spellCastingTime' inconsistency");
                }
            }
            if (spellRange == null) {
                spellRange = bean.getSpellRange();
            } else {
                if (!spellRange.equals(bean.getSpellRange())) {
                    throw new IllegalStateException("'spellRange' inconsistency");
                }
            }
            if (spellTarget == null) {
                spellTarget = bean.getSpellTarget();
            } else {
                if (!spellTarget.equals(bean.getSpellTarget())) {
                    throw new IllegalStateException("'spellTarget' inconsistency");
                }
            }
            if (spellEffect == null) {
                spellEffect = bean.getSpellEffect();
            } else {
                if (!spellEffect.equals(bean.getSpellEffect())) {
                    throw new IllegalStateException("'spellEffect' inconsistency");
                }
            }
            if (spellArea == null) {
                spellArea = bean.getSpellArea();
            } else {
                if (!spellArea.equals(bean.getSpellArea())) {
                    throw new IllegalStateException("'spellArea' inconsistency");
                }
            }
            if (spellDuration == null) {
                spellDuration = bean.getSpellDuration();
            } else {
                if (!spellDuration.equals(bean.getSpellDuration())) {
                    throw new IllegalStateException("'spellDuration' inconsistency");
                }
            }
            if (spellSavingThrow == null) {
                spellSavingThrow = bean.getSpellSavingThrow();
            } else {
                if (!spellSavingThrow.equals(bean.getSpellSavingThrow())) {
                    throw new IllegalStateException("'spellSavingThrow' inconsistency");
                }
            }
            if (spellSpellResistance == null) {
                spellSpellResistance = bean.getSpellSpellResistance();
            } else {
                if (!spellSpellResistance.equals(bean.getSpellSpellResistance())) {
                    throw new IllegalStateException("'spellSpellResistance' inconsistency");
                }
            }
            if (spellDescriptionPlain == null) {
                spellDescriptionPlain = bean.getSpellDescriptionPlain();
            } else {
                if (!spellDescriptionPlain.equals(bean.getSpellDescriptionPlain())) {
                    throw new IllegalStateException("'spellDescriptionPlain' inconsistency");
                }
            }
            if (spellDescriptionFormatted == null) {
                spellDescriptionFormatted = bean.getSpellDescriptionFormatted();
            } else {
                if (!spellDescriptionFormatted.equals(bean.getSpellDescriptionFormatted())) {
                    throw new IllegalStateException("'spellDescriptionFormatted' inconsistency");
                }
            }
            if (spellShortDescriptionPlain == null) {
                spellShortDescriptionPlain = bean.getSpellShortDescriptionPlain();
            } else {
                if (!spellShortDescriptionPlain.equals(bean.getSpellShortDescriptionPlain())) {
                    throw new IllegalStateException("'spellShortDescriptionPlain' inconsistency");
                }
            }
            if (spellShortDescriptionFormatted == null) {
                spellShortDescriptionFormatted = bean.getSpellShortDescriptionFormatted();
            } else {
                if (!spellShortDescriptionFormatted.equals(bean.getSpellShortDescriptionFormatted())) {
                    throw new IllegalStateException("'spellShortDescriptionFormatted' inconsistency");
                }
            }
            if (spellFlavourTextPlain == null) {
                spellFlavourTextPlain = bean.getSpellFlavourTextPlain();
            } else {
                if (!spellFlavourTextPlain.equals(bean.getSpellFlavourTextPlain())) {
                    throw new IllegalStateException("'spellFlavourTextPlain' inconsistency");
                }
            }
            if (spellFlavourTextFormatted == null) {
                spellFlavourTextFormatted = bean.getSpellFlavourTextFormatted();
            } else {
                if (!spellFlavourTextFormatted.equals(bean.getSpellFlavourTextFormatted())) {
                    throw new IllegalStateException("'spellFlavourTextFormatted' inconsistency");
                }
            }
            if (spellIsRitual == null) {
                spellIsRitual = bean.getSpellIsRitual();
            } else {
                if (bean.getSpellIsRitual() != spellIsRitual) {
                    throw new IllegalStateException("'spellIsRitual' inconsistency");
                }
            }

            // Character class + level + extra

            // Components
            if (bean.getComponentId() != 0 && bean.getComponentName() != null) {
                components.add(ComponentBean.newInstance(
                        bean.getComponentId(), bean.getComponentName(), null
                ));
            }

            // Descriptors
            if (bean.getDescriptorId() != 0 && bean.getDescriptorName() != null) {
                descriptors.add(DescriptorBean.newInstance(
                        bean.getDescriptorId(), bean.getDescriptorName()
                ));
            }

            // Schools + subschools

        }

        spellBeanBuilder
                .id(id)
                .spellId(spellId)
                .spellName(spellName)
                .rulebookId(rulebookId)
                .rulebookName(rulebookName)
                .spellPage(spellPage)
                .spellCastingTime(spellCastingTime)
                .spellRange(spellRange)
                .spellTarget(spellTarget)
                .spellEffect(spellEffect)
                .spellArea(spellArea)
                .spellDuration(spellDuration)
                .spellSavingThrow(spellSavingThrow)
                .spellSpellResistance(spellSpellResistance)
                .spellDescriptionPlain(spellDescriptionPlain)
                .spellDescriptionFormatted(spellDescriptionFormatted)
                .spellShortDescriptionPlain(spellShortDescriptionPlain)
                .spellShortDescriptionFormatted(spellShortDescriptionFormatted)
                .spellFlavourTextPlain(spellFlavourTextPlain)
                .spellFlavourTextFormatted(spellFlavourTextFormatted)
                .spellIsRitual(spellIsRitual);

        return spellBeanBuilder.build();
    }

    @Override
    public Observable<Long> save(@NonNull SpellBean spellBean) {
        // TODO implement
        // This operation requires multiple steps:
        // - check if new data is required in other tables, insert if needed
        // - save to spell_base
        return null;
    }

    @Override
    public Observable<Integer> count() {
        // Simply count via SpellBaseDao
        return mSpellBaseDao.count();
    }

    @Override
    public Observable<Integer> count(@NonNull SpellSelection selection) {
        // Simply count via SpellCompositeDao
        return mSpellCompositeDao.count(selection);
    }
}
