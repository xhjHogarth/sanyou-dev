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

    private Integer verticality;

    private Integer state;

    @Column(name = "maintain_type")
    private Integer maintainType;

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
    public Integer getVerticality() {
        return verticality;
    }

    /**
     * @param verticality
     */
    public void setVerticality(Integer verticality) {
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

    public Integer getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(Integer maintainType) {
        this.maintainType = maintainType;
    }
}