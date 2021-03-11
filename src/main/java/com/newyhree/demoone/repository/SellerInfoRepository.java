package com.newyhree.demoone.repository;


import com.newyhree.demoone.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String > {

   SellerInfo findByOpenId(String openId);
}
