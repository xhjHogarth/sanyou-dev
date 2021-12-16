package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Project {
    /**
     * 项目id
     */
    @Id
    private Integer id;

    /**
     * 项目名
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目编码
     */
    @Column(name = "project_code")
    private String projectCode;

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

    /**
     * 厂家id
     */
    @Column(name = "factory_id")
    private String factoryId;

    /**
     * 创建项目的用户id
     */
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
     * 获取项目id
     *
     * @return id - 项目id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置项目id
     *
     * @param id 项目id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取项目名
     *
     * @return project_name - 项目名
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名
     *
     * @param projectName 项目名
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取项目编码
     *
     * @return project_code - 项目编码
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * 设置项目编码
     *
     * @param projectCode 项目编码
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * 获取厂家id
     *
     * @return factory_id - 厂家id
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂家id
     *
     * @param factoryId 厂家id
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
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

    public Double getDdbLength() {
        return ddbLength;
    }

    public void setDdbLength(Double ddbLength) {
        this.ddbLength = ddbLength;
    }

    public Double getDdbWidth() {
        return ddbWidth;
    }

    public void setDdbWidth(Double ddbWidth) {
        this.ddbWidth = ddbWidth;
    }

    public Double getDdbHeight() {
        return ddbHeight;
    }

    public void setDdbHeight(Double ddbHeight) {
        this.ddbHeight = ddbHeight;
    }

    public Double getYjbLength() {
        return yjbLength;
    }

    public void setYjbLength(Double yjbLength) {
        this.yjbLength = yjbLength;
    }

    public Double getYjbWidth() {
        return yjbWidth;
    }

    public void setYjbWidth(Double yjbWidth) {
        this.yjbWidth = yjbWidth;
    }

    public Double getYjbHeight() {
        return yjbHeight;
    }

    public void setYjbHeight(Double yjbHeight) {
        this.yjbHeight = yjbHeight;
    }
}