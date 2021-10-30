package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "search_history")
public class SearchHistory {
    @Id
    private Integer id;

    @Column(name = "search_code")
    private String searchCode;

    @Column(name = "search_date")
    private Date searchDate;

    @Column(name = "user_id")
    private String userId;

    private Integer verticality;

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
     * @return search_code
     */
    public String getSearchCode() {
        return searchCode;
    }

    /**
     * @param searchCode
     */
    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    /**
     * @return search_date
     */
    public Date getSearchDate() {
        return searchDate;
    }

    /**
     * @param searchDate
     */
    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
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

    public Integer getVerticality() {
        return verticality;
    }

    public void setVerticality(Integer verticality) {
        this.verticality = verticality;
    }
}