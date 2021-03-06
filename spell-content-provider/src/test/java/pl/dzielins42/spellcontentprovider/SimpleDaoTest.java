package pl.dzielins42.spellcontentprovider;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.List;

import pl.dzielins42.spellcontentprovider.base.AbstractSelection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public abstract class SimpleDaoTest<
        BEAN,
        SELECTION extends AbstractSelection,
        DAO extends Dao<BEAN, SELECTION>,
        COLUMNS extends BaseColumns
        > extends AbsDaoTest {

    protected DAO mDao;
    protected BEAN[] mTestBeans;

    public SimpleDaoTest() {
        super();
    }

    @Override
    protected void clearAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + tableName() + ";");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mDao = dao();
        mTestBeans = testBeans();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        mDao = null;
    }

    @Override
    public void save_insert() throws Exception {
        BEAN bean = mTestBeans[0];
        final long id = mDao.save(bean).blockingGet();

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                1,
                db.query(
                        tableName(),
                        new String[]{COLUMNS._ID},
                        COLUMNS._ID + " = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null
                ).getCount()
        );
        db.close();
    }

    @Override
    public void save_update() throws Exception {
        BEAN bean = mTestBeans[0];
        final long id = mDao.save(bean).blockingGet();
        modifyBean(bean);
        mDao.save(bean).blockingGet();

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                1,
                db.query(
                        tableName(),
                        new String[]{COLUMNS._ID},
                        COLUMNS._ID + " = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null
                ).getCount()
        );
        db.close();
    }

    @Override
    public void get_1() throws Exception {
        insertTestBeans();
        SELECTION selection = selectionForSingleTestBean();
        List<BEAN> querryResult = mDao.get(selection).blockingFirst();
        assertEquals(1, querryResult.size());
    }

    @Override
    public void get_3() throws Exception {
        insertTestBeans();
        SELECTION selection = selectionForThreeTestBeans();
        List<BEAN> querryResult = mDao.get(selection).blockingFirst();
        assertEquals(3, querryResult.size());
    }

    @Override
    public void remove_selection() throws Exception {
        insertTestBeans();
        SELECTION selection = selectionForThreeTestBeans();
        final int removedCount = mDao.remove(selection).blockingGet();
        assertEquals(3, removedCount);
        assertEquals(mTestBeans.length - removedCount, countRows());
        List<BEAN> querryResult = mDao.get(selection).blockingFirst();
        assertTrue(querryResult.isEmpty());
    }

    @Override
    public void remove_bean() throws Exception {
        BEAN bean = mTestBeans[0];
        mDao.save(bean).blockingGet();
        Throwable exception = mDao.remove(bean).blockingGet();
        assertNull(exception);

        SQLiteDatabase db = getReadableDatabase();
        assertEquals(
                0,
                db.query(
                        tableName(),
                        new String[]{COLUMNS._ID}, null, null,
                        null,
                        null,
                        null
                ).getCount()
        );
        db.close();
    }

    @Override
    public void count_all() throws Exception {
        insertTestBeans();
        int count = mDao.count().blockingGet();
        assertEquals(mTestBeans.length, count);
    }

    @Override
    public void count_selection() throws Exception {
        insertTestBeans();
        int count = mDao.count(selectionForThreeTestBeans()).blockingGet();
        assertEquals(3, count);
    }

    protected void insertTestBeans() {
        for (int i = 0; i < mTestBeans.length; i++) {
            mDao.save(mTestBeans[i]).blockingGet();
        }
    }

    protected long countRows() {
        SQLiteDatabase db = getReadableDatabase();
        final long result = DatabaseUtils.queryNumEntries(db, tableName());
        db.close();

        return result;
    }

    protected abstract BEAN[] testBeans();

    protected abstract SELECTION selectionForSingleTestBean();

    protected abstract SELECTION selectionForThreeTestBeans();

    protected abstract String tableName();

    protected abstract void modifyBean(BEAN bean);

    protected abstract DAO dao();

}
