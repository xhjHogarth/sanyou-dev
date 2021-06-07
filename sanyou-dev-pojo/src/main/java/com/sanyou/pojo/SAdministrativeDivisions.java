package com.sanyou.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "s_administrative_divisions")
public class SAdministrativeDivisions {
    /**
     * 行政区划代码
     */
    @Id
    private Integer code;

    /**
     * 名字
     */
    private String name;

    /**
     * country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县
     */
    private String level;

    /**
     * 所属行政区划代码
     */
    private Integer pcode;

    /**
     * 所属行政区划名字
     */
    private String pname;

    /**
     * 行政区划完整名字
     */
    private String fullname;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 获取行政区划代码
     *
     * @return code - 行政区划代码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置行政区划代码
     *
     * @param code 行政区划代码
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县
     *
     * @return level - country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县
     *
     * @param level country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取所属行政区划代码
     *
     * @return pcode - 所属行政区划代码
     */
    public Integer getPcode() {
        return pcode;
    }

    /**
     * 设置所属行政区划代码
     *
     * @param pcode 所属行政区划代码
     */
    public void setPcode(Integer pcode) {
        this.pcode = pcode;
    }

    /**
     * 获取所属行政区划名字
     *
     * @return pname - 所属行政区划名字
     */
    public String getPname() {
        return pname;
    }

    /**
     * 设置所属行政区划名字
     *
     * @param pname 所属行政区划名字
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 获取行政区划完整名字
     *
     * @return fullname - 行政区划完整名字
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 设置行政区划完整名字
     *
     * @param fullname 行政区划完整名字
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}