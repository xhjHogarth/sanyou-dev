package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Order {
    /**
     * 订单id
     */
    @Id
    private Integer id;

    /**
     * 订单名
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 订单编码
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 导电棒长
     */
    @Column(name = "ddb_length")
    private Double ddbLength;

    /**
     * 导电棒宽
     */
    @Column(name = "ddb_width")
    private Double ddbWidth;

    /**
     * 导电棒高
     */
    @Column(name = "ddb_height")
    private Double ddbHeight;

    /**
     * 阴极板长
     */
    @Column(name = "yjb_length")
    private Double yjbLength;

    /**
     * 阴极板宽
     */
    @Column(name = "yjb_width")
    private Double yjbWidth;

    /**
     * 阴极板高
     */
    @Column(name = "yjb_height")
    private Double yjbHeight;

    @Column(name = "user_id")
    private String userId;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 删除标志
     */
    @Column(name = "delete_mark")
    private Byte deleteMark;

    /**
     * 获取订单id
     *
     * @return id - 订单id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单id
     *
     * @param id 订单id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单名
     *
     * @return order_name - 订单名
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * 设置订单名
     *
     * @param orderName 订单名
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * 获取订单编码
     *
     * @return order_code - 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编码
     *
     * @param orderCode 订单编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取导电棒长
     *
     * @return ddb_length - 导电棒长
     */
    public Double getDdbLength() {
        return ddbLength;
    }

    /**
     * 设置导电棒长
     *
     * @param ddbLength 导电棒长
     */
    public void setDdbLength(Double ddbLength) {
        this.ddbLength = ddbLength;
    }

    /**
     * 获取导电棒宽
     *
     * @return ddb_width - 导电棒宽
     */
    public Double getDdbWidth() {
        return ddbWidth;
    }

    /**
     * 设置导电棒宽
     *
     * @param ddbWidth 导电棒宽
     */
    public void setDdbWidth(Double ddbWidth) {
        this.ddbWidth = ddbWidth;
    }

    /**
     * 获取导电棒高
     *
     * @return ddb_height - 导电棒高
     */
    public Double getDdbHeight() {
        return ddbHeight;
    }

    /**
     * 设置导电棒高
     *
     * @param ddbHeight 导电棒高
     */
    public void setDdbHeight(Double ddbHeight) {
        this.ddbHeight = ddbHeight;
    }

    /**
     * 获取阴极板长
     *
     * @return yjb_length - 阴极板长
     */
    public Double getYjbLength() {
        return yjbLength;
    }

    /**
     * 设置阴极板长
     *
     * @param yjbLength 阴极板长
     */
    public void setYjbLength(Double yjbLength) {
        this.yjbLength = yjbLength;
    }

    /**
     * 获取阴极板宽
     *
     * @return yjb_width - 阴极板宽
     */
    public Double getYjbWidth() {
        return yjbWidth;
    }

    /**
     * 设置阴极板宽
     *
     * @param yjbWidth 阴极板宽
     */
    public void setYjbWidth(Double yjbWidth) {
        this.yjbWidth = yjbWidth;
    }

    /**
     * 获取阴极板高
     *
     * @return yjb_height - 阴极板高
     */
    public Double getYjbHeight() {
        return yjbHeight;
    }

    /**
     * 设置阴极板高
     *
     * @param yjbHeight 阴极板高
     */
    public void setYjbHeight(Double yjbHeight) {
        this.yjbHeight = yjbHeight;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
     * 获取修改时间
     *
     * @return updatetime - 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取删除标志
     *
     * @return delete_mark - 删除标志
     */
    public Byte getDeleteMark() {
        return deleteMark;
    }

    /**
     * 设置删除标志
     *
     * @param deleteMark 删除标志
     */
    public void setDeleteMark(Byte deleteMark) {
        this.deleteMark = deleteMark;
    }
}