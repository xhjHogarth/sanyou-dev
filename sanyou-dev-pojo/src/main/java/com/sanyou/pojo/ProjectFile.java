package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "project_file")
public class ProjectFile {
    /**
     * 项目文件id
     */
    @Id
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 原文件名
     */
    private String realname;

    /**
     * 文件格式
     */
    private String extension;

    /**
     * 上传时间
     */
    private Date uploadtime;

    /**
     * 获取项目文件id
     *
     * @return id - 项目文件id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置项目文件id
     *
     * @param id 项目文件id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取项目id
     *
     * @return project_id - 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id
     *
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取存储路径
     *
     * @return path - 存储路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置存储路径
     *
     * @param path 存储路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取文件名
     *
     * @return filename - 文件名
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置文件名
     *
     * @param filename 文件名
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 获取原文件名
     *
     * @return realname - 原文件名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置原文件名
     *
     * @param realname 原文件名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取文件格式
     *
     * @return extension - 文件格式
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 设置文件格式
     *
     * @param extension 文件格式
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * 获取上传时间
     *
     * @return uploadtime - 上传时间
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * 设置上传时间
     *
     * @param uploadtime 上传时间
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}