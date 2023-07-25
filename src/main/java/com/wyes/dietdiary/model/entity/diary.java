package com.wyes.dietdiary.model.entity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table
@Data
public class diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer diaryID;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    Date diaryDate= new Date();

    @Column
    String mealCategory;

    @Column
    Integer foodID;

    @Column
    Float weight;


}
