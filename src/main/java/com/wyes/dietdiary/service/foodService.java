package com.wyes.dietdiary.service;

import com.wyes.dietdiary.model.entity.food;
import com.wyes.dietdiary.model.dao.foodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
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

    public  List<food> findByCat(Integer cat){

        List<String> stringList = new ArrayList<>();
        stringList.add("蔬果類");
        stringList.add("豆魚蛋肉類");
        stringList.add("五穀與澱粉類");
        stringList.add("乳品類");
        stringList.add("油脂與堅果類");
        stringList.add("其他類別");
        stringList.add("加工食品類");

        Iterable<food> allfd=fooddao.findAll();
        List<food> target = new ArrayList<food>();
        allfd.forEach(target::add);

        List<food> reaultFd = new ArrayList<food>();

        for (int i = 0; i < target.size(); i++) {
            String tmp=target.get(i).getFoodCategory();
            if(target.get(i).getFoodCategory().equals(stringList.get(cat)))
            {
                reaultFd.add(target.get(i));
            }
        }

        return reaultFd;

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
