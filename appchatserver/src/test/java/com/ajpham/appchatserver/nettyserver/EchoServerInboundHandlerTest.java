/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.nettyserver;

import com.ajpham.appchatserver.database.MyStorage;
import com.ajpham.appchatserver.database.RockDBApp;
import com.ajpham.appchatserver.dto.ChannelDTO;
import com.ajpham.appchatserver.dto.MessageDTO;
import com.ajpham.appchatserver.dto.RequestDTO;
import com.ajpham.appchatserver.dto.ResponseDTO;
import com.ajpham.appchatserver.dto.TokenDTO;
import com.ajpham.appchatserver.dto.UserDTO;
import com.google.protobuf.ByteString;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EchoServerInboundHandlerTest {

    private static MyStorage _storage;
    private static HandleReadWriteStream _handleReadWriteStream;
    private static EchoServerInboundHandler _handler;

    public EchoServerInboundHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        _storage = new RockDBApp("./teststorage");
        _storage.connect();
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
     * Test of handleLogin method, of class EchoServerInboundHandler.
     */
    @Test
    public void testHandleLoginCase1() throws Exception {
        System.out.println("handleLogin");
        String userName = "taiptht";
        String password = "123";
        if (_storage.contains("user." + userName)) {
            try {
                UserDTO userDTO = (UserDTO) _handleReadWriteStream.toObject((byte[]) _storage.get("user." + userName));
                String passwordDecode = TokenDTO.base64Decode(userDTO.getPassword());
                assertTrue(password.equals(passwordDecode));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EchoServerInboundHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testHandleLoginCase2() throws Exception {
        System.out.println("handleLogin");
        String userName = "taiptht";
        String password = "12345";
        if (_storage.contains("user." + userName)) {
            try {
                UserDTO userDTO = (UserDTO) _handleReadWriteStream.toObject((byte[]) _storage.get("user." + userName));
                String passwordDecode = TokenDTO.base64Decode(userDTO.getPassword());
                assertFalse(password.equals(passwordDecode));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EchoServerInboundHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testHandleLoginCase3() throws Exception {
        System.out.println("handleLogin");
        String userName = "taipthttt";
        String password = "12345";
        assertFalse(_storage.contains("user." + userName));
    }

    /**
     * Test of handleRegister method, of class EchoServerInboundHandler.
     */
    //đổi file ảnh thành byte
    public byte[] convertFile(String path) throws IOException {
        try {
            if (path.length() == 0) {
                return null;
            }
            File file = new File(path);
            FileInputStream fileInput = new FileInputStream(file);
            byte[] bFile = new byte[(int) file.length()];
            fileInput.read(bFile);
            fileInput.close();
            return bFile;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Test
    public void testHandleRegisterCase1() throws IOException {
        System.out.println("handleRegister");
        String name = "Trung Bim Bo";
        String userName = "trungdv1";
        String password = "123";
        String path = "/home/taipham/Pictures/Screenshot from 2018-07-05 16-27-22.png";
        ByteString avatar = ByteString.copyFrom(this.convertFile(path));
        if (!_storage.contains("user." + userName)) {
            String passwordEnCry = TokenDTO.base64Encode(password);
            UserDTO userDTO = new UserDTO(name, userName, passwordEnCry, avatar.toByteArray());
            byte[] value = _handleReadWriteStream.toStream(userDTO);
            _storage.put("user." + userName, value); //<user.userName, obj>
            assertNotNull(userDTO);
        }
    }

    @Test
    public void testHandleRegisterCase2() throws IOException {
        System.out.println("handleRegister");
        String name = "Trung Bim Bo";
        String userName = "trungdv1";
        String password = "123";
        String path = "/home/taipham/Pictures/Screenshot from 2018-07-05 16-27-22.png";
        UserDTO userDTO = null;
        ByteString avatar = ByteString.copyFrom(this.convertFile(path));
        if (!_storage.contains("user." + userName)) {
            String passwordEnCry = TokenDTO.base64Encode(password);
            userDTO = new UserDTO(name, userName, passwordEnCry, avatar.toByteArray());
            byte[] value = _handleReadWriteStream.toStream(userDTO);
            _storage.put("user." + userName, value); //<user.userName, obj>
        }
        assertNull(userDTO);
    }

    /**
     * Test of handleFindUser method, of class EchoServerInboundHandler.
     */
    @Test
    public void testHandleFindUserCase1() throws Exception {
        System.out.println("handleFindUser");
        String userName = "trungdv1";
        assertTrue(_storage.contains("user." + userName));
    }

    @Test
    public void testHandleFindUserCase2() throws Exception {
        System.out.println("handleFindUser");
        String userName = "trungdv2";
        assertFalse(_storage.contains("user." + userName));
    }

    /**
     * Test of handleCreateChannel method, of class EchoServerInboundHandler.
     */
    @Test
    public void testHandleCreateChannelCase1() throws Exception {
        System.out.println("handleCreateChannel");
        String userCreate = "taiptht";
        String channelName = "taiVu";
        String path = "/home/taipham/Pictures/Screenshot from 2018-07-05 16-27-22.png";
        ByteString avatar = ByteString.copyFrom(convertFile(path));
        String listMembers = "taiptht, vutq";
        ChannelDTO channelDTO = null;
        if (_storage.contains("channel." + channelName)) {
            channelDTO = new ChannelDTO(userCreate, channelName, avatar.toByteArray(), listMembers);
            byte[] value = _handleReadWriteStream.toStream(channelDTO);
            _storage.put("channel." + channelName, value); //<channle.channelName, objChannel>
            _storage.put(channelName + ".members", listMembers.getBytes("utf-8")); //<channelName.members, listMembers>
            assertNotNull(channelDTO);
        }
    }

    @Test
    public void testHandleCreateChannelCase2() throws Exception {
        System.out.println("handleCreateChannel");
        String userCreate = "taiptht";
        String channelName = "taiVu";
        String path = "/home/taipham/Pictures/Screenshot from 2018-07-05 16-27-22.png";
        ByteString avatar = ByteString.copyFrom(convertFile(path));
        String listMembers = "taiptht, vutq";
        ChannelDTO channelDTO = null;
        if (_storage.contains("channel." + channelName)) {
            channelDTO = new ChannelDTO(userCreate, channelName, avatar.toByteArray(), listMembers);
            byte[] value = _handleReadWriteStream.toStream(channelDTO);
            _storage.put("channel." + channelName, value); //<channle.channelName, objChannel>
            _storage.put(channelName + ".members", listMembers.getBytes("utf-8")); //<channelName.members, listMembers>
            assertNotNull(channelDTO);
        }
        assertNull(channelDTO);
    }

    /**
     * Test of handleGetAllChannel method, of class EchoServerInboundHandler.
     */
    @Test
    public void testHandleGetAllChannel() throws Exception {
        System.out.println("handleGetAllChannel");
        MyStorage storage = new RockDBApp("./storage");
        storage.connect();
        String userName = "taiptht";
        List<ResponseDTO.Channel> listChannelsResult = new ArrayList<>();
        String key = userName + ".channels";
        if (storage.contains(key)) {
            String listChannels = new String((byte[]) storage.get(key), "utf8");
            String[] elementsChannels = listChannels.split("\\, ");
            for (String channel : elementsChannels) {
                ChannelDTO channelDTO = new ChannelDTO();
                key = "channel." + channel;
                channelDTO = (ChannelDTO) _handleReadWriteStream.toObject((byte[]) storage.get(key));
                ResponseDTO.Channel resChannel = ResponseDTO.Channel.newBuilder()
                        .setUserCreate(channelDTO.getUserCreate())
                        .setChannelName(channelDTO.getChannelName())
                        .setAvatar(ByteString.copyFrom(channelDTO.getAvatar()))
                        .setListMembers(channelDTO.getListMembers())
                        .build();
                listChannelsResult.add(resChannel);
            }
            assertEquals(2, listChannelsResult.size());
        }
    }

    /**
     * Test of handleMessageChat method, of class EchoServerInboundHandler.
     */
    @Test
    public void testHandleMessageChat() throws Exception {
        System.out.println("handleMessageChat");
        MyStorage storage = new RockDBApp("./storage");
        storage.connect();
        String userName = "taiptht";
        String messBody = "hello moi nguoi";
        Date date = new Date();
        String id = String.valueOf(date.getTime());
        String channelName = "Tai_Vu_1";
        MessageDTO messageDTO = null;
        //save db
        messageDTO = new MessageDTO(id, messBody, userName, id);
        byte[] value = _handleReadWriteStream.toStream(messageDTO);
        storage.put(id, value); //<id, objectMess>
        if (storage.contains(channelName + ".messages")) {
            String messages = new String((byte[]) storage.get(channelName + ".messages"), "utf8");
            messages = messages + ", " + id;
            storage.put(channelName + ".messages", messages.getBytes("utf-8")); //<channelName.messages, idMessage>
        } else {
            storage.put(channelName + ".messages", id.getBytes("utf-8")); //<channelName.messages, idMessage>
        }
        assertNotNull(messageDTO);
    }

    /**
     * Test of handleGetAllMessage method, of class EchoServerInboundHandler.
     */
    @Test
    public void testHandleGetAllMessage() throws Exception {
        System.out.println("handleGetAllMessage");
        String key = "Tai_Vu_1.messages";
        List<ResponseDTO.Message> listMessagesResult = new ArrayList<>();
        MyStorage storage = new RockDBApp("./storage");
        storage.connect();

        if (storage.contains(key)) {
            String messagesId = new String((byte[]) storage.get(key), "utf8");
            String[] elements = messagesId.split("\\, ");
            for (String id : elements) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO = (MessageDTO) _handleReadWriteStream.toObject((byte[]) storage.get(id));
                ResponseDTO.Message resMessage = ResponseDTO.Message.newBuilder()
                        .setUserNameSend(messageDTO.getUserSend())
                        .setBody(messageDTO.getBody())
                        .setTimeSend(messageDTO.getTimeSend())
                        .build();
                listMessagesResult.add(resMessage);
            }
            assertEquals(3, listMessagesResult.size());
        }
    }

}
