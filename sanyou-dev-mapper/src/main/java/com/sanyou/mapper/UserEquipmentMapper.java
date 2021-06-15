package com.sanyou.mapper;

import com.sanyou.pojo.UserEquipment;
import com.sanyou.utils.MyMapper;

import java.util.List;

public interface UserEquipmentMapper extends MyMapper<UserEquipment> {


    List<UserEquipment> checkAuth(String lineno, String userId);
}