package com.example.casper.lifeprice;

import com.example.casper.lifeprice.data.ShopLoader;
import com.example.casper.lifeprice.data.model.Shop;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShopLoaderTest {
    ShopLoader shopLoader=new ShopLoader();

    @Test
    public void getShops() {
        assertNotNull(shopLoader.getShops());
        assertEquals(0,shopLoader.getShops().size());

    }

    @Test
    public void download() {
        String content=shopLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
        assertTrue(content.length()>300);
        assertTrue(content.contains("\"name\": \"沃尔玛(前山店)\"")&&content.contains("\"latitude\": \"22.251953\""));
    }

    @Test
    public void parseJson() {
        String content=shopLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");
        assertEquals(0,shopLoader.getShops().size());
        shopLoader.parseJson(content);
        assertEquals(3,shopLoader.getShops().size());
        Shop shop=shopLoader.getShops().get(1);
        assertEquals(22.261365,shop.getLatitude(),1e-6);
        assertEquals(113.532989,shop.getLongitude(),1e-6);
        assertEquals("沃尔玛(前山店)",shop.getName());
        assertEquals("沃尔玛(前山店)",shop.getMemo());

    }
}