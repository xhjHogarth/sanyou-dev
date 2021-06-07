package com.sanyou.mapper;

import com.sanyou.pojo.Equipment;
import com.sanyou.pojo.vo.EquipmentVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentMapper extends MyMapper<Equipment> {
    List<EquipmentVo> query(@Param("equip") EquipmentVo equipmentVo);

    List<EquipmentVo> getFactoryList();

    List<EquipmentVo> getSubFactoryList(String factoryId);
}