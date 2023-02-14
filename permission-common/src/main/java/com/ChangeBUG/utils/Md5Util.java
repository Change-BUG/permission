package com.ChangeBUG.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Md5Util {

    /**
     * 转换 为 MD5
     */
    public String String_Change_Md5(String str, int numberOfDigits) {
        String md5Str = "";
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
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

                if (numberOfDigits == 16) {
                    //16位
                    md5Str = buf.toString().substring(8, 24);
                } else if (numberOfDigits == 32) {
                    //32位
                    md5Str = buf.toString();
                } else {
                    System.out.println("numberOfDigits 只能是16位或者32位");
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str;
    }

}