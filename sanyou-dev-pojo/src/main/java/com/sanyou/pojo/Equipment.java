package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Equipment {
    @Id
    private String id;

    /**
     * 设备编号
     */
    @Column(name = "equip_no")
    private String equipNo;

    /**
     * 厂家
     */
    @Column(name = "factory_id")
    private String factoryId;

    /**
     * 生产线
     */
    @Column(name = "sub_factory_id")
    private String subFactoryId;

    /**
     * 设备状态
     */
    @Column(name = "equip_status")
    private Byte equipStatus;

    /**
     * 设备在线时长
     */
    @Column(name = "equip_online_time")
    private Date equipOnlineTime;

    /**
     * 设备周期
     */
    @Column(name = "equip_cycle")
    private Double equipCycle;

    /**
     * 设备健康上限
     */
    @Column(name = "equip_health_limit")
    private Double equipHealthLimit;

    /**
     * 设备亚健康上限
     */
    @Column(name = "equip_subhealth_limit")
    private Double equipSubhealthLimit;

    /**
     * 设备地址
     */
    @Column(name = "equip_address")
    private String equipAddress;

    /**
     * 经度
     */
    @Column(name = "equip_lat")
    private Double equipLat;

    /**
     * 纬度
     */
    @Column(name = "equip_lng")
    private Double equipLng;

    private Date createtime;

    private Date updatetime;

    private Date deletetime;

    @Column(name = "delete_mark")
    private Byte deleteMark;

    @Column(name = "enable_mark")
    private Byte enableMark;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取设备编号
     *
     * @return equip_no - 设备编号
     */
    public String getEquipNo() {
        return equipNo;
    }

    /**
     * 设置设备编号
     *
     * @param equipNo 设备编号
     */
    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    /**
     * 获取厂家
     *
     * @return factory_id - 厂家
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂家
     *
     * @param factoryId 厂家
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取生产线
     *
     * @return sub_factory_id - 生产线
     */
    public String getSubFactoryId() {
        return subFactoryId;
    }

    /**
     * 设置生产线
     *
     * @param subFactoryId 生产线
     */
    public void setSubFactoryId(String subFactoryId) {
        this.subFactoryId = subFactoryId;
    }

    /**
     * 获取设备状态
     *
     * @return equip_status - 设备状态
     */
    public Byte getEquipStatus() {
        return equipStatus;
    }

    /**
     * 设置设备状态
     *
     * @param equipStatus 设备状态
     */
    public void setEquipStatus(Byte equipStatus) {
        this.equipStatus = equipStatus;
    }

    /**
     * 获取设备在线时长
     *
     * @return equip_online_time - 设备在线时长
     */
    public Date getEquipOnlineTime() {
        return equipOnlineTime;
    }

    /**
     * 设置设备在线时长
     *
     * @param equipOnlineTime 设备在线时长
     */
    public void setEquipOnlineTime(Date equipOnlineTime) {
        this.equipOnlineTime = equipOnlineTime;
    }

    /**
     * 获取设备周期
     *
     * @return equip_cycle - 设备周期
     */
    public Double getEquipCycle() {
        return equipCycle;
    }

    /**
     * 设置设备周期
     *
     * @param equipCycle 设备周期
     */
    public void setEquipCycle(Double equipCycle) {
        this.equipCycle = equipCycle;
    }

    /**
     * 获取设备健康上限
     *
     * @return equip_health_limit - 设备健康上限
     */
    public Double getEquipHealthLimit() {
        return equipHealthLimit;
    }

    /**
     * 设置设备健康上限
     *
     * @param equipHealthLimit 设备健康上限
     */
    public void setEquipHealthLimit(Double equipHealthLimit) {
        this.equipHealthLimit = equipHealthLimit;
    }

    /**
     * 获取设备亚健康上限
     *
     * @return equip_subhealth_limit - 设备亚健康上限
     */
    public Double getEquipSubhealthLimit() {
        return equipSubhealthLimit;
    }

    /**
     * 设置设备亚健康上限
     *
     * @param equipSubhealthLimit 设备亚健康上限
     */
    public void setEquipSubhealthLimit(Double equipSubhealthLimit) {
        this.equipSubhealthLimit = equipSubhealthLimit;
    }

    /**
     * 获取设备地址
     *
     * @return equip_address - 设备地址
     */
    public String getEquipAddress() {
        return equipAddress;
    }

    /**
     * 设置设备地址
     *
     * @param equipAddress 设备地址
     */
    public void setEquipAddress(String equipAddress) {
        this.equipAddress = equipAddress;
    }

    /**
     * 获取经度
     *
     * @return equip_lat - 经度
     */
    public Double getEquipLat() {
        return equipLat;
    }

    /**
     * 设置经度
     *
     * @param equipLat 经度
     */
    public void setEquipLat(Double equipLat) {
        this.equipLat = equipLat;
    }

    /**
     * 获取纬度
     *
     * @return equip_lng - 纬度
     */
    public Double getEquipLng() {
        return equipLng;
    }

    /**
     * 设置纬度
     *
     * @param equipLng 纬度
     */
    public void setEquipLng(Double equipLng) {
        this.equipLng = equipLng;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return deletetime
     */
    public Date getDeletetime() {
        return deletetime;
    }

    /**
     * @param deletetime
     */
    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    /**
     * @return delete_mark
     */
    public Byte getDeleteMark() {
        return deleteMark;
    }

    /**
     * @param deleteMark
     */
    public void setDeleteMark(Byte deleteMark) {
        this.deleteMark = deleteMark;
    }

    /**
     * @return enable_mark
     */
    public Byte getEnableMark() {
        return enableMark;
    }

    /**
     * @param enableMark
     */
    public void setEnableMark(Byte enableMark) {
        this.enableMark = enableMark;
    }
}