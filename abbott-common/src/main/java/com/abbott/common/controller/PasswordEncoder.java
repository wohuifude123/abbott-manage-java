package com.abbott.common.controller;

import java.security.MessageDigest;

public class PasswordEncoder {

    private Object salt;        // 盐值
    private String algorithm;   // 加密方式

    public PasswordEncoder(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    /**
     * MD5 加密
     * @param password 密码
     * @return 32位密文（小写）
     */
    public String encryption(String password) {
        String re_md5 = new String();
        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(mergePasswordAndSalt(password, salt).getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public String mergePasswordAndSalt (String password, Object salt) {
        String saltStr = salt.toString();
        String passwordAndSalt = password + saltStr;
        if (password == null) {
            password = "";
        }
        if ((saltStr == null) || "".equals(saltStr)) {
            return password;
        } else {
//            return password + salt.toString();
            return passwordAndSalt;
        }
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encryption(rawPass);
        return pass1.equals(pass2);
    }

//    public static void main(String[] args) {
//        String salt = ":liu";
//        PasswordEncoder passwordEncoder = new PasswordEncoder(salt, "MD5");
//        System.out.println(passwordEncoder.encryption("123456"));
//
//        Boolean flagPassword;
//        flagPassword = passwordEncoder.isPasswordValid("29d73f3ed156fae61039213bc8f8b590", "123456");
//        System.out.println(flagPassword);
//    }
}