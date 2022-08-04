package com.hspedu.smallchange;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }
}
