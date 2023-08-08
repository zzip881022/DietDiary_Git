package com.wyes.dietdiary.service;

import com.wyes.dietdiary.model.dao.foodDao;
import com.wyes.dietdiary.model.entity.diary;
import com.wyes.dietdiary.model.dao.diaryDao;

import com.wyes.dietdiary.model.entity.food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public Iterable<diary> getDiarysByDateAndCat(String dt,String cat){
        Iterable<diary> diarylist=diarydao.findAll();
        List<diary> target = new ArrayList<diary>();
        diarylist.forEach(target::add);
        List<diary> result = new ArrayList<diary>();
        for (int i = 0; i < target.size(); i++) {
            Date date=target.get(i).getDiaryDate();
            String dateToString=String.format("%1$tY-%1$tm-%1$td", date);
            if(dateToString.equals(dt)&&target.get(i).getMealCategory().equals(cat))
            {
                result.add(target.get(i));
            }
        }
        return result;
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
