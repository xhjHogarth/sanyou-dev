package com.sanyou.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@ApiModel(value = "厂家对象", description = "这是厂家对象")
public class Factory {

    @ApiModelProperty(value = "厂家ID", name = "id",example = "")
    @Id
    private String id;

    @ApiModelProperty(value = "厂家名", name = "factoryName",example = "123")
    @Column(name = "factory_name")
    private String factoryName;

    @ApiModelProperty(value = "删除标志", name = "deleteMark",example = "0")
    @Column(name = "delete_mark")
    private Byte deleteMark;

    @ApiModelProperty(value = "创建时间", name = "createtime",example = "",required = false)
    private Date createtime;

    @ApiModelProperty(value = "修改时间", name = "updatetime",example = "",required = false)
    private Date updatetime;

    @ApiModelProperty(value = "删除时间", name = "deletetime",example = "",required = false)
    private Date deletetime;

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
     * @return factory_name
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * @param factoryName
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
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
}