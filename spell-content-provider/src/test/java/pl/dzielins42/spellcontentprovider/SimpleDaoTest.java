package pl.dzielins42.spellcontentprovider;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.List;

import pl.dzielins42.spellcontentprovider.base.AbstractSelection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class SimpleDaoTest<
        BEAN,
        SELECTION extends AbstractSelection,
        DAO extends AbsDao<BEAN, SELECTION>,
        COLUMNS extends BaseColumns
        > extends AbsDaoTest {

    protected DAO mDao;

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
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        mDao = null;
    }

    @Override
    public void save_insert() throws Exception {
        BEAN bean = testBeans()[0];
        final long id = mDao.save(bean).blockingFirst();

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
        BEAN bean = testBeans()[0];
        final long id = mDao.save(bean).blockingFirst();
        modifyBean(bean);
        mDao.save(bean).blockingFirst();

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
        final int removedCount = mDao.remove(selection).blockingFirst();
        assertEquals(3, removedCount);
        assertEquals(testBeans().length - removedCount, countRows());
        List<BEAN> querryResult = mDao.get(selection).blockingFirst();
        assertTrue(querryResult.isEmpty());
    }

    @Override
    public void remove_bean() throws Exception {
        BEAN bean = testBeans()[0];
        mDao.save(bean).blockingFirst();
        final boolean result = mDao.remove(bean).blockingFirst();
        assertTrue(result);

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

    protected void insertTestBeans() {
        for (int i = 0; i < testBeans().length; i++) {
            mDao.save(testBeans()[i]).blockingFirst();
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
