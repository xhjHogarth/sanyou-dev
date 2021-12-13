package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Product {
    /**
     * 阴极板id
     */
    @Id
    private Integer id;

    /**
     * 阴极板编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 垂直度
     */
    @Column(name = "product_value")
    private Double productValue;

    /**
     * 阴极板状态
     */
    @Column(name = "product_state")
    private Integer productState;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改状态时间
     */
    @Column(name = "update_state_date")
    private Date updateStateDate;

    /**
     * 维修类型
     */
    @Column(name = "maintain_type")
    private Integer maintainType;

    /**
     * 维修日期
     */
    @Column(name = "update_maintain_date")
    private Date updateMaintainDate;

    /**
     * 维修用户id
     */
    @Column(name = "userId")
    private String userid;

    /**
     * 获取阴极板id
     *
     * @return id - 阴极板id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置阴极板id
     *
     * @param id 阴极板id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取阴极板编码
     *
     * @return product_code - 阴极板编码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置阴极板编码
     *
     * @param productCode 阴极板编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取垂直度
     *
     * @return product_value - 垂直度
     */
    public Double getProductValue() {
        return productValue;
    }

    /**
     * 设置垂直度
     *
     * @param productValue 垂直度
     */
    public void setProductValue(Double productValue) {
        this.productValue = productValue;
    }

    /**
     * 获取阴极板状态
     *
     * @return product_state - 阴极板状态
     */
    public Integer getProductState() {
        return productState;
    }

    /**
     * 设置阴极板状态
     *
     * @param productState 阴极板状态
     */
    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取修改状态时间
     *
     * @return update_state_date - 修改状态时间
     */
    public Date getUpdateStateDate() {
        return updateStateDate;
    }

    /**
     * 设置修改状态时间
     *
     * @param updateStateDate 修改状态时间
     */
    public void setUpdateStateDate(Date updateStateDate) {
        this.updateStateDate = updateStateDate;
    }

    /**
     * 获取维修类型
     *
     * @return maintain_type - 维修类型
     */
    public Integer getMaintainType() {
        return maintainType;
    }

    /**
     * 设置维修类型
     *
     * @param maintainType 维修类型
     */
    public void setMaintainType(Integer maintainType) {
        this.maintainType = maintainType;
    }

    /**
     * 获取维修日期
     *
     * @return update_maintain_date - 维修日期
     */
    public Date getUpdateMaintainDate() {
        return updateMaintainDate;
    }

    /**
     * 设置维修日期
     *
     * @param updateMaintainDate 维修日期
     */
    public void setUpdateMaintainDate(Date updateMaintainDate) {
        this.updateMaintainDate = updateMaintainDate;
    }

    /**
     * 获取维修用户id
     *
     * @return userId - 维修用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置维修用户id
     *
     * @param userid 维修用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}