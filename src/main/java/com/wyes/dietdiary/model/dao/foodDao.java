package com.wyes.dietdiary.model.dao;

import com.wyes.dietdiary.model.entity.food;
import org.springframework.data.repository.CrudRepository;
public interface foodDao extends CrudRepository<food, Integer>{

}
