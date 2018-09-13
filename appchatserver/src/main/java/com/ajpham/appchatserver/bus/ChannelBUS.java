/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.bus;

/**
 *
 * @author taipham
 */
public class ChannelBUS {

    public ChannelBUS() {

    }

    public static boolean checkRoomCreate(String userCreate, String listMembers, String roomName, String avatar) {
        if (userCreate.equals("") || listMembers.equals("") || roomName.equals("") || avatar.equals("Choose Avatar")
                || userCreate.length() > 20 || roomName.length() > 20 || avatar.equals("")) {
            return false;
        }
        return true;
    }
}
