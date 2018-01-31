package pl.dzielins42.spellcontentprovider.rulebook;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;

@RunWith(RobolectricTestRunner.class)
public class RulebookDaoTest
        extends SimpleDaoTest<RulebookBean, RulebookSelection, RulebookDao, RulebookColumns> {

    @Override
    protected RulebookBean[] testBeans() {
        return new RulebookBean[]{
                RulebookBean.newBuilder().name("Test1").build(),
                RulebookBean.newBuilder().name("Test2").build(),
                RulebookBean.newBuilder().name("Test3").build(),
                RulebookBean.newBuilder().name("Test4").build(),
                RulebookBean.newBuilder().name("Test5").build(),
        };
    }

    @Override
    protected RulebookSelection selectionForSingleTestBean() {
        return new RulebookSelection().name("Test1");
    }

    @Override
    protected RulebookSelection selectionForThreeTestBeans() {
        return new RulebookSelection().name("Test1", "Test2", "Test3");
    }

    @Override
    protected String tableName() {
        return RulebookColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(RulebookBean rulebookBean) {
        rulebookBean.setName(rulebookBean.getName() + "_changed");
    }

    @Override
    protected RulebookDao dao() {
        return new RulebookDao(RuntimeEnvironment.application);
    }

}