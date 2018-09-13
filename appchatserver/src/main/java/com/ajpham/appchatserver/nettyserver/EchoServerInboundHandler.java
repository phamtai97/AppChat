/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.nettyserver;

import com.ajpham.appchatserver.bus.ChannelBUS;
import com.ajpham.appchatserver.bus.UserBUS;
import com.ajpham.appchatserver.database.MyStorage;
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
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taipham
 */
public class EchoServerInboundHandler extends SimpleChannelInboundHandler<RequestDTO.Request> {
    
    private ResponseDTO.Response _msgResponse;
    private ChannelGroup _channelGroup;
    private ConcurrentHashMap<String, ChannelId> _mapUserChannel; //userName, channelId
    private SimpleDateFormat _simpleDateFormat;
    private static MyStorage _storage;
    private static HandleReadWriteStream _handleReadWriteStream;
    final private static String ALG = "hmacSha256";
    final private static String pattern = "yyyy-MM-dd HH:mm:ss";
    
    public EchoServerInboundHandler(MyStorage database, ChannelGroup channelGroup, ConcurrentHashMap<String, ChannelId> mapUserChannel) {
        _storage = database;
        _handleReadWriteStream = new HandleReadWriteStream();
        this._channelGroup = channelGroup;
        this._mapUserChannel = mapUserChannel;
        this._simpleDateFormat = new SimpleDateFormat(pattern);
    }
    
    public void sendMessage(Channel channel, ResponseDTO.Response msgResponse) {
        channel.writeAndFlush(msgResponse);
    }
    
    public Channel getChannel(ChannelId channelId) {
        return _channelGroup.find(channelId);
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        _channelGroup.add(ctx.channel());
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        _channelGroup.remove(ctx.channel());
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient: " + incoming.remoteAddress() + " Active");
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient: " + incoming.remoteAddress() + " In Acitve");
    }

//     @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                .addListener(ChannelFutureListener.CLOSE);
//    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    
    public void handleLogin(ChannelHandlerContext ctx, RequestDTO.User user) throws UnsupportedEncodingException, InvalidKeyException {
        String userName = user.getUserName();
        String password = user.getPassword();
        if (UserBUS.checkUserLogin(userName, password)) {
            //check db
            if (_storage.contains("user." + userName)) {
                try {
                    UserDTO userDTO = (UserDTO) _handleReadWriteStream.toObject((byte[]) _storage.get("user." + userName));
                    String passwordDecode = TokenDTO.base64Decode(userDTO.getPassword());
                    if (password.equals(passwordDecode)) {
                        //tao token
                        String token = TokenDTO.createToken(userName);
                        this._msgResponse = ResponseDTO.Response.newBuilder()
                                .setType(ResponseDTO.TypeMessage.LOGIN)
                                .setToken(ResponseDTO.Token.newBuilder()
                                        .setToken(token))
                                .setStatus("Login Success")
                                .build();
                    } else {
                        this._msgResponse = ResponseDTO.Response.newBuilder()
                                .setType(ResponseDTO.TypeMessage.LOGIN)
                                .setStatus("Login Failed")
                                .build();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EchoServerInboundHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this._msgResponse = ResponseDTO.Response.newBuilder()
                        .setType(ResponseDTO.TypeMessage.LOGIN)
                        .setStatus("Login Failed")
                        .build();
            }
        } else {
            this._msgResponse = ResponseDTO.Response.newBuilder()
                    .setType(ResponseDTO.TypeMessage.LOGIN)
                    .setStatus("Login Failed")
                    .build();
        }
        
        this.sendMessage(ctx.channel(), this._msgResponse);
    }
    
    
    public void handleRegister(ChannelHandlerContext ctx, RequestDTO.User user) {
        String name = user.getName();
        String userName = user.getUserName();
        String password = user.getPassword();
        ByteString avatar = user.getAvatar();
        
        if (UserBUS.checkUserRegister(name, userName, password, String.valueOf(avatar))) {
            if (_storage.contains("user." + userName)) {
                this._msgResponse = ResponseDTO.Response.newBuilder()
                        .setType(ResponseDTO.TypeMessage.REGISTER)
                        .setStatus("Register Failed")
                        .build();
            } else {
                //save db
                //encryption password
                String passwordEnCry = TokenDTO.base64Encode(password);
                UserDTO userDTO = new UserDTO(name, userName, passwordEnCry, avatar.toByteArray());
                byte[] value = _handleReadWriteStream.toStream(userDTO);
                _storage.put("user." + userName, value); //<user.userName, obj>

                try {
                    //tao token
                    String token = TokenDTO.createToken(userName);
                    this._msgResponse = ResponseDTO.Response.newBuilder()
                            .setType(ResponseDTO.TypeMessage.REGISTER)
                            .setToken(ResponseDTO.Token.newBuilder()
                                    .setToken(token))
                            .setUser(ResponseDTO.User.newBuilder()
                                    .setName(name)
                                    .setUserName(userName)
                                    .setAvatar(avatar)
                                    .build())
                            .setStatus("Register Success")
                            .build();
                    this._mapUserChannel.put(userName, ctx.channel().id()); //<userName, ChannelId>
                } catch (UnsupportedEncodingException | InvalidKeyException ex) {
                    Logger.getLogger(EchoServerInboundHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            this._msgResponse = ResponseDTO.Response.newBuilder()
                    .setType(ResponseDTO.TypeMessage.REGISTER)
                    .setStatus("Register Failed")
                    .build();
        }
        this.sendMessage(ctx.channel(), this._msgResponse);
    }
    
    public void handleAuthencation(ChannelHandlerContext ctx, RequestDTO.Token token) throws UnsupportedEncodingException, InvalidKeyException, ClassNotFoundException {
        String tokenTmp = token.getToken();
        String[] elements = tokenTmp.split("\\.");
        String userName = TokenDTO.base64Decode(elements[1]);
        UserDTO userDTO = (UserDTO) _handleReadWriteStream.toObject((byte[]) _storage.get("user." + userName));
        this._msgResponse = ResponseDTO.Response.newBuilder()
                .setType(ResponseDTO.TypeMessage.AUTHENCATION)
                .setUser(ResponseDTO.User.newBuilder()
                        .setName(userDTO.getName())
                        .setUserName(userDTO.getUserName())
                        .setAvatar(ByteString.copyFrom(userDTO.getAvatar()))
                        .build())
                .setStatus("Authencation")
                .build();
        this.sendMessage(ctx.channel(), this._msgResponse);
        this._mapUserChannel.put(userName, ctx.channel().id()); //<userName, ChannelId>
    }
    
    public void handleFindUser(ChannelHandlerContext ctx, RequestDTO.User user) throws UnsupportedEncodingException, InvalidKeyException {
        if (_storage.contains("user." + user.getUserName())) {//find
            _msgResponse = ResponseDTO.Response.newBuilder()
                    .setType(ResponseDTO.TypeMessage.FINDUSER)
                    .setUser(ResponseDTO.User.newBuilder()
                            .setUserName(user.getUserName())
                            .build())
                    .setStatus("Find")
                    .build();
        } else {
            _msgResponse = ResponseDTO.Response.newBuilder()
                    .setType(ResponseDTO.TypeMessage.FINDUSER)
                    .setStatus("Not Found")
                    .build();
        }
        this.sendMessage(ctx.channel(), this._msgResponse);
    }
    
    public void addChannelInUserChannel(String userName, String channelName) throws UnsupportedEncodingException {
        if (_storage.contains(userName + ".channels")) {
            String channels = new String((byte[]) _storage.get(userName + ".channels"), "utf8");
            channels = channels + ", " + channelName;
            _storage.put(userName + ".channels", channels.getBytes("utf-8")); //<userName.channels, channels>
        } else {
            _storage.put(userName + ".channels", channelName.getBytes("utf-8"));
        }
    }
    
    public void handleCreateChannel(ChannelHandlerContext ctx, RequestDTO.Channel channelCreate) throws UnsupportedEncodingException, ClassNotFoundException {
        String userCreate = channelCreate.getUserCreate();
        String channelName = channelCreate.getChannelName();
        ByteString avatar = channelCreate.getAvatar();
        String listMembers = channelCreate.getListMembers();
        
        if (ChannelBUS.checkRoomCreate(userCreate, listMembers, channelName, String.valueOf(avatar))) 
        {
            //check db name channel
            if (_storage.contains("channel." + channelName)) {
                _msgResponse = ResponseDTO.Response.newBuilder()
                        .setType(ResponseDTO.TypeMessage.CREATECHANNEL)
                        .setStatus("Create Channel Failed")
                        .build();
                this.sendMessage(ctx.channel(), _msgResponse);
            } else {
                //save db
                ChannelDTO channelDTO = new ChannelDTO(userCreate, channelName, avatar.toByteArray(), listMembers);
                byte[] value = _handleReadWriteStream.toStream(channelDTO);
                _storage.put("channel." + channelName, value); //<channle.channelName, objChannel>
                _storage.put(channelName + ".members", listMembers.getBytes("utf-8")); //<channelName.members, listMembers>

                //gui info channel ve cho tat ca user la member cua channel ma dang online
                _msgResponse = ResponseDTO.Response.newBuilder()
                        .setType(ResponseDTO.TypeMessage.CREATECHANNEL)
                        .setChannel(ResponseDTO.Channel.newBuilder()
                                .setUserCreate(userCreate)
                                .setChannelName(channelName)
                                .setAvatar(avatar)
                                .setListMembers(listMembers)
                                .build())
                        .setStatus("Create Channel Success")
                        .build();
                String[] elements = listMembers.split("\\, ");
                ChannelId channelId;
                Channel channel;
                for (String member : elements) {
                    this.addChannelInUserChannel(member, channelName);
                    channelId = _mapUserChannel.get(member);
                    if (channelId != null) {
                        channel = this.getChannel(channelId);
                        sendMessage(channel, _msgResponse);
                    }
                }
            }
        }
    }
    
    public boolean verifyToken(ChannelHandlerContext ctx, RequestDTO.Token token) throws UnsupportedEncodingException, InvalidKeyException {
        if (!TokenDTO.verifyToken(token.getToken())) {
            this._msgResponse = ResponseDTO.Response.newBuilder()
                    .setStatus("Inauthencation")
                    .build();
            this.sendMessage(ctx.channel(), this._msgResponse);
            return false;
        }
        return true;
    }
    
    public void handleGetAllChannel(ChannelHandlerContext ctx, RequestDTO.Token token) throws UnsupportedEncodingException, ClassNotFoundException {
        //get userName from token
        String tokenTmp = token.getToken();
        String[] elements = tokenTmp.split("\\.");
        String userName = TokenDTO.base64Decode(elements[1]);
        List<ResponseDTO.Channel> listChannelsResult = new ArrayList<>();
        //get list channel from db
        String key = userName + ".channels";
        if (_storage.contains(key)) {
            
            String listChannels = new String((byte[]) _storage.get(key), "utf8");
            String[] elementsChannels = listChannels.split("\\, ");
            for (String channel : elementsChannels) {
                ChannelDTO channelDTO = new ChannelDTO();
                key = "channel." + channel;
                channelDTO = (ChannelDTO) _handleReadWriteStream.toObject((byte[]) _storage.get(key));
                ResponseDTO.Channel resChannel = ResponseDTO.Channel.newBuilder()
                        .setUserCreate(channelDTO.getUserCreate())
                        .setChannelName(channelDTO.getChannelName())
                        .setAvatar(ByteString.copyFrom(channelDTO.getAvatar()))
                        .setListMembers(channelDTO.getListMembers())
                        .build();
                listChannelsResult.add(resChannel);
            }
        }
        
        Iterable<ResponseDTO.Channel> iterableChannels = listChannelsResult;
        
        ResponseDTO.ListChannel resListChannels = ResponseDTO.ListChannel.newBuilder()
                .addAllListChannels(iterableChannels)
                .build();
        
        _msgResponse = ResponseDTO.Response.newBuilder()
                .setType(ResponseDTO.TypeMessage.LISTCHANNEL)
                .setListChannel(resListChannels)
                .build();
        this.sendMessage(ctx.channel(), _msgResponse);
    }
    
    public void handleLogout(ChannelHandlerContext ctx, RequestDTO.Token token) {
        String tokenTmp = token.getToken();
        String[] elements = tokenTmp.split("\\.");
        String userName = TokenDTO.base64Decode(elements[1]);
        ChannelId channelId = _mapUserChannel.get(userName);
        _msgResponse = ResponseDTO.Response.newBuilder()
                .setType(ResponseDTO.TypeMessage.LOGOUT)
                .setStatus("Logout Success")
                .build();
        this.sendMessage(ctx.channel(), _msgResponse);
        _mapUserChannel.remove(userName);
    }
    
    public void addIdMessageInChannelMessage(String channelName, String idMessage) throws UnsupportedEncodingException {
        if (_storage.contains(channelName + ".messages")) {
            String messages = new String((byte[]) _storage.get(channelName + ".messages"), "utf8");
            messages = messages + ", " + idMessage;
            _storage.put(channelName + ".messages", messages.getBytes("utf-8")); //<channelName.messages, idMessage>
        } else {
            _storage.put(channelName + ".messages", idMessage.getBytes("utf-8")); //<channelName.messages, idMessage>
        }
    }
    
    public void handleMessageChat(ChannelHandlerContext ctx, RequestDTO.Token token, RequestDTO.Message message, String channelName) throws UnsupportedEncodingException {
        String tokenTmp = token.getToken();
        String[] elements = tokenTmp.split("\\.");
        String userName = TokenDTO.base64Decode(elements[1]);
        String messBody = message.getBody();
        Date date = new Date();
        String id = String.valueOf(date.getTime());

        //save db
        MessageDTO messageDTO = new MessageDTO(id, messBody, userName, id);
        byte[] value = _handleReadWriteStream.toStream(messageDTO);
        _storage.put(id, value); //<id, objectMess>
        addIdMessageInChannelMessage(channelName, id); //<channelName.messages, idMessage>

        //send message to user online
        _msgResponse = ResponseDTO.Response.newBuilder()
                .setType(ResponseDTO.TypeMessage.CHAT)
                .setMessageChat(ResponseDTO.Message.newBuilder()
                        .setBody(messBody)
                        .setUserNameSend(userName)
                        .setTimeSend(id)
                        .build())
                .setChannelName(channelName)
                .build();
        String listMembers = new String((byte[]) _storage.get(channelName + ".members"), "utf8");
        elements = listMembers.split("\\, ");
        ChannelId channelId;
        Channel channel;
        for (String element : elements) {
            channelId = _mapUserChannel.get(element);
            if (channelId != null) {
                channel = this.getChannel(channelId);
                sendMessage(channel, _msgResponse);
            }
        }
    }
    
    public void handleGetAllMessage(ChannelHandlerContext ctx, RequestDTO.Token token, String channelName) throws UnsupportedEncodingException, ClassNotFoundException {
        String key = channelName + ".messages";
        List<ResponseDTO.Message> listMessagesResult = new ArrayList<>();
        if (_storage.contains(key)) {
            String messagesId = new String((byte[]) _storage.get(key), "utf8");
            String[] elements = messagesId.split("\\, ");
            for (String id : elements) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO = (MessageDTO) _handleReadWriteStream.toObject((byte[]) _storage.get(id));
                ResponseDTO.Message resMessage = ResponseDTO.Message.newBuilder()
                        .setUserNameSend(messageDTO.getUserSend())
                        .setBody(messageDTO.getBody())
                        .setTimeSend(messageDTO.getTimeSend())
                        .build();
                listMessagesResult.add(resMessage);
            }
            
            Iterable<ResponseDTO.Message> iterableMessages = listMessagesResult;
            
            ResponseDTO.AllMessage resAllMessage = ResponseDTO.AllMessage.newBuilder()
                    .addAllAllMessage(iterableMessages)
                    .build();
            
            _msgResponse = ResponseDTO.Response.newBuilder()
                    .setType(ResponseDTO.TypeMessage.ALLMESSAGE)
                    .setAllMessage(resAllMessage)
                    .setChannelName(channelName)
                    .build();
            this.sendMessage(ctx.channel(), _msgResponse);
        }
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestDTO.Request msg) throws Exception {
        RequestDTO.TypeMessage type = msg.getType();
        
        switch (type) {
            case LOGIN:
                this.handleLogin(ctx, msg.getUser());
                break;
            case REGISTER:
                this.handleRegister(ctx, msg.getUser());
                break;
            case AUTHENCATION:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleAuthencation(ctx, msg.getToken());
                }
                break;
            case FINDUSER:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleFindUser(ctx, msg.getUser());
                }
                break;
            case CREATECHANNEL:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleCreateChannel(ctx, msg.getChannel());
                }
                break;
            case LISTCHANNEL:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleGetAllChannel(ctx, msg.getToken());
                }
                break;
            case ALLMESSAGE:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleGetAllMessage(ctx, msg.getToken(), msg.getChannelName());
                }
                break;
            case CHAT:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleMessageChat(ctx, msg.getToken(), msg.getMessageChat(), msg.getChannelName());
                }
                break;
            case LOGOUT:
                if (this.verifyToken(ctx, msg.getToken())) {
                    this.handleLogout(ctx, msg.getToken());
                }
                break;
            default:
                break;
        }
        
    }
}
