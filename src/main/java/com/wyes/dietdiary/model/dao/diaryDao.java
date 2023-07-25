package com.wyes.dietdiary.model.dao;

import com.wyes.dietdiary.model.entity.diary;
import org.springframework.data.repository.CrudRepository;
public interface diaryDao extends CrudRepository<diary, Integer> {
}
