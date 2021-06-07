package com.sanyou.service;

import com.sanyou.pojo.Equipment;
import com.sanyou.pojo.vo.EquipmentVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/5
 * Time: 21:34
 * Version:V1.0
 */
public interface EquipmentService {

    /**
     * 新增设备
     * @param equipment
     */
    void addEquipment(Equipment equipment);

    /**
     * 分页查询设备
     * @param equipmentVo
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult query(EquipmentVo equipmentVo, Integer page, Integer pageSize);

    /**
     * 启用/禁用设备
     * @param id
     * @param mark
     */
    void enableOrUnEnableEquip(String id, int mark);

    /**
     * 修改设备信息
     * @param equipment
     * @param userId
     */
    void updateEquipInfo(Equipment equipment,String userId);

    /**
     * 查询所有设备,生成树结构返回
     * @return
     */
    List<EquipmentVo> getEquipmentTree();

    /**
     * 查询用户拥有的设备权限
     * @param userId 用户id
     * @return
     */
    List<Equipment> getUserEquip(String userId);
}
