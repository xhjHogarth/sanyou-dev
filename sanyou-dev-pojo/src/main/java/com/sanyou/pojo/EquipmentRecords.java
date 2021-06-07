package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "equipment_records")
public class EquipmentRecords {
    @Id
    private String id;

    @Column(name = "equip_no")
    private String equipNo;

    @Column(name = "user_id")
    private String userId;

    private String content;

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
     * @return equip_no
     */
    public String getEquipNo() {
        return equipNo;
    }

    /**
     * @param equipNo
     */
    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
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