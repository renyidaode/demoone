package com.newyhree.demoone.service;

import com.newyhree.demoone.dataObject.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenId(String openId);
}
