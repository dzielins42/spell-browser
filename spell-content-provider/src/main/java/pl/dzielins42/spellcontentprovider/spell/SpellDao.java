package pl.dzielins42.spellcontentprovider.spell;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import pl.dzielins42.spellcontentprovider.Dao;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassBean;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassWithLevelBean;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.component.ComponentWithExtraBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorBean;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookBean;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.school.SchoolBean;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseBean;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseDao;
import pl.dzielins42.spellcontentprovider.spellbase.SpellBaseSelection;
import pl.dzielins42.spellcontentprovider.spellcomposite.SpellCompositeBean;
import pl.dzielins42.spellcontentprovider.spellcomposite.SpellCompositeDao;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesBean;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesDao;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsDao;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsDao;

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
    protected final SpellsToCharacterClassesDao mSpellsToCharacterClassesDao;
    @NonNull
    protected final SpellsToComponentsDao mSpellsToComponentsDao;
    @NonNull
    protected final SpellsToDescriptorsDao mSpellsToDescriptorsDao;

    public SpellDao(
            @NonNull Context context,
            @NonNull CharacterClassDao characterClassDao,
            @NonNull ComponentDao componentDao,
            @NonNull DescriptorDao descriptorDao,
            @NonNull RulebookDao rulebookDao,
            @NonNull SchoolDao schoolDao,
            @NonNull SpellBaseDao spellBaseDao,
            @NonNull SpellCompositeDao spellCompositeDao,
            @NonNull SpellsToCharacterClassesDao spellsToCharacterClassesDao,
            @NonNull SpellsToComponentsDao spellsToComponentsDao,
            @NonNull SpellsToDescriptorsDao spellsToDescriptorsDao
    ) {
        mContext = context;
        mCharacterClassDao = characterClassDao;
        mComponentDao = componentDao;
        mDescriptorDao = descriptorDao;
        mRulebookDao = rulebookDao;
        mSchoolDao = schoolDao;
        mSpellBaseDao = spellBaseDao;
        mSpellCompositeDao = spellCompositeDao;
        mSpellsToCharacterClassesDao = spellsToCharacterClassesDao;
        mSpellsToComponentsDao = spellsToComponentsDao;
        mSpellsToDescriptorsDao = spellsToDescriptorsDao;
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
        // This operation requires multiple steps:
        // - get data from spell_composite
        // - split data by id (spell_composite may contain multiple rows for single spell_base row)
        // - merge each group into SpellBean
        return mSpellCompositeDao.get(selection)
                .flatMap(new Function<List<SpellCompositeBean>, ObservableSource<List<SpellBean>>>() {
                    @Override
                    public ObservableSource<List<SpellBean>> apply(List<SpellCompositeBean> spellCompositeBeans) throws Exception {
                        Multimap<Long, SpellCompositeBean> mapById =
                                MultimapBuilder.treeKeys().arrayListValues().build();

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
        Long id = null;
        Long spellId = null;
        String spellName = null;
        Long rulebookId = null;
        String rulebookName = null;
        Long schoolId = null;
        Long schoolMainTypeId = null;
        String schoolMainTypeName = null;
        Long schoolSubTypeId = null;
        String schoolSubTypeName = null;
        Integer spellPage = null;
        String spellCastingTime = null;
        String spellRange = null;
        String spellTarget = null;
        String spellEffect = null;
        String spellArea = null;
        String spellDuration = null;
        String spellSavingThrow = null;
        String spellSpellResistance = null;
        String spellDescriptionPlain = null;
        String spellDescriptionFormatted = null;
        String spellShortDescriptionPlain = null;
        String spellShortDescriptionFormatted = null;
        String spellFlavourTextPlain = null;
        String spellFlavourTextFormatted = null;
        Boolean spellIsRitual = null;

        Set<ComponentWithExtraBean> components = new HashSet<>();
        Set<DescriptorBean> descriptors = new HashSet<>();
        Set<CharacterClassWithLevelBean> characterClasses = new HashSet<>();

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

            if (spellId != id) {
                throw new IllegalStateException("'id' and 'spellId' inconsistency");
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
            if (schoolId == null) {
                schoolId = bean.getSchoolId();
            } else {
                if (bean.getSchoolId() != schoolId) {
                    throw new IllegalStateException("'schoolId' inconsistency");
                }
            }
            if (schoolMainTypeId == null) {
                schoolMainTypeId = bean.getSchoolLevel1Id();
            } else {
                if (bean.getSchoolLevel1Id() != schoolMainTypeId) {
                    throw new IllegalStateException("'schoolMainTypeId' inconsistency");
                }
            }
            if (schoolMainTypeName == null) {
                schoolMainTypeName = bean.getSchoolLevel1Name();
            } else {
                if (!TextUtils.equals(bean.getSchoolLevel1Name(), schoolMainTypeName)) {
                    throw new IllegalStateException("'schoolMainTypeName' inconsistency");
                }
            }
            if (schoolSubTypeId == null) {
                schoolSubTypeId = bean.getSchoolLevel2Id();
            } else {
                if (bean.getSchoolLevel2Id() != schoolSubTypeId) {
                    throw new IllegalStateException("'schoolSubTypeId' inconsistency");
                }
            }
            if (schoolSubTypeName == null) {
                schoolSubTypeName = bean.getSchoolLevel2Name();
            } else {
                if (!TextUtils.equals(bean.getSchoolLevel2Name(), schoolSubTypeName)) {
                    throw new IllegalStateException("'schoolSubTypeName' inconsistency");
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
                if (!TextUtils.equals(spellCastingTime, bean.getSpellCastingTime())) {
                    throw new IllegalStateException("'spellCastingTime' inconsistency");
                }
            }
            if (spellRange == null) {
                spellRange = bean.getSpellRange();
            } else {
                if (!TextUtils.equals(spellRange, bean.getSpellRange())) {
                    throw new IllegalStateException("'spellRange' inconsistency");
                }
            }
            if (spellTarget == null) {
                spellTarget = bean.getSpellTarget();
            } else {
                if (!TextUtils.equals(spellTarget, bean.getSpellTarget())) {
                    throw new IllegalStateException("'spellTarget' inconsistency");
                }
            }
            if (spellEffect == null) {
                spellEffect = bean.getSpellEffect();
            } else {
                if (!TextUtils.equals(spellEffect, bean.getSpellEffect())) {
                    throw new IllegalStateException("'spellEffect' inconsistency");
                }
            }
            if (spellArea == null) {
                spellArea = bean.getSpellArea();
            } else {
                if (!TextUtils.equals(spellArea, bean.getSpellArea())) {
                    throw new IllegalStateException("'spellArea' inconsistency");
                }
            }
            if (spellDuration == null) {
                spellDuration = bean.getSpellDuration();
            } else {
                if (!TextUtils.equals(spellDuration, bean.getSpellDuration())) {
                    throw new IllegalStateException("'spellDuration' inconsistency");
                }
            }
            if (spellSavingThrow == null) {
                spellSavingThrow = bean.getSpellSavingThrow();
            } else {
                if (!TextUtils.equals(spellSavingThrow, bean.getSpellSavingThrow())) {
                    throw new IllegalStateException("'spellSavingThrow' inconsistency");
                }
            }
            if (spellSpellResistance == null) {
                spellSpellResistance = bean.getSpellSpellResistance();
            } else {
                if (!TextUtils.equals(spellSpellResistance, bean.getSpellSpellResistance())) {
                    throw new IllegalStateException("'spellSpellResistance' inconsistency");
                }
            }
            if (spellDescriptionPlain == null) {
                spellDescriptionPlain = bean.getSpellDescriptionPlain();
            } else {
                if (!TextUtils.equals(spellDescriptionPlain, bean.getSpellDescriptionPlain())) {
                    throw new IllegalStateException("'spellDescriptionPlain' inconsistency");
                }
            }
            if (spellDescriptionFormatted == null) {
                spellDescriptionFormatted = bean.getSpellDescriptionFormatted();
            } else {
                if (!TextUtils.equals(spellDescriptionFormatted, bean.getSpellDescriptionFormatted())) {
                    throw new IllegalStateException("'spellDescriptionFormatted' inconsistency");
                }
            }
            if (spellShortDescriptionPlain == null) {
                spellShortDescriptionPlain = bean.getSpellShortDescriptionPlain();
            } else {
                if (!TextUtils.equals(spellShortDescriptionPlain, bean.getSpellShortDescriptionPlain())) {
                    throw new IllegalStateException("'spellShortDescriptionPlain' inconsistency");
                }
            }
            if (spellShortDescriptionFormatted == null) {
                spellShortDescriptionFormatted = bean.getSpellShortDescriptionFormatted();
            } else {
                if (!TextUtils.equals(spellShortDescriptionFormatted, bean.getSpellShortDescriptionFormatted())) {
                    throw new IllegalStateException("'spellShortDescriptionFormatted' inconsistency");
                }
            }
            if (spellFlavourTextPlain == null) {
                spellFlavourTextPlain = bean.getSpellFlavourTextPlain();
            } else {
                if (!TextUtils.equals(spellFlavourTextPlain, bean.getSpellFlavourTextPlain())) {
                    throw new IllegalStateException("'spellFlavourTextPlain' inconsistency");
                }
            }
            if (spellFlavourTextFormatted == null) {
                spellFlavourTextFormatted = bean.getSpellFlavourTextFormatted();
            } else {
                if (!TextUtils.equals(spellFlavourTextFormatted, bean.getSpellFlavourTextFormatted())) {
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
            characterClasses.add(CharacterClassWithLevelBean.builder()
                                         .classId(bean.getCharacterClassId())
                                         .className(bean.getCharacterClassName())
                                         .level(bean.getCharacterClassLevel())
                                         .extra(bean.getCharacterClassExtra())
                                         .build()
            );

            // Components
            if (bean.getComponentId() != 0 && bean.getComponentName() != null) {
                components.add(ComponentWithExtraBean.builder()
                                       .id(bean.getComponentId())
                                       .name(bean.getComponentName())
                                       .extra(bean.getComponentExtra())
                                       .build()
                );
            }

            // Descriptors
            if (bean.getDescriptorId() != null && bean.getDescriptorId() != 0 && bean.getDescriptorName() != null) {
                descriptors.add(DescriptorBean.newInstance(
                        bean.getDescriptorId(), bean.getDescriptorName()
                ));
            }
        }

        return SpellBean.builder()
                .id(id)
                .name(spellName)
                .rulebookId(rulebookId)
                .rulebookName(rulebookName)

                .schoolId(schoolId)
                .schoolMainTypeId(schoolMainTypeId)
                .schoolMainTypeName(schoolMainTypeName)
                .schoolSubTypeId(schoolSubTypeId)
                .schoolSubTypeName(schoolSubTypeName)

                .page(spellPage)
                .castingTime(spellCastingTime)
                .range(spellRange)
                .target(spellTarget)
                .effect(spellEffect)
                .area(spellArea)
                .duration(spellDuration)
                .savingThrow(spellSavingThrow)
                .spellResistance(spellSpellResistance)
                .descriptionPlain(spellDescriptionPlain)
                .descriptionFormatted(spellDescriptionFormatted)
                .shortDescriptionPlain(spellShortDescriptionPlain)
                .shortDescriptionFormatted(spellShortDescriptionFormatted)
                .flavourTextPlain(spellFlavourTextPlain)
                .flavourTextFormatted(spellFlavourTextFormatted)
                .isRitual(spellIsRitual)
                .components(new ArrayList(components))
                .descriptors(new ArrayList(descriptors))
                .characterClasses(new ArrayList(characterClasses))
                .build();
    }

    @Override
    public Observable<Long> save(@NonNull final SpellBean spellBean) {
        // TODO implement
        // This operation requires multiple steps:
        // - check if new data is required in other tables, insert if needed
        // - save to spell_base

        // Save school(s), components, descriptors and character classes
        // It is assumed that higher layer takes care of data consistency, so if spellBean
        // contains for example existing descriptor but with changed name, it will be overwritten
        // for this and other records

        /*
        List<Observable<Long>> firstPhase = new ArrayList<>();
        // Prepare schools saving
        final SchoolBean schoolMainType = SchoolBean.newInstance(
                spellBean.getSchoolMainTypeId(),
                spellBean.getSchoolMainTypeName(),
                null
        );
        final boolean hasSubType = spellBean.getSchoolSubTypeId() != null
                && !TextUtils.isEmpty(spellBean.getSchoolSubTypeName());
        final SchoolBean schoolSubType = !hasSubType ? null : SchoolBean.newInstance(
                spellBean.getSchoolSubTypeId(),
                spellBean.getSchoolSubTypeName(),
                spellBean.getSchoolMainTypeId()
        );
        Observable<Long> saveSchool = mSchoolDao.save(schoolMainType);
        if (hasSubType) {
            saveSchool = saveSchool.concatWith(mSchoolDao.save(schoolSubType));
        }
        firstPhase.add(saveSchool);
        // Prepare descriptors saving
        for (DescriptorBean descriptorBean : spellBean.getDescriptors()) {
            firstPhase.add(mDescriptorDao.save(descriptorBean));
        }
        // Prepare components saving
        for (ComponentWithExtraBean componentWithExtraBean : spellBean.getComponents()) {
            firstPhase.add(mComponentDao.save(ComponentBean.newInstance(
                    componentWithExtraBean.getId(), componentWithExtraBean.getName()
            )));
        }
        // Prepare character class saving
        for (CharacterClassWithLevelBean characterClassBean : spellBean.getCharacterClasses()) {
            firstPhase.add(mCharacterClassDao.save(CharacterClassBean.newInstance(
                    characterClassBean.getClassId(), characterClassBean.getClassName()
            )));
        }
        */
        /*
        List<ObservableSource<Long>> o1 = new ArrayList<>();
        for (DescriptorBean descriptorBean:spellBean.getDescriptors()){
            o1.add(mDescriptorDao.save(descriptorBean));
        }
        for(ComponentBean componentBean:spellBean.getComponents()){
            o1.add(mComponentDao.save(componentBean));
        }
        */

        final SavingContext savingContext = new SavingContext();

        final Observable<Long> saveObservable = Observable.just(1L);

        // 1. Prepare school(s) and rulebook saving
        final SchoolBean schoolMainType = SchoolBean.newInstance(
                spellBean.getSchoolMainTypeId(),
                spellBean.getSchoolMainTypeName(),
                null
        );
        final boolean hasSubType = spellBean.getSchoolSubTypeId() != null
                && !TextUtils.isEmpty(spellBean.getSchoolSubTypeName());
        Observable<Boolean> saveSchool = mSchoolDao.save(schoolMainType)
                .map(new Function<Long, Boolean>() {
                    @Override
                    public Boolean apply(Long schoolMainTypeId) throws Exception {
                        savingContext.schoolMainTypeId = schoolMainTypeId;
                        return true;
                    }
                });
        if (hasSubType) {
            saveSchool = saveSchool.flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                @Override
                public ObservableSource<Boolean> apply(Boolean bool) throws Exception {
                    final SchoolBean schoolSubType = SchoolBean.newInstance(
                            spellBean.getSchoolSubTypeId(),
                            spellBean.getSchoolSubTypeName(),
                            savingContext.schoolMainTypeId
                    );

                    return mSchoolDao.save(schoolSubType)
                            .map(new Function<Long, Boolean>() {
                                @Override
                                public Boolean apply(Long schoolSubTypeId) throws Exception {
                                    savingContext.schoolSubTypeId = schoolSubTypeId;
                                    return true;
                                }
                            });
                }
            });
        }
        final RulebookBean rulebook = RulebookBean.newInstance(
                spellBean.getRulebookId(),
                spellBean.getRulebookName()
        );
        Observable<Boolean> saveRulebook = mRulebookDao.save(rulebook)
                .map(new Function<Long, Boolean>() {
                    @Override
                    public Boolean apply(Long rulebookId) throws Exception {
                        savingContext.rulebookId = rulebookId;
                        return true;
                    }
                });

        // 2. Prepare base spell saving
        // Using school and ruelbook ids
        Observable<Long> saveBaseSpell =
                Observable.zip(
                        saveSchool,
                        saveRulebook,
                        new BiFunction<Boolean, Boolean, Boolean>() {
                            @Override
                            public Boolean apply(
                                    Boolean schoolSaved,
                                    Boolean rulebookSaved
                            ) throws Exception {
                                return true;
                            }
                        }
                ).flatMap(new Function<Boolean, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(
                            Boolean schoolAndRulebookSaved
                    ) throws Exception {
                        long schoolId = (savingContext.schoolSubTypeId != null && savingContext.schoolSubTypeId != 0) ? savingContext.schoolSubTypeId : savingContext.schoolMainTypeId;
                        final SpellBaseBean spellBase = SpellBaseBean.newBuilder()
                                .schoolId(schoolId)
                                .spellResistance(spellBean.getSpellResistance())
                                .savingThrow(spellBean.getSavingThrow())
                                .range(spellBean.getRange())
                                .page(spellBean.getPage())
                                .name(spellBean.getName())
                                .area(spellBean.getArea())
                                .castingTime(spellBean.getCastingTime())
                                .target(spellBean.getTarget())
                                .effect(spellBean.getEffect())
                                .duration(spellBean.getDuration())
                                .rulebookId(savingContext.rulebookId)
                                .descriptionFormatted(spellBean.getDescriptionFormatted())
                                .descriptionPlain(spellBean.getDescriptionPlain())
                                .flavourTextFormatted(spellBean.getFlavourTextFormatted())
                                .flavourTextPlain(spellBean.getFlavourTextPlain())
                                .id(spellBean.getId())
                                .isRitual(spellBean.isRitual())
                                .shortDescriptionFormatted(spellBean.getShortDescriptionFormatted())
                                .shortDescriptionPlain(spellBean.getShortDescriptionPlain())
                                .build();

                        return mSpellBaseDao.save(spellBase);
                    }
                });

        // 3. Prepare character classes saving
        Single<List<Long>> saveCharacterClasses= Observable.merge(
                getSaveObservablesForCharacterClasses(spellBean)
        ).toList();

        // 4. Prepare joining base spell with character classes
        Observable<Boolean> saving = Observable.zip(saveBaseSpell, saveCharacterClasses.toObservable(), new BiFunction<Long, List<Long>, Boolean>() {
            @Override
            public Boolean apply(Long baseSpellId, List<Long> characterClassesIds) throws Exception {
                savingContext.baseSpellId = baseSpellId;
                savingContext.characterClassesIds = characterClassesIds;
                return true;
            }
        })
                .flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Boolean aBoolean) throws Exception {
                        List<Observable<Long>> saveObservables = new ArrayList<>();
                        // Spells to character classes
                        for (int i = 0; i < spellBean.getCharacterClasses().size(); i++) {
                            saveObservables.add(mSpellsToCharacterClassesDao.save(
                                    SpellsToCharacterClassesBean.newBuilder()
                                            .spellId(savingContext.baseSpellId)
                                            .characterClassId(savingContext.characterClassesIds.get(i))
                                            .level(spellBean.getCharacterClasses().get(i).getLevel())
                                            .extra(spellBean.getCharacterClasses().get(i).getExtra())
                                            .build()
                            ));
                        }

                        return Observable.zip(saveObservables, new Function<Object[], Boolean>() {
                            @Override
                            public Boolean apply(Object[] objects) throws Exception {
                                return true;
                            }
                        });
                    }
                });

        return saving.map(new Function<Boolean, Long>() {
            @Override
            public Long apply(Boolean aBoolean) throws Exception {
                return savingContext.baseSpellId;
            }
        });
    }

    private List<Observable<Long>> getSaveObservablesForCharacterClasses(SpellBean spellBean) {
        List<Observable<Long>> list = new ArrayList<>(spellBean.getCharacterClasses().size());
        for (CharacterClassWithLevelBean characterClassBean : spellBean.getCharacterClasses()) {
            list.add(mCharacterClassDao.save(CharacterClassBean.newInstance(
                    characterClassBean.getClassId(), characterClassBean.getClassName()
            )));
        }

        return list;
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

    /**
     * Class holding various data related to complex process of saving
     */
    private static class SavingContext {
        Long schoolMainTypeId;
        Long schoolSubTypeId;
        Long rulebookId;
        Long baseSpellId;
        List<Long> characterClassesIds;
    }

}
