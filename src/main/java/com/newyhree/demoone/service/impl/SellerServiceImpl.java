package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dataObject.SellerInfo;
import com.newyhree.demoone.repository.SellerInfoRepository;
import com.newyhree.demoone.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
        return null;
    }
}
