package com.newyhree.demoone.controller;


import com.newyhree.demoone.VO.ResultVo;
import com.newyhree.demoone.dataObject.ProductCategory;
import com.newyhree.demoone.service.CategoryService;
import com.newyhree.demoone.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        List<ProductCategory> categoryList = categoryService.findAll();
        return ResultVoUtils.success();
    }

    //修改
    @GetMapping("/index")
    public ResultVo index(@RequestParam(value = "categoryId")Integer categoryId){

            ProductCategory productCategory = categoryService.getOne(categoryId);

            return ResultVoUtils.success(productCategory);
    }
}
