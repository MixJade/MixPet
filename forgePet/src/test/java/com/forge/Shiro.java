package com.forge;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Shiro {
    public static void main(String[] args) {
        String password = "123456";
        Md5Hash md5Hash = new Md5Hash(password, "pet");
        System.out.println(md5Hash.toHex());
    }
}
