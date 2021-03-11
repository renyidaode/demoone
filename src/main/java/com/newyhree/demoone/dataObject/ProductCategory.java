package com.newyhree.demoone.dataObject;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@DynamicUpdate
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProductCategory implements Serializable {

    //类目Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

//    public ProductCategory(){}
//
//    public ProductCategory(String categoryName, Integer categoryType) {
//        this.categoryName = categoryName;
//        this.categoryType = categoryType;
//    }
}
