package com.sanyou.service;

import com.sanyou.pojo.vo.VerticalityDataVo;

/**
 * User: asus
 * Date: 2021-10-28
 * Time: 22:11
 * Version:V1.0
 */
public interface ScanCodeService {

    /**
     * 根据阴极板编码获取阴极板信息
     * @param scanCode 阴极板编码
     * @return
     */
    VerticalityDataVo getInfo(String scanCode);
}
