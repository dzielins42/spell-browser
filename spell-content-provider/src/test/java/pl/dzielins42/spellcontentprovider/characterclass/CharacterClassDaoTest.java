package pl.dzielins42.spellcontentprovider.characterclass;

import org.robolectric.RuntimeEnvironment;

import pl.dzielins42.spellcontentprovider.SimpleDaoTest;

public class CharacterClassDaoTest
        extends SimpleDaoTest<CharacterClassBean, CharacterClassSelection, CharacterClassDao, CharacterClassColumns> {

    @Override
    protected CharacterClassBean[] testBeans() {
        return new CharacterClassBean[]{
                CharacterClassBean.newBuilder().name("Test1").build(),
                CharacterClassBean.newBuilder().name("Test2").build(),
                CharacterClassBean.newBuilder().name("Test3").build(),
                CharacterClassBean.newBuilder().name("Test4").build(),
                CharacterClassBean.newBuilder().name("Test5").build(),
        };
    }

    @Override
    protected CharacterClassSelection selectionForSingleTestBean() {
        CharacterClassSelection selection = new CharacterClassSelection();
        selection.name("Test1");

        return selection;
    }

    @Override
    protected CharacterClassSelection selectionForThreeTestBeans() {
        CharacterClassSelection selection = new CharacterClassSelection();
        selection.name("Test1", "Test2", "Test3");

        return selection;
    }

    @Override
    protected String tableName() {
        return CharacterClassColumns.TABLE_NAME;
    }

    @Override
    protected void modifyBean(CharacterClassBean characterClassBean) {
        characterClassBean.setName(characterClassBean.getName() + "_changed");
    }

    @Override
    protected CharacterClassDao dao() {
        return new CharacterClassDao(RuntimeEnvironment.application);
    }

}