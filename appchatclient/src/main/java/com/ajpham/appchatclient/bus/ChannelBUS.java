/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.bus;

/**
 *
 * @author taipham
 */
public class ChannelBUS {
    public ChannelBUS(){
        
    }
    
    public boolean checkRoomCreate(String listMembers, String roomName, String avatar){
        if(listMembers.equals("") || roomName.equals("") || avatar.equals("Choose Avatar") || roomName.length() > 20 || avatar.equals("")){
            return false;
        }
        return true;
    }
}
