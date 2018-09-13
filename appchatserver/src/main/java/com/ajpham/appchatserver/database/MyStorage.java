/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.database;

/**
 *
 * @author taipham
 */
public interface MyStorage <T, U> extends Database{

    @Override
    public boolean connect();
    
    boolean put(T key, U value);

    boolean remove(T key);

    boolean contains(T key);

    U get(T key);
}
