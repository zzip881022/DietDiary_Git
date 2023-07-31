package com.wyes.dietdiary.controller;

import com.wyes.dietdiary.model.entity.food;
import com.wyes.dietdiary.service.foodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;


@RestController
@RequestMapping("/crawl")
public class CrawlController {

    @Autowired
    foodService foodservice;

    @GetMapping("/creatfoodDataBase")
    void creat_database(){
        List<food> db=new ArrayList<>();

        String category="ABCDEFG";
        List<Integer> foodlist= new ArrayList<>();

        for(int i=0;i<category.length();i++) {
            foodlist.addAll(getFoodList(category.charAt(i)+""));
        }

        ListIterator<Integer> it = foodlist.listIterator();

        while (it.hasNext()) {
            Integer x = getFoodData(it.next().toString());
        }


    }


    @GetMapping("/getfoodData/{foodpageID}")
    Integer getFoodData(@PathVariable String foodpageID){
        Integer rsp=-1;
        try {
            Connection connect = Jsoup.connect("https://health.udn.com/health/food/"+foodpageID);
            Document doc = connect.get();
            Elements foodname=doc.getElementsByClass("calories-minor-sorting__ingredient--name");
            Elements foodcat=doc.getElementsByClass("calories-minor-sorting__ingredient--type");
            Elements nutritions = doc.getElementsByClass("item--value");

            //---------------------爬下的資料都裝進model------------------------------------------------------------
            food newfood=new food();
            newfood.setFoodID(Integer.parseInt(foodpageID));
            newfood.setFoodName(foodname.text());
            newfood.setFoodCategory(foodcat.text());
            newfood.setFoodCal(Float.parseFloat(nutritions.get(0).text()));
            newfood.setFoodWater(Float.parseFloat(nutritions.get(1).text()));
            newfood.setFoodProtein(Float.parseFloat(nutritions.get(2).text()));
            newfood.setFoodFat(Float.parseFloat(nutritions.get(3).text()));
            newfood.setFoodCarb(Float.parseFloat(nutritions.get(4).text()));
            newfood.setFoodNA(Float.parseFloat(nutritions.get(5).text()));
            newfood.setFoodFiber(Float.parseFloat(nutritions.get(6).text()));
            newfood.setFoodDL(Float.parseFloat(nutritions.get(7).text()));
            //插入資料庫

            //如果該食物ID不存在資料庫
            if(!foodservice.findById(Integer.parseInt(foodpageID)).isPresent())
            {//加入資料庫
                rsp=foodservice.createFood(newfood);
            }else
            {
                foodservice.updateFood(Integer.parseInt(foodpageID),newfood);
            }


        }
        catch(IOException e){
            e.printStackTrace();

        }
        return rsp;
    }

    @GetMapping("/getfoodList/{cat}")
    List<Integer> getFoodList(@PathVariable String cat){

        List<Integer> foodIDList=new ArrayList<>();

        try {

            Connection connect = Jsoup.connect("https://health.udn.com/health/foodcate/"+cat);
            Document doc = connect.get();
            Elements lastPage=doc.getElementsByClass("pagenation__brief--number");

            int lastPageIndex=Integer.parseInt(lastPage.text());

            for(int i=1;i<=lastPageIndex;i++)
            {
                Connection connectPerPage = Jsoup.connect("https://health.udn.com/health/foodcate/"+cat+"?page="+i);
                Document docPerPage=connectPerPage.get();
                Elements links =docPerPage.select("a[href^=/health/food]");
                for (Element link : links){
                    foodIDList.add(Integer.parseInt(link.attr("href").replace("/health/food/","")));
                }

            }

        }
        catch(IOException e){
            e.printStackTrace();
        }

        return foodIDList;
    }




}
