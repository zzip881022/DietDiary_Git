package com.wyes.dietdiary.service;

import com.wyes.dietdiary.model.entity.food;
import com.wyes.dietdiary.model.dao.foodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class foodService {
    @Autowired
    foodDao fooddao;

    public Integer createFood(food fd){
        food newFood=fooddao.save(fd);
        return newFood.getFoodID();
    }

    public Iterable<food> getFoods(){
        return fooddao.findAll();
    }

    public Optional<food> findById(Integer id) {
        Optional<food> fd = fooddao.findById(id);
        return fd;
    }

    public Boolean updateFood(Integer id,food fd) {
        Optional<food> isExistFood = findById(id);
        if (! isExistFood.isPresent()) {
            return false;
        }

        food newfood = isExistFood.get();
        newfood.setFoodCategory(fd.getFoodCategory());
        newfood.setFoodFiber(fd.getFoodFiber());
        newfood.setFoodWater(fd.getFoodWater());
        newfood.setFoodName(fd.getFoodName());
        newfood.setFoodNA(fd.getFoodNA());
        newfood.setFoodCal(fd.getFoodCal());
        newfood.setFoodCarb(fd.getFoodCarb());
        newfood.setFoodProtein(fd.getFoodProtein());
        newfood.setFoodDL(fd.getFoodDL());
        fooddao.save(newfood);

        return true;
    }

}
