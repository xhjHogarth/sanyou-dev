package com.sanyou.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@ApiModel(value = "资源对象", description = "这是资源对象")
public class Resource {

    @ApiModelProperty(value = "资源ID", name = "id",example = "123")
    @Id
    private String id;

    @ApiModelProperty(value = "资源编码", name = "resCode",example = "123")
    @Column(name = "res_code")
    private String resCode;

    @ApiModelProperty(value = "资源标题", name = "title",example = "111")
    private String title;

    @ApiModelProperty(value = "资源层级", name = "layer",example = "1")
    private Byte layer;

    @ApiModelProperty(value = "父资源id", name = "parentId",example = "1")
    @Column(name = "parent_id")
    private String parentId;

    @ApiModelProperty(value = "请求的url地址", name = "url",example = "/resource")
    private String url;

    private Date createtime;

    private Date updatetime;

    private Date deletetime;

    @Column(name = "delete_mark")
    private Byte deleteMark;

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
     * @return res_name
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * @param resCode
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return layer
     */
    public Byte getLayer() {
        return layer;
    }

    /**
     * @param layer
     */
    public void setLayer(Byte layer) {
        this.layer = layer;
    }

    /**
     * @return parent_id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}