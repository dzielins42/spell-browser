package pl.dzielins42.spellbrowser.data.local;

import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.dzielins42.spellbrowser.data.local.component.ComponentContentValues;
import pl.dzielins42.spellbrowser.data.local.component.ComponentCursor;
import pl.dzielins42.spellbrowser.data.local.component.ComponentSelection;
import pl.dzielins42.spellbrowser.data.local.descriptor.DescriptorContentValues;
import pl.dzielins42.spellbrowser.data.local.descriptor.DescriptorCursor;
import pl.dzielins42.spellbrowser.data.local.descriptor.DescriptorSelection;
import pl.dzielins42.spellbrowser.data.local.rulebook.RulebookContentValues;
import pl.dzielins42.spellbrowser.data.local.rulebook.RulebookCursor;
import pl.dzielins42.spellbrowser.data.local.rulebook.RulebookSelection;
import pl.dzielins42.spellbrowser.data.local.school.SchoolContentValues;
import pl.dzielins42.spellbrowser.data.local.school.SchoolCursor;
import pl.dzielins42.spellbrowser.data.local.school.SchoolSelection;
import pl.dzielins42.spellbrowser.data.local.spell.SpellColumns;
import pl.dzielins42.spellbrowser.data.local.spell.SpellContentValues;
import pl.dzielins42.spellbrowser.data.local.spell.SpellCursor;
import pl.dzielins42.spellbrowser.data.local.spell.SpellSelection;
import pl.dzielins42.spellbrowser.data.local.spellstocomponents.SpellsToComponentsContentValues;
import pl.dzielins42.spellbrowser.data.local.spellstocomponents.SpellsToComponentsSelection;
import pl.dzielins42.spellbrowser.data.local.spellstodescriptors.SpellsToDescriptorsContentValues;
import pl.dzielins42.spellbrowser.data.local.spellstodescriptors.SpellsToDescriptorsSelection;
import pl.dzielins42.spellbrowser.data.local.spellstoschools.SpellsToSchoolsContentValues;
import pl.dzielins42.spellbrowser.data.local.spellstoschools.SpellsToSchoolsSelection;
import pl.dzielins42.spellbrowser.data.local.spellstosubschools.SpellsToSubSchoolsContentValues;
import pl.dzielins42.spellbrowser.data.local.spellstosubschools.SpellsToSubSchoolsSelection;
import pl.dzielins42.spellbrowser.data.local.subschool.SubSchoolContentValues;
import pl.dzielins42.spellbrowser.data.local.subschool.SubSchoolCursor;
import pl.dzielins42.spellbrowser.data.local.subschool.SubSchoolSelection;
import pl.dzielins42.spellbrowser.data.model.FormattedString;
import pl.dzielins42.spellbrowser.data.model.SourceLevelExtraTriplet;
import pl.dzielins42.spellbrowser.data.model.Spell;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func4;
import rx.joins.Plan0;
import rx.observables.ConnectableObservable;
import rx.observables.JoinObservable;
import rx.schedulers.Schedulers;

@Singleton
public class SpellDbService {

    private List<Spell> DUMMY;

    private final Context mContext;

    @Inject
    public SpellDbService(Application application) {
        mContext = application;

        Spell spell;
        SourceLevelExtraTriplet sleTriplet;
        DUMMY = new ArrayList<>();

        // Dummy spells
        // Fireball (D&D 5e SRD)
        spell = new Spell();
        spell.setId(1L);
        spell.setName("Fireball");
        spell.setRulebook("System Reference Document 5.1");
        spell.setPage(144);
        spell.setSources(Arrays.asList(
                new SourceLevelExtraTriplet("Sorcerer", 3, null),
                new SourceLevelExtraTriplet("Wizard", 3, null),
                new SourceLevelExtraTriplet("Warlock", 3, "Patron: The Fiend")
        ));
        spell.setSchools(Arrays.asList("Evocation"));
        spell.setCastingTime("1 action");
        spell.setRange("150 feet");
        spell.setComponents(Arrays.asList(
                "Verbal",
                "Somatic",
                "Material"
        ));
        spell.setDuration("Instantaneous");
        spell.setDescription(new FormattedString(
                "A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one. The fire spreads around corners. It ignites flammable objects in the area that aren’t being worn or carried. At Higher Levels. When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.",
                "<p>A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.</p><p>The fire spreads around corners. It ignites flammable objects in the area that aren’t being worn or carried.</p><p><i><b>At Higher Levels.</b></i> When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.</p>"
        ));

        DUMMY.add(spell);
        // Cure Wounds (D&D 5e SRD)
        spell = new Spell();
        spell.setId(2L);
        spell.setName("Cure Wounds");
        spell.setRulebook("System Reference Document 5.1");
        spell.setPage(132);
        spell.setSources(Arrays.asList(
                new SourceLevelExtraTriplet("Bard", 1, null),
                new SourceLevelExtraTriplet("Cleric", 1, null),
                new SourceLevelExtraTriplet("Cleric", 1, "Domain: Life"),
                new SourceLevelExtraTriplet("Druid", 1, null),
                new SourceLevelExtraTriplet("Paladin", 1, null),
                new SourceLevelExtraTriplet("Ranger", 1, null)
        ));
        spell.setSchools(Arrays.asList("Evocation"));
        spell.setCastingTime("1 action");
        spell.setRange("Touch");
        spell.setComponents(Arrays.asList(
                "Verbal",
                "Somatic"
        ));
        spell.setDuration("Instantaneous");
        spell.setDescription(new FormattedString(
                "A creature you touch regains a number of hit points equal to 1d8 + your spellcasting ability modifier. This spell has no effect on undead or constructs. At Higher Levels. When you cast this spell using a spell slot of 2nd level or higher, the healing increases by 1d8 for each slot level above 1st.",
                "<p>A creature you touch regains a number of hit points equal to 1d8 + your spellcasting ability modifier.</p><p>This spell has no effect on undead or constructs.</p><p><i><b>At Higher Levels.</b></i> When you cast this spell using a spell slot of 2nd level or higher, the healing increases by 1d8 for each slot level above 1st.</p>"
        ));

        DUMMY.add(spell);
    }

    public Observable<Spell> saveSpell(final Spell spell) {
        // 1. Check if rulebook exists, if not create and save new one
        // 2. Prepare spell data
        // 3. For each school, subschool, descriptor and component of the spell, check if it exists, if not create and save new one
        // 4.

        Observable<SpellContentValues> prepareSpellContentValues =
                getRulebookId(spell.getRulebook())
                        .map(new Func1<Long, SpellContentValues>() {
                            @Override
                            public SpellContentValues call(Long rulebookId) {
                                return convertSpellToSpellContentValues(spell)
                                        .putRulebookId(rulebookId);
                            }
                        });
        Observable<Boolean> checkIfSpellExists = Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return spellExists(spell.getId());
            }
        });

        Func2<SpellContentValues, Boolean, Long> insertOrUpdateSpellZipFunc =
                new Func2<SpellContentValues, Boolean, Long>() {
                    @Override
                    public Long call(SpellContentValues spellContentValues, Boolean exists) {
                        if (!exists) {
                            Log.d("dzielins", "call: insert");
                            return ContentUris.parseId(spellContentValues.insert(mContext));
                        } else {
                            Log.d("dzielins", "call: update");
                            spellContentValues.update(
                                    mContext, new SpellSelection().id(spell.getId())
                            );
                            return spell.getId();
                        }
                    }
                };
        ConnectableObservable<Long> insertOrUpdateSpell = Observable.zip(
                prepareSpellContentValues, checkIfSpellExists, insertOrUpdateSpellZipFunc
        ).publish();

        Observable<List<Long>> getSchoolIds;
        if (spell.getSchools() != null && !spell.getSchools().isEmpty()) {
            getSchoolIds = Observable.from(spell.getSchools())
                    .flatMap(new Func1<String, Observable<Long>>() {
                        @Override
                        public Observable<Long> call(String s) {
                            return getSchoolId(s);
                        }
                    }).toList();
        } else {
            getSchoolIds = Observable.just(Collections.<Long>emptyList());
        }
        Observable<List<Long>> getSubSchoolIds;
        if (spell.getSubschools() != null && !spell.getSubschools().isEmpty()) {
            getSubSchoolIds = Observable.from(spell.getSubschools())
                    .flatMap(new Func1<String, Observable<Long>>() {
                        @Override
                        public Observable<Long> call(String s) {
                            return getSubSchoolId(s);
                        }
                    }).toList();
        } else {
            getSubSchoolIds = Observable.just(Collections.<Long>emptyList());
        }
        Observable<List<Long>> getComponentIds;
        if (spell.getComponents() != null && !spell.getComponents().isEmpty()) {
            getComponentIds = Observable.from(spell.getComponents())
                    .flatMap(new Func1<String, Observable<Long>>() {
                        @Override
                        public Observable<Long> call(String s) {
                            return getComponentId(s);
                        }
                    }).toList();
        } else {
            getComponentIds = Observable.just(Collections.<Long>emptyList());
        }
        Observable<List<Long>> getDescriptorIds;
        if (spell.getDescriptors() != null && !spell.getDescriptors().isEmpty()) {
            getDescriptorIds = Observable.from(spell.getDescriptors())
                    .flatMap(new Func1<String, Observable<Long>>() {
                        @Override
                        public Observable<Long> call(String s) {
                            return getDescriptorId(s);
                        }
                    }).toList();
        } else {
            getDescriptorIds = Observable.just(Collections.<Long>emptyList());
        }

        Observable<Long> a = Observable.zip(insertOrUpdateSpell, getSchoolIds, new Func2<Long, List<Long>, Long>() {
            @Override
            public Long call(Long spellId, List<Long> schoolIds) {
                SpellsToSchoolsSelection spellsToSchoolsSelection = new SpellsToSchoolsSelection();
                long[] schoolIdsCasted = new long[schoolIds.size()];
                for(int i=0; i < schoolIds.size(); i++) {
                    SpellsToSchoolsContentValues spellsToSchoolsContentValues =
                            new SpellsToSchoolsContentValues();
                    spellsToSchoolsContentValues.putSchoolId(schoolIds.get(i));
                    spellsToSchoolsContentValues.putSpellId(spellId);
                    spellsToSchoolsContentValues.insert(mContext);

                    schoolIdsCasted[i] = schoolIds.get(i);
                }
                if(!schoolIds.isEmpty()) {
                    spellsToSchoolsSelection.spellId(spellId).and().schoolIdNot(schoolIdsCasted);
                    spellsToSchoolsSelection.delete(mContext);
                }

                return spellId;
            }
        });
        Observable<Long> b = Observable.zip(insertOrUpdateSpell, getSubSchoolIds, new Func2<Long, List<Long>, Long>() {
            @Override
            public Long call(Long spellId, List<Long> subSchoolIds) {
                SpellsToSubSchoolsSelection spellsToSubSchoolsSelection =
                        new SpellsToSubSchoolsSelection();
                long[] subSchoolIdsCasted = new long[subSchoolIds.size()];
                for(int i=0; i < subSchoolIds.size(); i++) {
                    SpellsToSubSchoolsContentValues spellsToSubSchoolsContentValues =
                            new SpellsToSubSchoolsContentValues();
                    spellsToSubSchoolsContentValues.putSubSchoolId(subSchoolIds.get(i));
                    spellsToSubSchoolsContentValues.putSpellId(spellId);
                    spellsToSubSchoolsContentValues.insert(mContext);

                    subSchoolIdsCasted[i] = subSchoolIds.get(i);
                }
                if (!subSchoolIds.isEmpty()) {
                    spellsToSubSchoolsSelection.spellId(spellId).and()
                            .subSchoolIdNot(subSchoolIdsCasted);
                    spellsToSubSchoolsSelection.delete(mContext);
                }

                return spellId;
            }
        });
        Observable<Long> c = Observable.zip(insertOrUpdateSpell, getComponentIds, new Func2<Long, List<Long>, Long>() {
            @Override
            public Long call(Long spellId, List<Long> componentIds) {
                SpellsToComponentsSelection spellsToComponentsSelection =
                        new SpellsToComponentsSelection();
                long[] componentIdsCasted = new long[componentIds.size()];
                for(int i=0; i < componentIds.size(); i++) {
                    SpellsToComponentsContentValues spellsToComponentsContentValues =
                            new SpellsToComponentsContentValues();
                    spellsToComponentsContentValues.putComponentId(componentIds.get(i));
                    spellsToComponentsContentValues.putSpellId(spellId);
                    spellsToComponentsContentValues.insert(mContext);

                    componentIdsCasted[i] = componentIds.get(i);
                }
                if (!componentIds.isEmpty()) {
                    spellsToComponentsSelection.spellId(spellId).and()
                            .componentIdNot(componentIdsCasted);
                    spellsToComponentsSelection.delete(mContext);
                }

                return spellId;
            }
        });
        Observable<Long> d = Observable.zip(insertOrUpdateSpell, getDescriptorIds, new Func2<Long, List<Long>, Long>() {
            @Override
            public Long call(Long spellId, List<Long> descriptorIds) {
                SpellsToDescriptorsSelection spellsToDescriptorsSelection =
                        new SpellsToDescriptorsSelection();
                long[] descriptorIdsCasted = new long[descriptorIds.size()];
                for(int i=0; i < descriptorIds.size(); i++) {
                    SpellsToDescriptorsContentValues spellsToDescriptorsContentValues =
                            new SpellsToDescriptorsContentValues();
                    spellsToDescriptorsContentValues.putDescriptorId(descriptorIds.get(i));
                    spellsToDescriptorsContentValues.putSpellId(spellId);
                    spellsToDescriptorsContentValues.insert(mContext);

                    descriptorIdsCasted[i] = descriptorIds.get(i);
                }
                if (!descriptorIds.isEmpty()) {
                    spellsToDescriptorsSelection.spellId(spellId).and()
                            .descriptorIdNot(descriptorIdsCasted);
                    spellsToDescriptorsSelection.delete(mContext);
                }

                return spellId;
            }
        });

        Plan0<Long> plan = JoinObservable.from(a).and(b).and(c).and(d).then(
                new Func4<Long, Long, Long, Long, Long>() {
                    @Override
                    public Long call(Long aLong, Long aLong2, Long aLong3, Long aLong4) {
                        return aLong;
                    }
                });

        insertOrUpdateSpell.connect();

        JoinObservable.when(plan).toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Long aLong) {
            }
        });

        return null;
    }

    public Observable<Spell> getSpells() {
        return Observable.from(DUMMY);
    }

    public Observable<Spell> getSpell(long id) {
        for (Spell spell : DUMMY) {
            if (spell.getId() == id) {
                return Observable.just(spell);
            }
        }

        return Observable.just(null);
    }

    /**
     * Converts {@link Spell} object into {@link SpellContentValues} object.
     * @param spell object to convert.
     * @return {@link SpellContentValues} converted from provided {@link Spell} object.
     */
    private SpellContentValues convertSpellToSpellContentValues(Spell spell) {
        SpellContentValues spellContentValues = new SpellContentValues()
                .putName(spell.getName())
                .putPage(spell.getPage())
                .putCastingTime(spell.getCastingTime())
                .putRange(spell.getRange())
                .putTarget(spell.getTarget())
                .putEffect(spell.getEffect())
                .putArea(spell.getArea())
                .putDuration(spell.getDuration())
                .putSavingThrow(spell.getSavingThrow())
                .putSpellResistance(spell.getSpellResistance());
        if (spell.getDescription() != null) {
            spellContentValues.putDescriptionPlain(spell.getDescription().getPlain())
                    .putDescriptionFormatted(spell.getDescription().getFormatted());
        }
        if (spell.getShortDescription() != null) {
            spellContentValues.putShortDescriptionPlain(spell.getShortDescription().getPlain())
                    .putShortDescriptionPlain(spell.getShortDescription().getFormatted());
        }
        if (spell.getFlavourText() != null) {
            spellContentValues.putFlavourTextPlain(spell.getFlavourText().getPlain())
                    .putFlavourTextFormatted(spell.getFlavourText().getFormatted());
        }

        return spellContentValues;
    }

    /**
     * Checks if spell with provided {@code spellId} already exists in {@link SpellContentProvider}.
     * @param spellId spell ID to check.
     * @return true if spell with given ID exists  in {@link SpellContentProvider}, false otherwise.
     */
    private boolean spellExists(@Nullable Long spellId) {
        if (spellId == null || spellId < 0) {
            return false;
        } else {
            SpellCursor spellCursor = new SpellSelection().id(spellId)
                    .query(mContext, new String[]{SpellColumns._ID});
            return spellCursor != null && spellCursor.getCount() > 0;
        }
    }

    /**
     * Retrieves ID of a descriptor with given name from {@link SpellContentProvider} or creates new
     * descriptor and returns its ID.
     *
     * @param name name of the descriptor.
     * @return observable emitting descriptor's id.
     */
    private Observable<Long> getDescriptorId(final String name) {
        return Observable.fromCallable(new Callable<DescriptorCursor>() {
            @Override
            public DescriptorCursor call() throws Exception {
                return new DescriptorSelection().name(name).query(mContext);
            }
        })
                .flatMap(new Func1<DescriptorCursor, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(DescriptorCursor descriptorCursor) {
                        if (descriptorCursor != null && descriptorCursor.getCount() > 0) {
                            descriptorCursor.moveToFirst();
                            return Observable.just(descriptorCursor.getId());
                        } else {
                            return Observable.fromCallable(new Callable<Long>() {
                                @Override
                                public Long call() throws Exception {
                                    return ContentUris.parseId(
                                            new DescriptorContentValues().putName(name)
                                                    .insert(mContext));
                                }
                            });
                        }
                    }
                })
                .first();
    }

    /**
     * Retrieves ID of a component with given name from {@link SpellContentProvider} or creates new
     * component and returns its ID.
     *
     * @param name name of the component.
     * @return observable emitting component's id.
     */
    private Observable<Long> getComponentId(final String name) {
        return Observable.fromCallable(new Callable<ComponentCursor>() {
            @Override
            public ComponentCursor call() throws Exception {
                return new ComponentSelection().name(name).query(mContext);
            }
        })
                .flatMap(new Func1<ComponentCursor, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(ComponentCursor componentCursor) {
                        if (componentCursor != null && componentCursor.getCount() > 0) {
                            componentCursor.moveToFirst();
                            return Observable.just(componentCursor.getId());
                        } else {
                            return Observable.fromCallable(new Callable<Long>() {
                                @Override
                                public Long call() throws Exception {
                                    return ContentUris.parseId(
                                            new ComponentContentValues().putName(name)
                                                    .insert(mContext));
                                }
                            });
                        }
                    }
                })
                .first();
    }

    /**
     * Retrieves ID of a sub-school with given name from {@link SpellContentProvider} or creates new
     * sub-school and returns its ID.
     *
     * @param name name of the sub-school.
     * @return observable emitting sub-school's id.
     */
    private Observable<Long> getSubSchoolId(final String name) {
        return Observable.fromCallable(new Callable<SubSchoolCursor>() {
            @Override
            public SubSchoolCursor call() throws Exception {
                return new SubSchoolSelection().name(name).query(mContext);
            }
        })
                .flatMap(new Func1<SubSchoolCursor, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(SubSchoolCursor subSchoolCursor) {
                        if (subSchoolCursor != null && subSchoolCursor.getCount() > 0) {
                            subSchoolCursor.moveToFirst();
                            return Observable.just(subSchoolCursor.getId());
                        } else {
                            return Observable.fromCallable(new Callable<Long>() {
                                @Override
                                public Long call() throws Exception {
                                    return ContentUris.parseId(
                                            new SubSchoolContentValues().putName(name)
                                                    .insert(mContext));
                                }
                            });
                        }
                    }
                })
                .first();
    }

    /**
     * Retrieves ID of a school with given name from {@link SpellContentProvider} or creates new
     * school and returns its ID.
     *
     * @param name name of the school.
     * @return observable emitting school's id.
     */
    private Observable<Long> getSchoolId(final String name) {
        return Observable.fromCallable(new Callable<SchoolCursor>() {
            @Override
            public SchoolCursor call() throws Exception {
                return new SchoolSelection().name(name).query(mContext);
            }
        })
                .flatMap(new Func1<SchoolCursor, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(SchoolCursor schoolCursor) {
                        if (schoolCursor != null && schoolCursor.getCount() > 0) {
                            schoolCursor.moveToFirst();
                            return Observable.just(schoolCursor.getId());
                        } else {
                            return Observable.fromCallable(new Callable<Long>() {
                                @Override
                                public Long call() throws Exception {
                                    return ContentUris.parseId(
                                            new SchoolContentValues().putName(name)
                                                    .insert(mContext));
                                }
                            });
                        }
                    }
                })
                .first();
    }

    /**
     * Retrieves ID of a rulebook with given name from {@link SpellContentProvider} or creates new
     * rulebook and returns its ID.
     *
     * @param name name of the rulebook.
     * @return observable emitting rulebook's id.
     */
    private Observable<Long> getRulebookId(final String name) {
        return Observable.fromCallable(new Callable<RulebookCursor>() {
            @Override
            public RulebookCursor call() throws Exception {
                return new RulebookSelection().name(name).query(mContext);
            }
        })
                .flatMap(new Func1<RulebookCursor, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(RulebookCursor rulebookCursor) {
                        if (rulebookCursor != null && rulebookCursor.getCount() > 0) {
                            rulebookCursor.moveToFirst();
                            return Observable.just(rulebookCursor.getId());
                        } else {
                            return Observable.fromCallable(new Callable<Long>() {
                                @Override
                                public Long call() throws Exception {
                                    return ContentUris.parseId(
                                            new RulebookContentValues().putName(name)
                                                    .insert(mContext));
                                }
                            });
                        }
                    }
                })
                .first();
    }

}
