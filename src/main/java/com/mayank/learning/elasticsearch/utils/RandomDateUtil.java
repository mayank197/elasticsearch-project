package com.mayank.learning.elasticsearch.utils;

/*
    File Name : RandomDateUtil.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 00:33
*/

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateUtil {

    private static final long minDay = LocalDate.of(2010, 1, 1).toEpochDay();

    private static final long maxDay = LocalDate.now().toEpochDay();

    public static LocalDate generateRandomLocalDate(){
        long randomDay = minDay + ThreadLocalRandom.current().nextLong(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }


}
