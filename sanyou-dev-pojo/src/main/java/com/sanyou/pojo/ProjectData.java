package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "project_data")
public class ProjectData {
    @Id
    @Column(name = "custom_project_name")
    private String customProjectName;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "factory_id")
    private String factoryId;

    @Column(name = "dir_path")
    private String dirPath;

    @Column(name = "fileName")
    private String filename;

    private String extension;

    @Column(name = "ddb_length")
    private Integer ddbLength;

    @Column(name = "ddb_width")
    private Integer ddbWidth;

    @Column(name = "ddb_height")
    private Integer ddbHeight;

    @Column(name = "yjb_length")
    private Integer yjbLength;

    @Column(name = "yjb_width")
    private Integer yjbWidth;

    @Column(name = "yjb_height")
    private Integer yjbHeight;

    @Column(name = "MD5")
    private String md5;

    @Column(name = "createTime")
    private Date createtime;

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
     * @return factory_id
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * @param factoryId
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * @return dir_path
     */
    public String getDirPath() {
        return dirPath;
    }

    /**
     * @param dirPath
     */
    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    /**
     * @return fileName
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return ddb_length
     */
    public Integer getDdbLength() {
        return ddbLength;
    }

    /**
     * @param ddbLength
     */
    public void setDdbLength(Integer ddbLength) {
        this.ddbLength = ddbLength;
    }

    /**
     * @return ddb_width
     */
    public Integer getDdbWidth() {
        return ddbWidth;
    }

    /**
     * @param ddbWidth
     */
    public void setDdbWidth(Integer ddbWidth) {
        this.ddbWidth = ddbWidth;
    }

    /**
     * @return ddb_height
     */
    public Integer getDdbHeight() {
        return ddbHeight;
    }

    /**
     * @param ddbHeight
     */
    public void setDdbHeight(Integer ddbHeight) {
        this.ddbHeight = ddbHeight;
    }

    /**
     * @return yjb_length
     */
    public Integer getYjbLength() {
        return yjbLength;
    }

    /**
     * @param yjbLength
     */
    public void setYjbLength(Integer yjbLength) {
        this.yjbLength = yjbLength;
    }

    /**
     * @return yjb_width
     */
    public Integer getYjbWidth() {
        return yjbWidth;
    }

    /**
     * @param yjbWidth
     */
    public void setYjbWidth(Integer yjbWidth) {
        this.yjbWidth = yjbWidth;
    }

    /**
     * @return yjb_height
     */
    public Integer getYjbHeight() {
        return yjbHeight;
    }

    /**
     * @param yjbHeight
     */
    public void setYjbHeight(Integer yjbHeight) {
        this.yjbHeight = yjbHeight;
    }

    /**
     * @return MD5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
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
}