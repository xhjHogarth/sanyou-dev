package com.sanyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class User {
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真名
     */
    private String realname;

    /**
     * 性别(保密：1，男：2，女：3)
     */
    private Byte sex;

    /**
     * 手机
     */
    private String mobile;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 详细住址
     */
    private String address;

    /**
     * 启用禁用标志
     */
    @Column(name = "enable_mark")
    private Byte enableMark;

    /**
     * 删除标志
     */
    @Column(name = "delete_mark")
    private Byte deleteMark;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 删除时间
     */
    private Date deletetime;

    /**
     * 注册ip
     */
    @Column(name = "regist_ip")
    private String registIp;

    /**
     * 最后登录ip
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 厂家id
     */
    @Column(name = "factory_id")
    private String factoryId;

    /**
     * 子厂家id
     */
    @Column(name = "sub_factory_id")
    private String subFactoryId;

    /**
     * 部门
     */
    private String depart;

    /**
     * 职位
     */
    private String position;


    /**
     * 用户组id
     */
    @Column(name = "group_id")
    private String groupId;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取真名
     *
     * @return realname - 真名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置真名
     *
     * @param realname 真名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取性别(保密：1，男：2，女：3)
     *
     * @return sex - 性别(保密：1，男：2，女：3)
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别(保密：1，男：2，女：3)
     *
     * @param sex 性别(保密：1，男：2，女：3)
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取手机
     *
     * @return mobile - 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取qq
     *
     * @return qq - qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置qq
     *
     * @param qq qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取微信
     *
     * @return wechat - 微信
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信
     *
     * @param wechat 微信
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取电子邮件
     *
     * @return email - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return area - 区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区
     *
     * @param area 区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取详细住址
     *
     * @return address - 详细住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细住址
     *
     * @param address 详细住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取启用禁用标志
     *
     * @return enable_mark - 启用禁用标志
     */
    public Byte getEnableMark() {
        return enableMark;
    }

    /**
     * 设置启用禁用标志
     *
     * @param enableMark 启用禁用标志
     */
    public void setEnableMark(Byte enableMark) {
        this.enableMark = enableMark;
    }

    /**
     * 获取删除标志
     *
     * @return delete_mark - 删除标志
     */
    public Byte getDeleteMark() {
        return deleteMark;
    }

    /**
     * 设置删除标志
     *
     * @param deleteMark 删除标志
     */
    public void setDeleteMark(Byte deleteMark) {
        this.deleteMark = deleteMark;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取修改时间
     *
     * @return updatetime - 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取删除时间
     *
     * @return deletetime - 删除时间
     */
    public Date getDeletetime() {
        return deletetime;
    }

    /**
     * 设置删除时间
     *
     * @param deletetime 删除时间
     */
    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    /**
     * 获取注册ip
     *
     * @return regist_ip - 注册ip
     */
    public String getRegistIp() {
        return registIp;
    }

    /**
     * 设置注册ip
     *
     * @param registIp 注册ip
     */
    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    /**
     * 获取最后登录ip
     *
     * @return last_login_ip - 最后登录ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后登录ip
     *
     * @param lastLoginIp 最后登录ip
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取厂家id
     *
     * @return factory_id - 厂家id
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂家id
     *
     * @param factoryId 厂家id
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取子厂家id
     *
     * @return sub_factory_id - 子厂家id
     */
    public String getSubFactoryId() {
        return subFactoryId;
    }

    /**
     * 设置子厂家id
     *
     * @param subFactoryId 子厂家id
     */
    public void setSubFactoryId(String subFactoryId) {
        this.subFactoryId = subFactoryId;
    }

    /**
     * 获取部门
     *
     * @return depart - 部门
     */
    public String getDepart() {
        return depart;
    }

    /**
     * 设置部门
     *
     * @param depart 部门
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * 获取职位
     *
     * @return position - 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position;
    }
}