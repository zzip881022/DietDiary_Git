package com.wyes.dietdiary.model.entity;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table
@Data
public class food {

    @Id
    Integer foodID;

    @Column(nullable = false)
    String foodName;

    @Column(nullable = false)
    String foodCategory;

    @Column
    Float foodCal;

    @Column
    Float foodWater;

    @Column
    Float foodProtein;

    @Column
    Float foodFat;

    @Column
    Float foodCarb;

    @Column
    Float foodNA;

    @Column
    Float foodFiber;

    @Column
    Float foodDL;

}
