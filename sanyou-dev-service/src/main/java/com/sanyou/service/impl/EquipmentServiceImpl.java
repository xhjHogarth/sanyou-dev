package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.EquipmentMapper;
import com.sanyou.mapper.EquipmentRecordsMapper;
import com.sanyou.mapper.FactoryMapper;
import com.sanyou.mapper.UserEquipmentMapper;
import com.sanyou.pojo.Equipment;
import com.sanyou.pojo.EquipmentRecords;
import com.sanyou.pojo.Factory;
import com.sanyou.pojo.UserEquipment;
import com.sanyou.pojo.vo.EquipmentVo;
import com.sanyou.service.EquipmentService;
import com.sanyou.utils.PagedResult;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/5
 * Time: 21:35
 * Version:V1.0
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentRecordsMapper equipmentRecordsMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private UserEquipmentMapper userEquipmentMapper;

    @Autowired
    private Sid sid;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void addEquipment(Equipment equipment) {
        String id = sid.nextShort();
        equipment.setId(id);
        equipment.setCreatetime(new Date());
        equipment.setEquipStatus((byte)1);
        equipment.setDeleteMark((byte)0);

        equipmentMapper.insert(equipment);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(EquipmentVo equipmentVo, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<EquipmentVo> list = equipmentMapper.query(equipmentVo);

        for (EquipmentVo vo : list) {
            String factoryId = vo.getFactoryId();
            if(StringUtils.isNotBlank(factoryId)){
                Factory factory = factoryMapper.selectByPrimaryKey(factoryId);
                if(factory != null)
                    vo.setFactoryName(factory.getFactoryName());
            }
            String subFactoryId = vo.getSubFactoryId();
            if(StringUtils.isNotBlank(subFactoryId)){
                Factory factory = factoryMapper.selectByPrimaryKey(subFactoryId);
                if(factory != null)
                    vo.setSubFactoryName(factory.getFactoryName());
            }

            if(vo.getEquipStatus() == 1)
                vo.setEquipStatusName("未上线");
            else if(vo.getEquipStatus() == 2)
                vo.setEquipStatusName("在线");
            else if(vo.getEquipStatus() == 3)
                vo.setEquipStatusName("已离线");

        }

        PageInfo<EquipmentVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void enableOrUnEnableEquip(String id, int mark) {
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setEnableMark((byte)mark);
        equipment.setUpdatetime(new Date());

        Example example = new Example(Equipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);
        equipmentMapper.updateByExampleSelective(equipment,example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateEquipInfo(Equipment equipment,String userId) {

        Equipment equipment1 = equipmentMapper.selectByPrimaryKey(equipment.getId());
        if(equipment1 != null){
            String content = "";
            if(equipment.getEquipCycle().doubleValue() != equipment1.getEquipCycle().doubleValue()){
                content =  "周期由" + equipment1.getEquipCycle().doubleValue() + "修改为" +
                        equipment.getEquipCycle().doubleValue();
            }
            if(equipment.getEquipHealthLimit().doubleValue() != equipment1.getEquipHealthLimit().doubleValue()){
                if(content.length() > 0)
                    content += ",";
                content =  content + "健康值上限由" + equipment1.getEquipHealthLimit().doubleValue() + "修改为" +
                        equipment.getEquipHealthLimit().doubleValue();
            }
            if(equipment.getEquipSubhealthLimit().doubleValue() != equipment1.getEquipSubhealthLimit().doubleValue()){
                if(content.length() > 0)
                    content += ",";
                content =  content + "亚健康值由" + equipment1.getEquipSubhealthLimit().doubleValue() + "修改为" +
                        equipment.getEquipSubhealthLimit().doubleValue();
            }
            EquipmentRecords equipmentRecords = new EquipmentRecords();
            String id = sid.nextShort();
            equipmentRecords.setId(id);
            equipmentRecords.setEquipNo(equipment.getEquipNo());
            equipmentRecords.setUserId(userId);
            equipmentRecords.setContent(content);
            equipmentRecords.setCreatetime(new Date());
            equipmentRecordsMapper.insert(equipmentRecords);
        }


        equipment.setUpdatetime(new Date());

        if(equipment.getDeleteMark() != null && equipment.getDeleteMark() == 1)
            equipment.setDeletetime(new Date());

        Example example = new Example(Equipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",equipment.getId());
        equipmentMapper.updateByExampleSelective(equipment,example);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<EquipmentVo> getEquipmentTree() {

        List<EquipmentVo> equipTree = new ArrayList<>();

        List<EquipmentVo> factoryList = equipmentMapper.getFactoryList();

        if(factoryList != null && factoryList.size() > 0){
            for (EquipmentVo factoryVo : factoryList) {

                String factoryId = factoryVo.getId();
                List<EquipmentVo> subFactoryList = equipmentMapper.getSubFactoryList(factoryId);
                if(subFactoryList != null && subFactoryList.size() > 0){
                    for (EquipmentVo subFactoryVo : subFactoryList) {
                        String subFactoryId = subFactoryVo.getId();

                        Example example = new Example(Equipment.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("factoryId",factoryId);
                        criteria.andEqualTo("subFactoryId",subFactoryId);
                        List<Equipment> equipmentList = equipmentMapper.selectByExample(example);

                        List<EquipmentVo> equipmentVos = new ArrayList<>();
                        for (Equipment equipment : equipmentList) {
                            EquipmentVo equipmentVo = new EquipmentVo();
                            BeanUtils.copyProperties(equipment,equipmentVo);
                            equipmentVo.setLabel(equipment.getEquipNo());
                            equipmentVos.add(equipmentVo);
                        }
                        subFactoryVo.setChildren(equipmentVos);
                    }
                }
                factoryVo.setChildren(subFactoryList);
            }
            equipTree = factoryList;
        }


        return equipTree;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Equipment> getUserEquip(String userId) {

        Example example = new Example(UserEquipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<UserEquipment> userEquipments = userEquipmentMapper.selectByExample(example);

        if(userEquipments.size() > 0){
            List<Equipment> equipmentList = new ArrayList<>();

            for (UserEquipment userEquipment : userEquipments) {
                String equipmentId = userEquipment.getEquipId();
                Equipment equipment = equipmentMapper.selectByPrimaryKey(equipmentId);
                equipmentList.add(equipment);
            }

            return equipmentList;
        }else{
            return new ArrayList<>();
        }

    }
}
