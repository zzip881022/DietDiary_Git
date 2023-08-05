package com.wyes.dietdiary.service;

import com.wyes.dietdiary.model.dao.foodDao;
import com.wyes.dietdiary.model.entity.diary;
import com.wyes.dietdiary.model.dao.diaryDao;

import com.wyes.dietdiary.model.entity.food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class diaryService {
    @Autowired
    diaryDao diarydao;


    public Integer createDiary(diary dy){
        diary newDiary=diarydao.save(dy);
        return newDiary.getDiaryID();
    }

    public Iterable<diary> getDiarys(){
        return diarydao.findAll();
    }

    public Optional<diary> findById(Integer id) {
        Optional<diary> dy = diarydao.findById(id);
        return dy;
    }

    public Boolean updateTodo(Integer id,diary dy) {
        Optional<diary> isExistDiary = findById(id);
        if (! isExistDiary.isPresent()) {
            return false;
        }
        diary newDiary = isExistDiary.get();
        if (dy.getWeight() == null||dy.getDiaryDate()==null||dy.getFoodID()==null||dy.getMealCategory()==null) {
            return false;
        }
        newDiary.setWeight(dy.getWeight());
        newDiary.setDiaryDate(dy.getDiaryDate());
        newDiary.setFoodID(dy.getFoodID());
        newDiary.setMealCategory(dy.getMealCategory());

        diarydao.save(newDiary);
        return true;
    }


    public Boolean deleteDiary(Integer id) {
        Optional<diary> findDiary = findById(id);
        if (!findDiary.isPresent()) {
            return false;
        }
        try {
            diarydao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
