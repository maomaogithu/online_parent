package com.xiaou.msmservice.service;

import java.util.HashMap;
import java.util.Map;

/**
 * TOOD
 *
 * @Description
 * @Author Administrator
 * @Date$ 2023/4/28
 **/
public interface MsmService {
    boolean sendMsm(HashMap<String, Object> parmas, String phone);
}
