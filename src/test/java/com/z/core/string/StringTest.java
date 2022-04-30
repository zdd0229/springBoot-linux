package com.z.core.string;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StringTest {
    @Test
    public void testHashCode() {
        String test = "kajshlfaksjdh";
        System.out.println(test.hashCode());
    }

    @Test
    public void testConstruction() throws UnsupportedEncodingException {

        String a = "1123";
        String b = new String("222");
        String c = new String(new char[]{'2', '好'});
        String d = new String(new int[]{0x61, 0x62, 0x63}, 0, 3);

        char[] ints = "中国".toCharArray();
        System.out.println(ArrayUtils.toString(ints));
        byte[] defaultByte = "中国".getBytes();
        byte[] utfByte = "中国".getBytes("utf-8");
        printHexArray(defaultByte);
        printHexArray(utfByte);
        String e = new String(new byte[]{-28, -72, -83, -27, -101, -67}, 0, 6, Charset.defaultCharset());

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("0");
        threadLocal.set("1");
        threadLocal.set("2");
        String s = threadLocal.get();
        System.out.println(s);

    }

    public static void printHexArray(byte[] array) {
        for (byte t : array) {
            System.out.print(String.format("%d ", t));
        }
        System.out.println();
    }

}
