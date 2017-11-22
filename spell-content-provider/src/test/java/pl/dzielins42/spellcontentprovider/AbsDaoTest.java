package pl.dzielins42.spellcontentprovider;

import android.database.sqlite.SQLiteDatabase;

import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

public abstract class AbsDaoTest {

    private SpellContentProviderSQLiteOpenHelper mSQLiteOpenHelper;

    public AbsDaoTest() {
        super();
        Robolectric.setupContentProvider(SpellContentProvider.class, SpellContentProvider.AUTHORITY);
    }

    public void setUp() throws Exception {
        mSQLiteOpenHelper = SpellContentProviderSQLiteOpenHelper.getInstance(RuntimeEnvironment.application);
    }

    public void tearDown() throws Exception {
        RuntimeEnvironment.application.deleteDatabase(SpellContentProviderSQLiteOpenHelper.DATABASE_FILE_NAME);
    }

    protected SQLiteDatabase getReadableDatabase() {
        return mSQLiteOpenHelper.getReadableDatabase();
    }

}
