/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.database;

import java.io.UnsupportedEncodingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author taipham
 */
public class RockDBAppTest {
    private static MyStorage _storage;
    public RockDBAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        _storage = new RockDBApp("./teststorage");
        _storage.connect();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class RockDBApp.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        boolean expResult = true;
        boolean result = _storage.connect();
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class RockDBApp.
     */
    @Test
    public void testPut() throws UnsupportedEncodingException {
        System.out.println("put");
        String key = "taiptht";
        String valueTmp = "taiptht, vutq";
        byte[] value = valueTmp.getBytes("utf-8");
        boolean expResult = true;
        boolean result = _storage.put(key, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class RockDBApp.
     */
    @Test
    public void testRemoveCase1() {
        System.out.println("remove");
        String key = "taiptht";
        boolean expResult = true;
        boolean result = _storage.remove(key);
        assertEquals(expResult, result);;
    }

    @Test
    public void testRemoveCase2() {
        System.out.println("remove");
        String key = "taiptht";
        boolean expResult = false;
        boolean result = _storage.remove(key);
        assertEquals(expResult, result);;
    }
    
    /**
     * Test of contains method, of class RockDBApp.
     */
    @Test
    public void testContains() throws UnsupportedEncodingException {
        System.out.println("contains");
        String key = "taiptht";
        String valueTmp = "taiptht, vutq";
        byte[] value = valueTmp.getBytes("utf-8");
        _storage.put(key, value);
        boolean expResult = true;
        boolean result = _storage.contains(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class RockDBApp.
     */
    @Test
    public void testGet() throws UnsupportedEncodingException {
        System.out.println("get");
        String key = "taiptht";
        String expResult = "taiptht, vutq";
        String result = new String((byte[]) _storage.get(key), "utf-8");
        assertEquals(expResult, result);
    }
    
}
