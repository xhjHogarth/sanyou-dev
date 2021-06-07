package com.sanyou.mapper;

import com.sanyou.pojo.EquipmentRecords;
import com.sanyou.pojo.vo.EquipmentRecordsVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentRecordsMapper extends MyMapper<EquipmentRecords> {
    List<EquipmentRecordsVo> query(@Param("query") String query);
}