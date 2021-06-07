package com.sanyou.service.impl;

import com.sanyou.mapper.SAdministrativeDivisionsMapper;
import com.sanyou.pojo.SAdministrativeDivisions;
import com.sanyou.service.AdministrationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/7
 * Time: 15:41
 * Version:V1.0
 */
@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private SAdministrativeDivisionsMapper divisionsMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<SAdministrativeDivisions> query(String level, String pcode) {

        Example example = new Example(SAdministrativeDivisions.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("level",level);

        if(StringUtils.isNotBlank(pcode))
            criteria.andEqualTo("pcode",pcode);

        List<SAdministrativeDivisions> list = divisionsMapper.selectByExample(example);

        return list;
    }
}
