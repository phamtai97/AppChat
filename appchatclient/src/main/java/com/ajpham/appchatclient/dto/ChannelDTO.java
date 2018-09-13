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
public class ChannelDTO {
    private String _userCreate;
    private String _channelName;
    private byte[] _avatar;
    private String _listMembers;
    private int NumberMessageWait;
    private boolean _checkClick;
    
    public ChannelDTO(String _userCreate, String _channelName, byte[] _avatar, String _listMembers, int NumberMessageWait, boolean checkClick) {
        this._userCreate = _userCreate;
        this._channelName = _channelName;
        this._avatar = _avatar;
        this._listMembers = _listMembers;
        this.NumberMessageWait = NumberMessageWait;
        this._checkClick = checkClick;
    }

    public int getNumberMessageWait() {
        return NumberMessageWait;
    }

    public void setNumberMessageWait(int NumberMessageWait) {
        this.NumberMessageWait = NumberMessageWait;
    }

    public boolean isCheckClick() {
        return _checkClick;
    }

    public void setCheckClick(boolean _checkClick) {
        this._checkClick = _checkClick;
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

    @Override
    public String toString() {
        return this._channelName; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
