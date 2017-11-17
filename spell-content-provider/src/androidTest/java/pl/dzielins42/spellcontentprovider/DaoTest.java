package pl.dzielins42.spellcontentprovider;

import android.database.sqlite.SQLiteDatabase;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;


import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassColumns;
import pl.dzielins42.spellcontentprovider.component.ComponentColumns;
import pl.dzielins42.spellcontentprovider.descriptor.DescriptorColumns;
import pl.dzielins42.spellcontentprovider.rulebook.RulebookColumns;
import pl.dzielins42.spellcontentprovider.school.SchoolColumns;
import pl.dzielins42.spellcontentprovider.spell.SpellColumns;
import pl.dzielins42.spellcontentprovider.spellstocharacterclasses.SpellsToCharacterClassesColumns;
import pl.dzielins42.spellcontentprovider.spellstocomponents.SpellsToComponentsColumns;
import pl.dzielins42.spellcontentprovider.spellstodescriptors.SpellsToDescriptorsColumns;
import pl.dzielins42.spellcontentprovider.spellstoschools.SpellsToSchoolsColumns;
import pl.dzielins42.spellcontentprovider.subschool.SubschoolColumns;

public class DaoTest extends ProviderTestCase2<SpellContentProvider> {

    private SpellContentProviderSQLiteOpenHelper mSQLiteOpenHelper;

    public DaoTest() {
        super(SpellContentProvider.class, SpellContentProvider.AUTHORITY);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mSQLiteOpenHelper = SpellContentProviderSQLiteOpenHelper.getInstance(getMockContext());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();

        db.delete(SpellsToCharacterClassesColumns.TABLE_NAME, null, null);
        db.delete(SpellsToComponentsColumns.TABLE_NAME, null, null);
        db.delete(SpellsToDescriptorsColumns.TABLE_NAME, null, null);
        db.delete(SpellsToSchoolsColumns.TABLE_NAME, null, null);

        db.delete(CharacterClassColumns.TABLE_NAME, null, null);
        db.delete(ComponentColumns.TABLE_NAME, null, null);
        db.delete(DescriptorColumns.TABLE_NAME, null, null);
        db.delete(RulebookColumns.TABLE_NAME, null, null);
        db.delete(SubschoolColumns.TABLE_NAME, null, null);
        db.delete(SchoolColumns.TABLE_NAME, null, null);
        db.delete(SpellColumns.TABLE_NAME, null, null);
    }

    SQLiteDatabase getReadableDatabase(){
        return mSQLiteOpenHelper.getReadableDatabase();
    }

    SQLiteDatabase getWritableDatabase() {
        return mSQLiteOpenHelper.getWritableDatabase();
    }

}