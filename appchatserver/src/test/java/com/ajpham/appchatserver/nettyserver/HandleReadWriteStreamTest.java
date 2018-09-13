/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.nettyserver;

import com.ajpham.appchatserver.dto.ChannelDTO;
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
public class HandleReadWriteStreamTest {
    private static HandleReadWriteStream _handleReadWriteStream;
    public HandleReadWriteStreamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        _handleReadWriteStream = new HandleReadWriteStream();
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
     * Test of toStream method, of class HandleReadWriteStream.
     */
    @Test
    public void testToStream() {
        System.out.println("toStream");
        Object obj = new ChannelDTO("taiptht", "TaiVu", null, "taiptht, vutq");
        byte[] result = _handleReadWriteStream.toStream(obj);
        assertTrue(result.length > 0);
    }

    /**
     * Test of toObject method, of class HandleReadWriteStream.
     */
    
    @Test
    public void testToObject() throws Exception {
        System.out.println("toObject");
        ChannelDTO obj = new ChannelDTO("taiptht", "TaiVu", null, "taiptht, vutq");
        byte[] stream = _handleReadWriteStream.toStream(obj); 
        ChannelDTO objRes = (ChannelDTO) _handleReadWriteStream.toObject(stream);
        boolean expResult = true;
        boolean result = _handleReadWriteStream.compareChannel(obj, objRes);
        assertEquals(expResult, result);
    }
    
}
