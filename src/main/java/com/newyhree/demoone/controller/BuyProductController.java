package com.newyhree.demoone.controller;


import com.newyhree.demoone.VO.ProductInfoVo;
import com.newyhree.demoone.VO.ProductVo;
import com.newyhree.demoone.VO.ResultVo;
import com.newyhree.demoone.dataObject.ProductCategory;
import com.newyhree.demoone.dataObject.ProductInfo;
import com.newyhree.demoone.service.CategoryService;
import com.newyhree.demoone.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //配置状态码和信息
        ResultVo resultVo = new ResultVo();

        //商品规格描述
        ProductInfoVo productInfoVos = new ProductInfoVo();

        //商品名称
        ProductVo productVos = new ProductVo();

        resultVo.setData(productVos);

        //商品规格描述
        productVos.setProductInfoVoList(Arrays.asList(productInfoVos));
        resultVo.setData(Arrays.asList(productVos));

        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //查询类目（）
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //查询拼装

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }


        //配置状态码和信息
        resultVo.setData(productVoList);
        resultVo.setCode(0);
        resultVo.setMsg("成功");

        return  resultVo;
    }
}
