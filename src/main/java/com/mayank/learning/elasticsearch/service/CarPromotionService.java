package com.mayank.learning.elasticsearch.service;

/*
    File Name : CarPromotionService.java
    
    @author Mayank Sharma
    First Created on 16-10-2020 at 18:43
*/

import java.util.Arrays;
import java.util.List;

public interface CarPromotionService {

    List<String> PROMOTION_TYPES = Arrays.asList("bonus", "discount");
    boolean isValidPromotionType(String promotionType);

}
