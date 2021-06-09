package com.sanyou.pojo.vo;

import java.util.Date;
import java.util.List;

public class ResourceVo {

    private String id;

    private String resCode;

    private String title;

    private Byte layer;

    private String parentId;

    private String url;

    private String parentCode;

    private Date createtime;

    private Date updatetime;

    private Date deletetime;

    private Byte deleteMark;

    private List<ResourceVo> children;

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<ResourceVo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceVo> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}