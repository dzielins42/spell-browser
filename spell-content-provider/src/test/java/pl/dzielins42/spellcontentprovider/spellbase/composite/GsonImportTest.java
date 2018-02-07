package pl.dzielins42.spellcontentprovider.spellbase.composite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonImportTest {

    private Gson mGson;

    @Before
    public void setUp() throws Exception {
        mGson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(
                        CompositeSpellBean.class, new CompositeSpellJsonSerializerDeserializer()
                )
                .create();
    }

    @After
    public void tearDown() throws Exception {
        mGson = null;
    }

    @Test
    public void importFromFile() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("spells.json")
        ));
        Type listType = new TypeToken<ArrayList<CompositeSpellBean>>(){}.getType();
        List<CompositeSpellBean> list = mGson.fromJson(in, listType);
        in.close();

        System.out.println(list);
    }

}
