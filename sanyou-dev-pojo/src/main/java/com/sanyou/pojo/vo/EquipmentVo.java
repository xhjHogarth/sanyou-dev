package com.sanyou.pojo.vo;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/5
 * Time: 22:42
 * Version:V1.0
 */
public class EquipmentVo {


    private String id;
    private String equipNo;
    private String factoryId;
    private String subFactoryId;
    private Byte equipStatus;
    private Date equipOnlineTime;
    private Integer equipCycle;
    private Double equipHealthLimit;
    private Double equipSubhealthLimit;
    private String equipAddress;
    private Double equipLat;
    private Double equipLng;
    private Date createtime;
    private Date updatetime;
    private Date deletetime;
    private Byte deleteMark;
    private Byte enableMark;

    private String query;
    private String factoryName;
    private String subFactoryName;
    private String equipStatusName;

    private List<EquipmentVo> children;
    private String label;

    public List<EquipmentVo> getChildren() {
        return children;
    }

    public void setChildren(List<EquipmentVo> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEquipStatusName() {
        return equipStatusName;
    }

    public void setEquipStatusName(String equipStatusName) {
        this.equipStatusName = equipStatusName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getSubFactoryId() {
        return subFactoryId;
    }

    public void setSubFactoryId(String subFactoryId) {
        this.subFactoryId = subFactoryId;
    }

    public Byte getEquipStatus() {
        return equipStatus;
    }

    public void setEquipStatus(Byte equipStatus) {
        this.equipStatus = equipStatus;
    }

    public Date getEquipOnlineTime() {
        return equipOnlineTime;
    }

    public void setEquipOnlineTime(Date equipOnlineTime) {
        this.equipOnlineTime = equipOnlineTime;
    }

    public Integer getEquipCycle() {
        return equipCycle;
    }

    public void setEquipCycle(Integer equipCycle) {
        this.equipCycle = equipCycle;
    }

    public Double getEquipHealthLimit() {
        return equipHealthLimit;
    }

    public void setEquipHealthLimit(Double equipHealthLimit) {
        this.equipHealthLimit = equipHealthLimit;
    }

    public Double getEquipSubhealthLimit() {
        return equipSubhealthLimit;
    }

    public void setEquipSubhealthLimit(Double equipSubhealthLimit) {
        this.equipSubhealthLimit = equipSubhealthLimit;
    }

    public String getEquipAddress() {
        return equipAddress;
    }

    public void setEquipAddress(String equipAddress) {
        this.equipAddress = equipAddress;
    }

    public Double getEquipLat() {
        return equipLat;
    }

    public void setEquipLat(Double equipLat) {
        this.equipLat = equipLat;
    }

    public Double getEquipLng() {
        return equipLng;
    }

    public void setEquipLng(Double equipLng) {
        this.equipLng = equipLng;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public Byte getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Byte deleteMark) {
        this.deleteMark = deleteMark;
    }

    public Byte getEnableMark() {
        return enableMark;
    }

    public void setEnableMark(Byte enableMark) {
        this.enableMark = enableMark;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getSubFactoryName() {
        return subFactoryName;
    }

    public void setSubFactoryName(String subFactoryName) {
        this.subFactoryName = subFactoryName;
    }
}
