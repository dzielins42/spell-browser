package pl.dzielins42.spells.browser.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorDao;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookDao;
import pl.dzielins42.spellcontentprovider.school.SchoolDao;
import pl.dzielins42.spellcontentprovider.spell.SpellDao;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolDao;
import pl.dzielins42.spells.browser.data.local.ActivityHelloService;
import pl.dzielins42.spells.browser.data.local.AppHelloService;
import pl.dzielins42.spells.browser.data.local.FragmentHelloService;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AppHelloService provideAppHelloService() {
        return new AppHelloService();
    }

    @Provides
    @Singleton
    ActivityHelloService provideActivityHelloService() {
        return new ActivityHelloService();
    }

    @Provides
    @Singleton
    FragmentHelloService provideFragmentHelloService() {
        return new FragmentHelloService();
    }

    @Provides
    @Singleton
    CharacterClassDao provideCharacterClassDao(Context context) {
        return new CharacterClassDao(context);
    }

    @Provides
    @Singleton
    ComponentDao provideComponentDao(Context context) {
        return new ComponentDao(context);
    }

    @Singleton
    @Provides
    DescriptorDao provideDescriptorDao(Context context) {
        return new DescriptorDao(context);
    }

    @Singleton
    @Provides
    RulebookDao provideRulebookDao(Context context) {
        return new RulebookDao(context);
    }

    @Singleton
    @Provides
    SchoolDao provideSchoolDao(Context context) {
        return new SchoolDao(context);
    }

    @Singleton
    @Provides
    SpellDao provideSpellDao(Context context) {
        return new SpellDao(context);
    }

    @Singleton
    @Provides
    SubschoolDao provideSubschoolDao(Context context) {
        return new SubschoolDao(context);
    }

}
