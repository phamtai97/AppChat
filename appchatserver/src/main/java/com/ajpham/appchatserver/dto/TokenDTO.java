/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.dto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author taipham
 */
public class TokenDTO {
    final private static String ALG = "hmacSha256";
    final private static String privateKey = "secret";
    
    private static String hmacSha256(String msg, String alg) throws UnsupportedEncodingException, InvalidKeyException {
        String res = "";
        try {
            SecretKeySpec key = new SecretKeySpec(privateKey.getBytes("UTF-8"), alg);
            Mac mac = Mac.getInstance(alg);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            res = hash.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public static String base64Encode(String msg) {
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(msg.getBytes());
    }
    
    public static String base64Decode(String msg) {
        byte[] decoded = Base64.getUrlDecoder().decode(msg);
        return new String(decoded);
    }
      
    public static String createToken(String payload) throws UnsupportedEncodingException, InvalidKeyException{
        String encodeHeader = base64Encode(ALG);
        String encodePayload = base64Encode(payload);
        String sign = hmacSha256(encodeHeader + "." + encodePayload, ALG);
        return encodeHeader + "." + encodePayload + "." + sign;
    }
    
    public static boolean verifyToken(String token) throws UnsupportedEncodingException, InvalidKeyException{
        String[] elements = token.split("\\.");
        String decodeHeader = base64Decode(elements[0]);
        String decodePayload = base64Decode(elements[1]);
        String sign = elements[2];
        String signTmp = hmacSha256(elements[0] + "." + elements[1], decodeHeader);
        return sign.equals(signTmp);
    } 
}
