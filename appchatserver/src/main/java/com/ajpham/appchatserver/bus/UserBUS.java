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
public class UserBUS {

    public UserBUS() {
    }

    public static boolean checkUserLogin(String userName, String password) {
        if (userName.equals("") || password.equals("") || userName.length() > 20 || password.length() > 20) {
            return false;
        }
        return true;
    }

    public static boolean checkUserRegister(String name, String userName, String password, String pathAvatar) {
        if (name.equals("") || userName.equals("") || password.equals("")
                || name.length() > 20 || userName.length() > 20 || password.length() > 20 || pathAvatar.equals("Choose Avatar") || pathAvatar.equals("")) {
            return false;
        }
        return true;
    }

}
