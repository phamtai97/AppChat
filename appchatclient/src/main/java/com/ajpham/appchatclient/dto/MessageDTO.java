/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.dto;

/**
 *
 * @author taipham
 */
public class MessageDTO  {
    private String _body;
    private String _userSend;
    private String _channelName;
    private String _timeSend;

    public MessageDTO() {
    }

    public MessageDTO(String _body, String _userSend, String _channelName, String _timeSend) {
        this._body = _body;
        this._userSend = _userSend;
        this._channelName = _channelName;
        this._timeSend = _timeSend;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String _body) {
        this._body = _body;
    }

    public String getUserSend() {
        return _userSend;
    }

    public void setUserSend(String _userSend) {
        this._userSend = _userSend;
    }

    public String getChannelName() {
        return _channelName;
    }

    public void setChannelName(String _channelName) {
        this._channelName = _channelName;
    }

    public String getTimeSend() {
        return _timeSend;
    }

    public void setTimeSend(String _timeSend) {
        this._timeSend = _timeSend;
    }

    
}
