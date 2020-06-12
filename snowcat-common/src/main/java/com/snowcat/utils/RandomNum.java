package com.snowcat.utils;

import java.util.Random;

public class RandomNum {
    public static int randomNum(){
        Random random = new Random();
        int i = random.nextInt(10000);
        return i;
    }
}
