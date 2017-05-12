package pl.dzielins42.spellbrowser.data.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.dzielins42.spellbrowser.data.model.FormattedString;
import pl.dzielins42.spellbrowser.data.model.SourceLevelExtraTriplet;
import pl.dzielins42.spellbrowser.data.model.Spell;
import rx.Observable;

@Singleton
public class SpellDbService {

    private List<Spell> DUMMY;

    @Inject
    public SpellDbService() {
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

    public Observable<List<Spell>> getSpells() {
        return Observable.just(DUMMY);
    }

    public Observable<Spell> getSpell(long id) {
        for (Spell spell : DUMMY) {
            if (spell.getId() == id) {
                return Observable.just(spell);
            }
        }

        return Observable.just(null);
    }

}
