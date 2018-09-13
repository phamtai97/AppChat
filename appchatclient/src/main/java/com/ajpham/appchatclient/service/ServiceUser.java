/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.service;

import com.ajpham.appchatclient.dto.RequestDTO;
import com.ajpham.appchatclient.nettyclient.EchoClientInboundHandler;
import com.google.protobuf.ByteString;

/**
 *
 * @author taipham
 */
public class ServiceUser {

    private static RequestDTO.User _user;
    private static RequestDTO.Request _msgRequest;
    private EchoClientInboundHandler _handler;

    public ServiceUser(EchoClientInboundHandler handler) {
        this._handler = handler;
    }

    public void sendMessageLogin(String userName, String password) {
        _user = RequestDTO.User.newBuilder()
                .setUserName(userName)
                .setPassword(password)
                .build();

        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.LOGIN)
                .setUser(_user)
                .build();
        this._handler.sendMessageSync(_msgRequest);
    }

    public void sendMessageRegister(String name, String userName, String password, byte[] avatar) {
        _user = RequestDTO.User.newBuilder()
                .setName(name)
                .setUserName(userName)
                .setPassword(password)
                .setAvatar(ByteString.copyFrom(avatar))
                .build();

        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.REGISTER)
                .setUser(_user)
                .build();
        this._handler.sendMessageSync(_msgRequest);
    }

    public void sendMessageFindUser(String token, String userName) {
        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.FINDUSER)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .setUser(RequestDTO.User.newBuilder()
                        .setUserName(userName)
                        .build())
                .build();
        this._handler.sendMessage(_msgRequest);
    }

    public void sendMessageLogout(String token) {
        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.LOGOUT)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .build();
        this._handler.sendMessage(_msgRequest);
    }

    public void sendMessageAuthencation(String token) {
        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.AUTHENCATION)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .build();
        this._handler.sendMessageSync(_msgRequest);
    }
}
