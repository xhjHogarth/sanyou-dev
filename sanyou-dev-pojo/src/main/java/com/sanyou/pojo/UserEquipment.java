package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_equipment")
public class UserEquipment {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "equip_id")
    private String equipId;

    private Date createtime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return equip_id
     */
    public String getEquipId() {
        return equipId;
    }

    /**
     * @param equipId
     */
    public void setEquipId(String equipId) {
        this.equipId = equipId;
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