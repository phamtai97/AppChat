
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.nettyclient;

import com.ajpham.appchatclient.dto.ChannelDTO;
import com.ajpham.appchatclient.dto.MessageDTO;
import com.ajpham.appchatclient.dto.RequestDTO;
import com.ajpham.appchatclient.dto.ResponseDTO;
import com.ajpham.appchatclient.dto.UserDTO;
import com.ajpham.appchatclient.gui.clientgui.AppChatGUI;
import com.ajpham.appchatclient.gui.clientgui.CreateRoomFormGUI;
import com.ajpham.appchatclient.gui.clientgui.LoginFormGUI;
import com.ajpham.appchatclient.gui.clientgui.RegisterFormGUI;
import com.ajpham.appchatclient.gui.linegui.InforLine;
import com.ajpham.appchatclient.service.ServiceUser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taipham
 */
public class EchoClientInboundHandler extends SimpleChannelInboundHandler<ResponseDTO.Response> {

    private static RequestDTO.Request _msgRequest;
    private static Channel _channel;
    private static RegisterFormGUI _registerFormGUI;
    private static LoginFormGUI _loginFormGUI;
    private static CreateRoomFormGUI _createRoomFormGUI;
    private static AppChatGUI _appChatGUI;
    private static String _token;
    private static ServiceUser _serviceUser;

    public void setChannel(Channel channel) {
        _channel = channel;
    }

    public void setRegisterFormGUI(RegisterFormGUI registerFormGUI) {
        _registerFormGUI = registerFormGUI;
    }

    public void setLoginFormGUI(LoginFormGUI loginFormGUI) {
        _loginFormGUI = loginFormGUI;
    }

    public void setCreateRoomGUI(CreateRoomFormGUI createRoomFormGUI) {
        _createRoomFormGUI = createRoomFormGUI;
    }

    public void setAppChatGUI(AppChatGUI appChatGUI) {
        _appChatGUI = appChatGUI;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        _serviceUser = serviceUser;
    }

    public void sendMessage(RequestDTO.Request msgRequest) {
        this._channel.writeAndFlush(msgRequest);
    }

    public void sendMessageSync(RequestDTO.Request msgRequest) {
        try {
            this._channel.writeAndFlush(msgRequest).sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(EchoClientInboundHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String msg = "tai dep trai";
//        ChannelFuture future = ctx.writeAndFlush(msg);
//        System.out.println("client send: " + msg);
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String tmp = (String) msg;
//        System.out.println("client receive: " + tmp);
////        ctx.close();
//    }

    private void handleLogin(String token) {
        _serviceUser.sendMessageAuthencation(token);
    }

    public void handleRegister(ResponseDTO.User user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setUserName(user.getUserName());
        userDTO.setAvatar(user.getAvatar().toByteArray());
        _registerFormGUI.initAppChat(userDTO, token);
    }

    public void handleAuthencation(ResponseDTO.User user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setUserName(user.getUserName());
        userDTO.setAvatar(user.getAvatar().toByteArray());
        _loginFormGUI.initAppChat(userDTO, token);
    }

    public void handleCreateChannel(ResponseDTO.Channel channel) {
        if (channel.getUserCreate().equals(_appChatGUI.getUser().getUserName())) {
            _createRoomFormGUI.setVisible(false);
            _createRoomFormGUI.dispose();
        }
        //tạo channel
        ChannelDTO channelDTO = new ChannelDTO(channel.getUserCreate(), channel.getChannelName(), channel.getAvatar().toByteArray(), channel.getListMembers(), 0, true);
        _appChatGUI.addChannel(channelDTO);
    }

//    public void getAllChannels(){
//        _serviceChannel.sendMessageGetAllChannel(_token);
//    }
//    
    public void handleGetAllChannel(ResponseDTO.ListChannel listChannel) {
        List<ResponseDTO.Channel> list = listChannel.getListChannelsList();
        _appChatGUI.getJListChannel().setModel(_appChatGUI.getListChannelModel());
        for (ResponseDTO.Channel channel : list) {
            ChannelDTO channelDTO = new ChannelDTO(channel.getUserCreate(), channel.getChannelName(), channel.getAvatar().toByteArray(), channel.getListMembers(), 0, false);
            _appChatGUI.addChannelToListChannels(channelDTO);
        }
        _appChatGUI.getJListChannel().setCellRenderer(new InforLine());
        _appChatGUI.setChannelActive(0);
        _appChatGUI.getAllMessageChannelActive();
    }

    public void handleMessageChat(ResponseDTO.Message message, String channelName) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserSend(message.getUserNameSend());
        messageDTO.setBody(message.getBody());
        messageDTO.setTimeSend(message.getTimeSend());
        messageDTO.setChannelName(channelName);

        _appChatGUI.addMessageInChannel(messageDTO);

    }

    public void handleGetAllMessage(ResponseDTO.AllMessage allMessage, String channelName) {
        List<ResponseDTO.Message> list = allMessage.getAllMessageList();
        for (ResponseDTO.Message message : list) {
            MessageDTO messageDTO = new MessageDTO(message.getBody(), message.getUserNameSend(), channelName, message.getTimeSend());
            _appChatGUI.addMessageInChannel(messageDTO);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseDTO.Response msg) throws Exception {
        ResponseDTO.TypeMessage type = msg.getType();
        String status = "";
        switch (type) {
            case LOGIN:
                status = msg.getStatus();
                if (status.equals("Login Failed")) {
                    _loginFormGUI.setLbNotify(status);
                    break;
                }
                _token = msg.getToken().getToken();
                this.handleLogin(_token);
                break;
            case REGISTER:
                status = msg.getStatus();
                if (status.equals("Register Failed")) {
                    _registerFormGUI.setLbNotify(status);
                    break;
                }
                _token = msg.getToken().getToken();
                this.handleRegister(msg.getUser(), _token);
                break;
            case AUTHENCATION:
                status = msg.getStatus();
                if (status.equals("Inauthencation")) {
                    System.out.println("Inauthencation");
                    //log out
                    break;
                }
                this.handleAuthencation(msg.getUser(), _token);
                break;
            case FINDUSER:
                status = msg.getStatus();
                if (status.equals("Not Found")) {
                    _createRoomFormGUI.setStatusFind(status);
                } else {
                    _createRoomFormGUI.setStatusFind(status);
                    _createRoomFormGUI.setTfMembers(msg.getUser().getUserName());
                }
                break;
            case CREATECHANNEL:
                status = msg.getStatus();
                if (status.equals("Create Channel Failed")) {
                    _createRoomFormGUI.setStatus(status);
                } else {
                    this.handleCreateChannel(msg.getChannel());
                }
                break;
            case LISTCHANNEL:
                this.handleGetAllChannel(msg.getListChannel());
                break;
            case ALLMESSAGE:
                this.handleGetAllMessage(msg.getAllMessage(), msg.getChannelName());
                break;
            case CHAT:
                this.handleMessageChat(msg.getMessageChat(), msg.getChannelName());
                break;
            case LOGOUT:
                _appChatGUI.initLoginForm();
                break;
            default:
                break;
        }
    }
}
