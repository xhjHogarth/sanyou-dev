package com.sanyou.pojo.vo;

import com.sanyou.pojo.Factory;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/2
 * Time: 18:43
 * Version:V1.0
 */
public class FactoryVo {

    private String id;
    private String factoryName;
    private Byte layer;
    private String parentId;
    private Byte deleteMark;
    private Date createtime;
    private Date updatetime;
    private Date deletetime;

    private int index;
    private Boolean hasChildren;
    private List<Factory> children;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Byte getLayer() {
        return layer;
    }

    public void setLayer(Byte layer) {
        this.layer = layer;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Byte getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Byte deleteMark) {
        this.deleteMark = deleteMark;
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

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<Factory> getChildren() {
        return children;
    }

    public void setChildren(List<Factory> children) {
        this.children = children;
    }
}
