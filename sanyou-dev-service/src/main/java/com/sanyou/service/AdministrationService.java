package com.sanyou.service;

import com.sanyou.pojo.SAdministrativeDivisions;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/7
 * Time: 15:41
 * Version:V1.0
 */
public interface AdministrationService {

    /**
     * 查找行政区划数据
     * @param level 行政级别
     * @param pcode 所属行政区划的编码
     * @return
     */
    List<SAdministrativeDivisions> query(String level, String pcode);
}
