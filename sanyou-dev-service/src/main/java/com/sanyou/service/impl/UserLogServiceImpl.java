package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.UserLogMapper;
import com.sanyou.pojo.UserLog;
import com.sanyou.pojo.vo.UserLogVo;
import com.sanyou.service.UserLogService;
import com.sanyou.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/9
 * Time: 20:57
 * Version:V1.0
 */
@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addUserLog(UserLog userLog) {

        userLogMapper.insert(userLog);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(UserLogVo userLogVo, Integer page, Integer pageSize) {

        Date createtime = userLogVo.getCreatetime();
        if(createtime == null){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date time = calendar.getTime();
            userLogVo.setCreatetime(time);

            calendar.add(Calendar.DATE,1);
            Date endTime = calendar.getTime();
            userLogVo.setEndTime(endTime);
        }else{
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(createtime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            userLogVo.setCreatetime(calendar.getTime());
            calendar.add(Calendar.DATE,1);
            Date endTime = calendar.getTime();
            userLogVo.setEndTime(endTime);
        }

        PageHelper.startPage(page,pageSize);
        List<UserLogVo> list = userLogMapper.query(userLogVo);
        PageInfo<UserLogVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;

    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void deleteUserLog(List<UserLog> list) {

        for (UserLog userLog : list) {
            userLog.setDeleteMark((byte)1);
            userLog.setDeletetime(new Date());

            Example example = new Example(UserLog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",userLog.getId());
            userLogMapper.updateByExampleSelective(userLog,example);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserLogVo> queryForDownload(UserLogVo userLogVo) {

        Date createtime = userLogVo.getCreatetime();
        if(createtime == null){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date time = calendar.getTime();
            userLogVo.setCreatetime(time);

            calendar.add(Calendar.DATE,1);
            Date endTime = calendar.getTime();
            userLogVo.setEndTime(endTime);
        }else{
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(createtime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.DATE,1);
            Date endTime = calendar.getTime();
            userLogVo.setEndTime(endTime);
        }
        userLogVo.setQuery("");

        List<UserLogVo> list = userLogMapper.query(userLogVo);
        return list;
    }
}
