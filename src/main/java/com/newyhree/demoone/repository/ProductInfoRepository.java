package com.newyhree.demoone.repository;

import com.newyhree.demoone.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer prouductStatus);

    ProductInfo findByProductId(String prouductId);
}
