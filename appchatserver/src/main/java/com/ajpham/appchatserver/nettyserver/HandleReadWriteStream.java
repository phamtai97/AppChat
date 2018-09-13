/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.nettyserver;

import com.ajpham.appchatserver.dto.ChannelDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author taipham
 */
public class HandleReadWriteStream {

    public byte[] toStream(Object obj) {
        // Reference for stream of bytes
        byte[] stream = null;
        // ObjectOutputStream is used to convert a Java object into OutputStream
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            stream = baos.toByteArray();
        } catch (IOException e) {
            // Error in serialization
            e.printStackTrace();
        }
        return stream;
    }

    public Object toObject(byte[] stream) throws ClassNotFoundException {
        Object obj = null;

        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(stream);
                ObjectInputStream ois = new ObjectInputStream(bais);) {
            obj = ois.readObject();
        } catch (IOException e) {
            // Error in de-serialization
            e.printStackTrace();
        }
        return obj;
    }

    public boolean compareChannel(ChannelDTO channel1, ChannelDTO channle2) {
        if (channel1.getChannelName().equals(channle2.getChannelName()) && channel1.getUserCreate().equals(channle2.getUserCreate())
                && channel1.getAvatar() == channle2.getAvatar() && channel1.getListMembers().equals(channle2.getListMembers())) {
            return true;
        }
        return false;
    }
}
