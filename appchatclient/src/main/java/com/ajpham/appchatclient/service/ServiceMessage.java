/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.service;

import com.ajpham.appchatclient.dto.RequestDTO;
import com.ajpham.appchatclient.nettyclient.EchoClientInboundHandler;

/**
 *
 * @author taipham
 */
public class ServiceMessage {

    private static RequestDTO.Message _message;
    private static RequestDTO.Request _msgRequest;
    private EchoClientInboundHandler _handler;

    public ServiceMessage(EchoClientInboundHandler handler) {
        this._handler = handler;
    }

    public void sendMessageChat(String token, String channelName, String userNameSend, String body) {
        _message = RequestDTO.Message.newBuilder()
                .setUserNameSend(userNameSend)
                .setBody(body)
                .build();
        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.CHAT)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .setMessageChat(_message)
                .setChannelName(channelName)
                .build();
        this._handler.sendMessage(_msgRequest);
    }

    public void sendMessageGetAllMessageChannel(String token, String channelName) {
        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.ALLMESSAGE)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .setChannelName(channelName)
                .build();

        this._handler.sendMessage(_msgRequest);
    }
}
