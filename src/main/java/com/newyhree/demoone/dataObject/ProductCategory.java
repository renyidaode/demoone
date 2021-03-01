package com.newyhree.demoone.dataObject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Data
@DynamicUpdate
@Entity
public class ProductCategory{

    //类目Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private Integer categoryType;

    public ProductCategory(){}

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
