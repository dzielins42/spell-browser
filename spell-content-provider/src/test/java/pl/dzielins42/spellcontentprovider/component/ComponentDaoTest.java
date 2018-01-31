package pl.dzielins42.spellcontentprovider.component;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;

@RunWith(RobolectricTestRunner.class)
public class ComponentDaoTest
        extends SimpleDaoTest<ComponentBean, ComponentSelection, ComponentDao, ComponentColumns> {

    @Override
    protected ComponentBean[] testBeans() {
        return new ComponentBean[]{
                ComponentBean.newBuilder().name("Test1").build(),
                ComponentBean.newBuilder().name("Test2").build(),
                ComponentBean.newBuilder().name("Test3").build(),
                ComponentBean.newBuilder().name("Test4").build(),
                ComponentBean.newBuilder().name("Test5").build(),
        };
    }

    @Override
    protected ComponentSelection selectionForSingleTestBean() {
        return new ComponentSelection().name("Test1");
    }

    @Override
    protected ComponentSelection selectionForThreeTestBeans() {
        return new ComponentSelection().name("Test1", "Test2", "Test3");
    }

    @Override
    protected String tableName() {
        return ComponentColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(ComponentBean componentBean) {
        componentBean.setName(componentBean.getName() + "_changed");
    }

    @Override
    protected ComponentDao dao() {
        return new ComponentDao(RuntimeEnvironment.application);
    }

}