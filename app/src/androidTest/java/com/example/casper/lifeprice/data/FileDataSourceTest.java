package com.example.casper.lifeprice.data;

import android.support.test.InstrumentationRegistry;

import com.example.casper.lifeprice.R;
import com.example.casper.lifeprice.data.model.Good;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileDataSourceTest {
    FileDataSource keeper;
    @Before
    public void setUp() throws Exception {
        keeper=new FileDataSource(InstrumentationRegistry.getTargetContext());
        keeper.load();
    }

    @After
    public void tearDown() throws Exception {

        keeper.save();
    }

    @Test
    public void getGoods() {
        FileDataSource  fileDataSource=new FileDataSource(InstrumentationRegistry.getTargetContext());
        assertEquals(0,fileDataSource.getGoods().size());

    }

    @Test
    public void save() {
        FileDataSource  fileDataSource=new FileDataSource(InstrumentationRegistry.getTargetContext());
        assertEquals(0,fileDataSource.getGoods().size());
        fileDataSource.getGoods().add(new Good("测试1",20, R.drawable.a1));
        fileDataSource.getGoods().add(new Good("测试2",10, R.drawable.a1));
        fileDataSource.save();
        FileDataSource fileloader=new  FileDataSource(InstrumentationRegistry.getTargetContext());
        fileloader.load();

        assertEquals(fileDataSource.getGoods().size(),fileloader.getGoods().size());
        assertNotEquals(fileDataSource.getGoods(),fileloader.getGoods());

        for(int i=0;i<fileDataSource.getGoods().size();i++){
            Good goodthis=fileDataSource.getGoods().get(i);
            Good goodthat=fileloader.getGoods().get(i);
            assertNotEquals(goodthis,goodthat);
            assertEquals(goodthis.getName(),goodthat.getName());
            assertEquals(goodthis.getPictureId(),goodthat.getPictureId());
            assertEquals(goodthis.getPrice(),goodthat.getPrice(),1e-3);
        }

    }

    @Test
    public void load() {
        FileDataSource  fileDataSource=new FileDataSource(InstrumentationRegistry.getTargetContext());
        assertEquals(0,fileDataSource.getGoods().size());
        fileDataSource.save();
        FileDataSource fileloader=new  FileDataSource(InstrumentationRegistry.getTargetContext());
        fileloader.load();

        assertEquals(fileDataSource.getGoods().size(),fileloader.getGoods().size());
        //assertNotEquals(fileDataSource.getGoods(),fileloader.getGoods());
    }
}