package com.mayank.learning.elasticsearch.service.implementation;

/*
    File Name : DefaultCarPromotionServiceImpl.java
    
    @author Mayank Sharma
    First Created on 16-10-2020 at 18:45
*/

import com.mayank.learning.elasticsearch.service.CarPromotionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarPromotionServiceImpl implements CarPromotionService {

    @Override
    public boolean isValidPromotionType(String promotionType) {
        return PROMOTION_TYPES.contains(promotionType.toLowerCase());
    }
}
