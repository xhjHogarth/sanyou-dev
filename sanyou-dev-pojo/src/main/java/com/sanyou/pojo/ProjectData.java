package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "project_data")
public class ProjectData {
    @Id
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "factory_id")
    private String factoryId;

    @Column(name = "dir_path")
    private String dirPath;

    @Column(name = "fileName")
    private String filename;

    private String extension;

    private Integer length;

    private Integer width;

    private Integer height;

    @Column(name = "MD5")
    private String md5;

    @Column(name = "createTime")
    private Date createtime;

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
     * @return length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height
     */
    public void setHeight(Integer height) {
        this.height = height;
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