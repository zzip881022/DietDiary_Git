package com.wyes.dietdiary.controller;

import com.wyes.dietdiary.model.entity.food;
import com.wyes.dietdiary.model.dao.foodDao;
import com.wyes.dietdiary.service.foodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;



@RestController
@RequestMapping("/foodDB")


public class FoodController {
    @Autowired
    foodService foodservice;
    @GetMapping("/foodList")
    public ResponseEntity getTodos() {
        Iterable<food> foodList = foodservice.getFoods();
        return ResponseEntity.status(HttpStatus.OK).body(foodList);
    }


}