/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.dto;

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
public class TokenDTOTest {
    
    public TokenDTOTest() {
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
     * Test of base64Encode method, of class TokenDTO.
     */
    @Test
    public void testBase64EncodeDecode() {
        System.out.println("base64EncodeDecode");
        String msg = "tantai";
        String result = TokenDTO.base64Encode(msg);
        String expResult = TokenDTO.base64Decode(result);
        assertEquals(expResult, msg);
    }

    /**
     * Test of createToken method, of class TokenDTO.
     */
    @Test
    public void testCreateToken() throws Exception {
        System.out.println("createToken");
        String payload = "taiptht";
        String token = TokenDTO.createToken(payload);
        boolean expResult = true;
        boolean result = TokenDTO.verifyToken(token);
        assertEquals(expResult, result);
    }
    
}
