/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.dto;

import java.io.Serializable;

/**
 *
 * @author taipham
 */
public class MessageDTO implements Serializable{
    private String _id;
    private String _body;
    private String _userSend;
    private String _timeSend; 

    public MessageDTO(String _id, String _body, String _userSend, String _timeSend) {
        this._id = _id;
        this._body = _body;
        this._userSend = _userSend;
        this._timeSend = _timeSend;
    }

    public MessageDTO() {
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

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTimeSend() {
        return _timeSend;
    }

    public void setTimeSend(String _timeSend) {
        this._timeSend = _timeSend;
    }
    
    
}
