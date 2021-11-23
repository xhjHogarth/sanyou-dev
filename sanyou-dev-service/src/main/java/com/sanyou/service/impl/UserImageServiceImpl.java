package com.sanyou.service.impl;

import com.sanyou.mapper.UserImageMapper;
import com.sanyou.pojo.UserImage;
import com.sanyou.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * User: asus
 * Date: 2021-11-19
 * Time: 13:39
 * Version:V1.0
 */
@Service
public class UserImageServiceImpl implements UserImageService {

    @Autowired
    private UserImageMapper userImageMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(UserImage userImage) {
        Example example = new Example(UserImage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userImage.getUserid());
        UserImage existUserImage = userImageMapper.selectOneByExample(example);

        if(existUserImage != null){
            existUserImage.setFilename(userImage.getFilename());
            existUserImage.setRealname(userImage.getRealname());
            existUserImage.setExtension(userImage.getExtension());
            existUserImage.setPath(userImage.getPath());
            existUserImage.setUploadtime(new Date());
            userImageMapper.updateByPrimaryKeySelective(existUserImage);
        }else{
            userImageMapper.insert(userImage);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserImage get(String userId) {
        Example example = new Example(UserImage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userId);
        UserImage userImage = userImageMapper.selectOneByExample(example);

        return userImage;
    }
}
