package com.example.homefinderz.model;

import org.junit.Test;

import org.junit.BeforeClass;
import org.junit.Assert;

import static org.junit.Assert.*;

public class UnitTest_Search {

    private static Search searchExample;
    private static Search emptySearchExample;

    public boolean isPositive(int value) {
        return value >= 0;
    }

    @BeforeClass
    public static void setUp() {
        emptySearchExample = new Search();
        searchExample = new Search(1, 100, 500, "Athens", 80, 30, 1, 1, 1, true);
    }

    @Test
    public void positiveValues() {
        Assert.assertTrue(isPositive(searchExample.getMinPrice()));
        Assert.assertTrue(isPositive(searchExample.getMaxPrice()));
        Assert.assertTrue(isPositive(searchExample.getMinSqm()));
        Assert.assertTrue(isPositive(searchExample.getMaxSqm()));
        Assert.assertTrue(isPositive(searchExample.getBathrooms()));
        Assert.assertTrue(isPositive(searchExample.getBedrooms()));
        Assert.assertTrue(isPositive(searchExample.getFloor()));

    }

    @Test
    public void getSets() {
        searchExample.setSearchId(18);
        assertEquals(18, searchExample.getSearchId());
        searchExample.setMinPrice(100);
        assertEquals(100, searchExample.getMinPrice());
        searchExample.setMaxPrice(500);
        assertEquals(500, searchExample.getMaxPrice());
        searchExample.setLocation("Athens");
        assertEquals("Athens", searchExample.getLocation());
        searchExample.setMaxSqm(80);
        assertEquals(80, searchExample.getMaxSqm());
        searchExample.setMinSqm(30);
        assertEquals(30, searchExample.getMinSqm());
        searchExample.setBedrooms(3);
        assertEquals(3, searchExample.getBedrooms());
        searchExample.setBathrooms(1);
        assertEquals(1, searchExample.getBathrooms());
        searchExample.setFloor(3);
        assertEquals(3, searchExample.getFloor());
        searchExample.setHeat(true);
        assertTrue(searchExample.getHeat());
    }

}