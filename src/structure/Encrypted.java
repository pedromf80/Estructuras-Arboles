/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Encrypted {

    public Encrypted() {
    }

    //fucion util para generar hash MD5
    public String getMD5(String encrypPass) {
        String crypted = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update();
            byte[] hash = md.digest(encrypPass.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff)+0x100, 16).substring(1));
            }
            crypted = sb.toString();
            return crypted;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypted.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crypted;
    }      

    //funcion util para generar hash SHA-256
    public String getSHA256(int index, String Timestamp, String previoushash, String data, BigInteger nonce){
        String stringToHhash = index+Timestamp+previoushash+data+nonce;
        String encoded = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update();
            byte[] hash = md.digest(stringToHhash.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.getEncoder().encodeToString(hash);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypted.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encoded;
    }

}