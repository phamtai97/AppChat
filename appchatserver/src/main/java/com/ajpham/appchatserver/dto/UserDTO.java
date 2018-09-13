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
public class UserDTO implements Serializable{
    private String _name;
    private String _userName;
    private String _password;
    private byte[] _avatar;

    public UserDTO(String _name, String _userName, String _password, byte[] _avatar) {
        this._name = _name;
        this._userName = _userName;
        this._password = _password;
        this._avatar = _avatar;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String _userName) {
        this._userName = _userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public byte[] getAvatar() {
        return _avatar;
    }

    public void setAvatar(byte[] _avatar) {
        this._avatar = _avatar;
    }    
}
