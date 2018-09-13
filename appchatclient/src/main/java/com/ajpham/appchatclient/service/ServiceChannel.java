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
public class ServiceChannel {

    private static RequestDTO.Channel _channel;
    private static RequestDTO.Request _msgRequest;
    private EchoClientInboundHandler _handler;

    public ServiceChannel(EchoClientInboundHandler handler) {
        this._handler = handler;
    }

    public void sendMessageGetAllChannel(String token) {
        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.LISTCHANNEL)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .build();
        this._handler.sendMessage(_msgRequest);
    }

    public void sendMessageCreateChannel(String token, String userCreate, String channelName, byte[] avatar, String listMembers) {
        this._channel = RequestDTO.Channel.newBuilder()
                .setUserCreate(userCreate)
                .setChannelName(channelName)
                .setAvatar(ByteString.copyFrom(avatar))
                .setListMembers(listMembers)
                .build();

        _msgRequest = RequestDTO.Request.newBuilder()
                .setType(RequestDTO.TypeMessage.CREATECHANNEL)
                .setToken(RequestDTO.Token.newBuilder()
                        .setToken(token)
                        .build())
                .setChannel(this._channel)
                .build();
        this._handler.sendMessage(_msgRequest);
    }
}
