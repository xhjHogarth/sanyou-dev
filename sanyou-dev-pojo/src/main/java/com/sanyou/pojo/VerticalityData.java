package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "verticality_data")
public class VerticalityData {
    @Id
    @Column(name = "verticality_id")
    private String verticalityId;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "contract_id")
    private String contractId;

    @Column(name = "createTime")
    private Date createtime;

    private Float verticality;

    private Integer state;

    @Column(name = "update_state_date")
    private Date updateStateDate;

    @Column(name = "maintain_type")
    private Integer maintainType;

    @Column(name = "update_maintain_date")
    private Date updateMaintainDate;

    @Column(name = "userId")
    private String userid;

    /**
     * @return verticality_id
     */
    public String getVerticalityId() {
        return verticalityId;
    }

    /**
     * @param verticalityId
     */
    public void setVerticalityId(String verticalityId) {
        this.verticalityId = verticalityId;
    }

    /**
     * @return project_id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return contract_id
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * @param contractId
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * @return createTime
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
     * @return verticality
     */
    public Float getVerticality() {
        return verticality;
    }

    /**
     * @param verticality
     */
    public void setVerticality(Float verticality) {
        this.verticality = verticality;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return update_state_date
     */
    public Date getUpdateStateDate() {
        return updateStateDate;
    }

    /**
     * @param updateStateDate
     */
    public void setUpdateStateDate(Date updateStateDate) {
        this.updateStateDate = updateStateDate;
    }

    /**
     * @return maintain_type
     */
    public Integer getMaintainType() {
        return maintainType;
    }

    /**
     * @param maintainType
     */
    public void setMaintainType(Integer maintainType) {
        this.maintainType = maintainType;
    }

    /**
     * @return update_maintain_date
     */
    public Date getUpdateMaintainDate() {
        return updateMaintainDate;
    }

    /**
     * @param updateMaintainDate
     */
    public void setUpdateMaintainDate(Date updateMaintainDate) {
        this.updateMaintainDate = updateMaintainDate;
    }

    /**
     * @return userId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}