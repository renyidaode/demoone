package com.newyhree.demoone.service;

import com.newyhree.demoone.dataObject.ProductInfo;
import com.newyhree.demoone.dto.CartDTO;
import com.newyhree.demoone.exception.SellException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    ProductInfo GetOne(String productId);

    //查询所有在架商品
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList) throws SellException;

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList) throws SellException;
}
