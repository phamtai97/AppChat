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
public class UserBUSTest {

    private static UserBUS _userBUS;

    public UserBUSTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        _userBUS = new UserBUS();
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
     * Test of checkUserLogin method, of class UserBUS.
     */
    @Test
    public void testCheckUserLoginCase1() {
        System.out.println("checkUserLogin");
        String userName = "taiptht";
        String password = "123456789";
        boolean expResult = true;
        boolean result = _userBUS.checkUserLogin(userName, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserLoginCase2() {
        System.out.println("checkUserLogin");
        String userName = "";
        String password = "123456789";
        boolean expResult = false;
        boolean result = _userBUS.checkUserLogin(userName, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserLoginCase3() {
        System.out.println("checkUserLogin");
        String userName = "taiptht";
        String password = "";
        UserBUS instance = new UserBUS();
        boolean expResult = false;
        boolean result = instance.checkUserLogin(userName, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserLoginCase4() {
        System.out.println("checkUserLogin");
        String userName = "";
        String password = "";
        boolean expResult = false;
        boolean result = _userBUS.checkUserLogin(userName, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserLoginCase5() {
        System.out.println("checkUserLogin");
        String userName = "111111111111111111111";
        String password = "12345";
        boolean expResult = false;
        boolean result = _userBUS.checkUserLogin(userName, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserLoginCase6() {
        System.out.println("checkUserLogin");
        String userName = "taiptht";
        String password = "111111111111111111111";
        boolean expResult = false;
        boolean result = _userBUS.checkUserLogin(userName, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUserRegister method, of class UserBUS.
     */
    @Test
    public void testCheckUserRegisterCase1() {
        System.out.println("checkUserRegister");
        String name = "Tan Tai";
        String userName = "taiptht";
        String password = "123";
        String pathAvatar = "/mnt/abcx";
        boolean expResult = true;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testCheckUserRegisterCase2() {
        System.out.println("checkUserRegister");
        String name = "";
        String userName = "taiptht";
        String password = "123";
        String pathAvatar = "/mnt/abcx";
        boolean expResult = false;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testCheckUserRegisterCase3() {
        System.out.println("checkUserRegister");
        String name = "";
        String userName = "";
        String password = "";
        String pathAvatar = "";
        boolean expResult = false;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testCheckUserRegisterCase4() {
        System.out.println("checkUserRegister");
        String name = "111111111111111111111";
        String userName = "taiptht";
        String password = "123";
        String pathAvatar = "/mnt/abcx";
        boolean expResult = false;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testCheckUserRegisterCase5() {
        System.out.println("checkUserRegister");
        String name = "Tan Tai";
        String userName = "111111111111111111111";
        String password = "123";
        String pathAvatar = "/mnt/abcx";
        boolean expResult = false;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testCheckUserRegisterCase6() {
        System.out.println("checkUserRegister");
        String name = "Tan Tai";
        String userName = "taiptht";
        String password = "111111111111111111111";
        String pathAvatar = "/mnt/abcx";
        boolean expResult = false;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testCheckUserRegisterCase7() {
        System.out.println("checkUserRegister");
        String name = "Tan Tai";
        String userName = "taiptht";
        String password = "12345";
        String pathAvatar = "Choose Avatar";
        boolean expResult = false;
        boolean result = _userBUS.checkUserRegister(name, userName, password, pathAvatar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}
