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
public class ChannelDTO implements Serializable{
    private String _userCreate;
    private String _channelName;
    private byte[] _avatar;
    private String _listMembers;

    public ChannelDTO(String _userCreate, String _channelName, byte[] _avatar, String _listMembers) {
        this._userCreate = _userCreate;
        this._channelName = _channelName;
        this._avatar = _avatar;
        this._listMembers = _listMembers;
    }

    public ChannelDTO() {
    }

    public String getUserCreate() {
        return _userCreate;
    }

    public void setUserCreate(String _userCreate) {
        this._userCreate = _userCreate;
    }

    public String getChannelName() {
        return _channelName;
    }

    public void setChannelName(String _channelName) {
        this._channelName = _channelName;
    }

    public byte[] getAvatar() {
        return _avatar;
    }

    public void setAvatar(byte[] _avatar) {
        this._avatar = _avatar;
    }

    public String getListMembers() {
        return _listMembers;
    }

    public void setListMembers(String _listMembers) {
        this._listMembers = _listMembers;
    }
}
