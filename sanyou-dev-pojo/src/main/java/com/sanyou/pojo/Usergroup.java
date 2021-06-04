package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Usergroup {
    @Id
    private String id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_type")
    private Byte groupType;

    @Column(name = "group_level")
    private Byte groupLevel;

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
     * @return group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return group_type
     */
    public Byte getGroupType() {
        return groupType;
    }

    /**
     * @param groupType
     */
    public void setGroupType(Byte groupType) {
        this.groupType = groupType;
    }

    /**
     * @return group_level
     */
    public Byte getGroupLevel() {
        return groupLevel;
    }

    /**
     * @param groupLevel
     */
    public void setGroupLevel(Byte groupLevel) {
        this.groupLevel = groupLevel;
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