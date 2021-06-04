package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "usergroup_role")
public class UsergroupRole {
    @Id
    private String id;

    @Column(name = "usergroupId")
    private String usergroupid;

    @Column(name = "roleId")
    private String roleid;

    private Date createtime;

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
     * @return usergroupId
     */
    public String getUsergroupid() {
        return usergroupid;
    }

    /**
     * @param usergroupid
     */
    public void setUsergroupid(String usergroupid) {
        this.usergroupid = usergroupid;
    }

    /**
     * @return roleId
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
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
}