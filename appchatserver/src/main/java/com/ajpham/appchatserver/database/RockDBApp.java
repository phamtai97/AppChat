/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.database;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 *
 * @author taipham
 */
public class RockDBApp implements MyStorage<String, byte[]>{
    private RocksDB _rocksDB = null;
    private String _directory = "";
    public RockDBApp(String path){
        this._directory = path;
    }
    
    @Override
    public boolean connect() {
        try {
            RocksDB.loadLibrary();
            Options options = new Options().setCreateIfMissing(true);
            this._rocksDB = RocksDB.open(options, this._directory);
            return true;
        } catch (RocksDBException ex) {
            Logger.getLogger(RockDBApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean put(String key, byte[] value) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            this._rocksDB.put(byteKey, value);
            return true;
        } catch (UnsupportedEncodingException | RocksDBException ex) {
            Logger.getLogger(RockDBApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean remove(String key) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = this._rocksDB.get(byteKey);

            if (byteValue != null) {
                this._rocksDB.delete(byteKey);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean contains(String key) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = this._rocksDB.get(byteKey);
            return byteValue != null;
        } catch (UnsupportedEncodingException | RocksDBException ex) {
            Logger.getLogger(RockDBApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public byte[] get(String key) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = this._rocksDB.get(byteKey);

            if (byteValue != null) {
                return byteValue;
            }
            return null;
        } catch (UnsupportedEncodingException | RocksDBException ex) {
            Logger.getLogger(RockDBApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
