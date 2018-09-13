/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.bus;

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
    public ChannelBUSTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
    @Test
    public void testCheckRoomCreateCase1() {
        System.out.println("checkRoomCreate");
        String userCreate = "taiptht";
        String listMembers = "taiptht, vutq";
        String roomName = "TaiVu_1";
        String avatar = "/mnt/abc";
        boolean expResult = true;
        boolean result = ChannelBUS.checkRoomCreate(userCreate, listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckRoomCreateCase2() {
        System.out.println("checkRoomCreate");
        String userCreate = "";
        String listMembers = "";
        String roomName = "";
        String avatar = "";
        boolean expResult = false;
        boolean result = ChannelBUS.checkRoomCreate(userCreate, listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckRoomCreateCase3() {
        System.out.println("checkRoomCreate");
        String userCreate = "1111111111111111111111";
        String listMembers = "taiptht, vutq";
        String roomName = "TaiVu_1";
        String avatar = "/mnt/abc/xyz";
        boolean expResult = false;
        boolean result = ChannelBUS.checkRoomCreate(userCreate, listMembers, roomName, avatar);
        assertEquals(expResult, result);
    }
}
