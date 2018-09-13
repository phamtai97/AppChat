/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.bus;

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
public class ChannelBUSTest {
    private static ChannelBUS _channBUS;
    public ChannelBUSTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        _channBUS = new ChannelBUS();
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
     * Test of checkRoomCreate method, of class ChannelBUS.
     */
    @org.junit.Test
    public void testCheckRoomCreateCase1() {
        System.out.println("checkRoomCreate");
        String listMembers = "taiptht, vutq";
        String roomName = "TaiVu_1";
        String avatar = "/mnt/abc/xyz";
        boolean expResult = true;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    @org.junit.Test
    public void testCheckRoomCreateCase2() {
        System.out.println("checkRoomCreate");
        String listMembers = "";
        String roomName = "";
        String avatar = "";
        boolean expResult = false;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void testCheckRoomCreateCase3() {
         System.out.println("checkRoomCreate");
        String listMembers = "taiptht, vutq";
        String roomName = "";
        String avatar = "/mnt/abc/xyz";
        boolean expResult = false;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void testCheckRoomCreateCase4() {
         System.out.println("checkRoomCreate");
        String listMembers = "";
        String roomName = "TaiVu_1";
        String avatar = "/mnt/abc/xyz";
        boolean expResult = false;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void testCheckRoomCreateCase5() {
         System.out.println("checkRoomCreate");
        String listMembers = "taiptht, vutq";
        String roomName = "TaiVu_1";
        String avatar = "";
        boolean expResult = false;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void testCheckRoomCreateCase6() {
         System.out.println("checkRoomCreate");
        String listMembers = "taiptht, vutq";
        String roomName = "TaiVu_1";
        String avatar = "Choose Avatar";
        boolean expResult = false;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void testCheckRoomCreateCase7() {
         System.out.println("checkRoomCreate");
        String listMembers = "taiptht, vutq";
        String roomName = "111111111111111111111";
        String avatar = "Choose Avatar";
        boolean expResult = false;
        boolean result = _channBUS.checkRoomCreate(listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
}
