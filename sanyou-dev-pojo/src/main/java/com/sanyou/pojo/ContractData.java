package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "contract_data")
public class ContractData {
    @Id
    @Column(name = "custom_project_name")
    private String customProjectName;

    @Id
    @Column(name = "custom_contract_name")
    private String customContractName;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "contract_id")
    private String contractId;

    @Column(name = "contract_name")
    private String contractName;

    /**
     * @return custom_project_name
     */
    public String getCustomProjectName() {
        return customProjectName;
    }

    /**
     * @param customProjectName
     */
    public void setCustomProjectName(String customProjectName) {
        this.customProjectName = customProjectName;
    }

    /**
     * @return custom_contract_name
     */
    public String getCustomContractName() {
        return customContractName;
    }

    /**
     * @param customContractName
     */
    public void setCustomContractName(String customContractName) {
        this.customContractName = customContractName;
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