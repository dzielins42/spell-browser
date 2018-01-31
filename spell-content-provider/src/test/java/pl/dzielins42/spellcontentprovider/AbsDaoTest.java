package pl.dzielins42.spellcontentprovider;

import android.database.sqlite.SQLiteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowSQLiteConnection;

@RunWith(RobolectricTestRunner.class)
public abstract class AbsDaoTest {

    private SpellContentProviderSQLiteOpenHelper mSQLiteOpenHelper;

    public AbsDaoTest() {
        super();
        Robolectric.setupContentProvider(
                SpellContentProvider.class,
                SpellContentProvider.AUTHORITY
        );
    }

    @Before
    public void setUp() throws Exception {
        ShadowSQLiteConnection.reset();
        mSQLiteOpenHelper = SpellContentProviderSQLiteOpenHelper.getInstance(
                RuntimeEnvironment.application
        );
    }

    @After
    public void tearDown() throws Exception {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        clearAllData(db);
        db.close();
        mSQLiteOpenHelper.close();
        ShadowSQLiteConnection.reset();
        RuntimeEnvironment.application.deleteDatabase(
                SpellContentProviderSQLiteOpenHelper.DATABASE_FILE_NAME
        );
    }

    protected SQLiteDatabase getReadableDatabase() {
        return mSQLiteOpenHelper.getReadableDatabase();
    }

    @Test
    public abstract void save_insert() throws Exception;

    @Test
    public abstract void save_update() throws Exception;

    @Test
    public abstract void get_1() throws Exception;

    @Test
    public abstract void get_3() throws Exception;

    @Test
    public abstract void remove_selection() throws Exception;

    @Test
    public abstract void remove_bean() throws Exception;

    protected abstract void clearAllData(SQLiteDatabase db);

}
