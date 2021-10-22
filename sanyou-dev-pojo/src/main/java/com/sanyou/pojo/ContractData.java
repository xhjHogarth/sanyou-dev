package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "contract_data")
public class ContractData {
    @Id
    @Column(name = "contract_id")
    private String contractId;

    @Id
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "contract_name")
    private String contractName;

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
     * @return contract_name
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * @param contractName
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
}