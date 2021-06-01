package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.ResourceMapper;
import com.sanyou.pojo.Resource;
import com.sanyou.pojo.vo.ResourceVo;
import com.sanyou.service.ResourceService;
import com.sanyou.utils.PagedResult;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 14:29
 * Version:V1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addResource(Resource resource) {
        String id = sid.nextShort();
        if(StringUtils.isNotBlank(resource.getParentId())){
            Example example = new Example(Resource.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",resource.getParentId());
            Resource resource1 = resourceMapper.selectOneByExample(example);
            resource.setLayer((byte)(resource1.getLayer()+1));
        }else {
            resource.setLayer((byte)0);
        }
        resource.setId(id);
        resource.setCreatetime(new Date());
        resource.setUpdatetime(new Date());
        resource.setDeleteMark((byte)0);

        resourceMapper.insert(resource);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Resource findByName(String resCode) {
        Example example = new Example(Resource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("resCode",resCode);
        criteria.andEqualTo("deleteMark",0);

        return resourceMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(String query, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<ResourceVo> list = resourceMapper.queryResources(query);
        PageInfo<ResourceVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateResource(List<Resource> list) {
        for (Resource resource : list) {
            Example example = new Example(Resource.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",resource.getId());

            if(resource.getDeleteMark() == 1)
                resource.setDeletetime(new Date());
            resource.setUpdatetime(new Date());
            resourceMapper.updateByExampleSelective(resource,example);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ResourceVo> getAll() {

        List<ResourceVo> list = resourceMapper.queryResources("");
        List<ResourceVo> result = list.stream().filter(t -> t.getLayer() == 1).collect(Collectors.toList());
        for (ResourceVo resourceVo : result) {
            List<ResourceVo> collect = list.stream().filter(t -> t.getLayer() == 2
                    && t.getParentId().equals(resourceVo.getId())).collect(Collectors.toList());

            if(collect != null && collect.size() > 0){
                resourceVo.setChildren(collect);
            }
        }

        return result;
    }
}
