package com.mayank.learning.elasticsearch.conroller;

/*
    File Name : CarPromotionController.java
    
    @author Mayank Sharma
    First Created on 16-10-2020 at 18:51
*/

import com.mayank.learning.elasticsearch.common.IllegalApiParamException;
import com.mayank.learning.elasticsearch.entity.CarPromotion;
import com.mayank.learning.elasticsearch.repository.CarPromotionElasticRepository;
import com.mayank.learning.elasticsearch.service.CarPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarPromotionController {

    @Autowired
    private CarPromotionService carPromotionService;

    @Autowired
    private CarPromotionElasticRepository carPromotionElasticRepository;

    @GetMapping(value = "/promotions")
    public List<CarPromotion> listAvailablePromotions(@RequestParam(name = "type") String promotionType,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        if(!carPromotionService.isValidPromotionType(promotionType)){
            throw new IllegalApiParamException("Invalid Promotion Type : "+promotionType);
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        return carPromotionElasticRepository.findByType(promotionType, pageRequest).getContent();
    }

}
