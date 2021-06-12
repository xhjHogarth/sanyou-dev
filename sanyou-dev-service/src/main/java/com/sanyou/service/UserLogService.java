package com.sanyou.service;

import com.sanyou.pojo.UserLog;
import com.sanyou.pojo.vo.UserLogVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/9
 * Time: 20:57
 * Version:V1.0
 */
public interface UserLogService {

    /**
     * 新增操作日志
     * @param userLog
     */
    void addUserLog(UserLog userLog);

    /**
     * 分页获取日志数据
     * @param userLogVo 查询条件
     * @param page 页数
     * @param pageSize 每页条数
     * @return
     */
    PagedResult query(UserLogVo userLogVo, Integer page, Integer pageSize);

    /**
     * 删除操作日志
     * @param list
     */
    void deleteUserLog(List<UserLog> list);

    /**
     * 获取下载数据
     * @param userLogVo
     * @return
     */
    List<UserLogVo> queryForDownload(UserLogVo userLogVo);
}
