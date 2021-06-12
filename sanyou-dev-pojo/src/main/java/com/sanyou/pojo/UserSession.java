package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_session")
public class UserSession {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    private String session;

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
     * @return session
     */
    public String getSession() {
        return session;
    }

    /**
     * @param session
     */
    public void setSession(String session) {
        this.session = session;
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