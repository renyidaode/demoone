package com.newyhree.demoone.controller;

import com.newyhree.demoone.VO.ResultVo;
import com.newyhree.demoone.dataObject.ProductCategory;
import com.newyhree.demoone.dataObject.ProductInfo;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.form.ProductForm;
import com.newyhree.demoone.service.CategoryService;
import com.newyhree.demoone.service.ProductService;
import com.newyhree.demoone.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(@RequestParam(value = "page", defaultValue = "1")Integer page,
                         @RequestParam(value = "size", defaultValue = "10")Integer size,
                         Map<String, Object> map){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        return ResultVoUtils.success(productInfoPage);
    }

    @GetMapping("/onSale")
    @Transactional
    public ResultVo onSale(@RequestParam("productId")String productId) throws SellException {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            e.printStackTrace();
        }
        return ResultVoUtils.success();
    }

    @GetMapping("/offSale")
    @Transactional
    public ResultVo offSale(@RequestParam("productId")String productId) throws SellException {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            e.printStackTrace();
        }
        return ResultVoUtils.success();
    }

//    @GetMapping("/index")
//    public void index(@RequestParam(value = "productId", required = false)String productId) throws SellException {
//        if (!StringUtils.isEmpty(productId)){
//            ProductInfo productInfo = productService.GetOne(productId);
//        }
//        List<ProductCategory> categoryList = categoryService.findAll();
//
//    }
    @GetMapping("/index")
    public ResultVo index(@RequestParam(value = "productId")String productId) throws SellException {

        ProductInfo productInfo = productService.GetOne(productId);
        return ResultVoUtils.success(productInfo);
    }


    //, BindingResult bindingResult
    @PostMapping("/save")
    public ResultVo save(@Valid ProductForm form){
        ProductInfo productInfo = productService.GetOne(form.getProductId());

        BeanUtils.copyProperties(form, productInfo);
        try {
            productService.save(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVoUtils.success();
    }
}
