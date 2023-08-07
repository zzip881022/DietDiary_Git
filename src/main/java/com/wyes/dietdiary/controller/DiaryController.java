package com.wyes.dietdiary.controller;
import com.wyes.dietdiary.model.entity.diary;
import com.wyes.dietdiary.service.diaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/fooddiary")
public class DiaryController {
    @Autowired
    diaryService diaryservice;

    @PostMapping("/addNewDiary")
    public  ResponseEntity createDiary(@RequestBody diary dy) {
        Integer rlt=diaryservice.createDiary(dy);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

    @GetMapping("/getDiary/{date}/{cat}")
    public ResponseEntity getDiaryByDateCat(@PathVariable String datee,@PathVariable Integer cat){
        Iterable<diary> diaryList=diaryservice.getDiarysByDateAndCat(datee,cat);
        return ResponseEntity.status(HttpStatus.OK).body(diaryList);
    }





}
