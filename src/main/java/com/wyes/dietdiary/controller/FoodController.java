package com.wyes.dietdiary.controller;

import com.wyes.dietdiary.model.entity.food;
import com.wyes.dietdiary.model.dao.foodDao;
import com.wyes.dietdiary.service.foodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/foodDB")


public class FoodController {
    @Autowired
    foodService foodservice;

    @GetMapping("/foodList")
    public ResponseEntity getAllFoods() {
        Iterable<food> foodList = foodservice.getFoods();
        return ResponseEntity.status(HttpStatus.OK).body(foodList);
    }

    @GetMapping("/foodList/{cat}")
    public ResponseEntity getCatFood(@PathVariable Integer cat) {
        List<food> foodList = foodservice.findByCat(cat);
        return ResponseEntity.status(HttpStatus.OK).body(foodList);
    }

    @GetMapping("/findFood/{id}")
    public Optional<food> getFood(@PathVariable Integer id) {
        Optional<food> fd = foodservice.findById(id);
        return fd;
    }

}
