package com.sanyou.service.impl;

import com.sanyou.mapper.QuestionImageMapper;
import com.sanyou.pojo.QuestionImage;
import com.sanyou.service.QuestionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: asus
 * Date: 2021-11-18
 * Time: 14:06
 * Version:V1.0
 */
@Service
public class QuestionImageServiceImpl implements QuestionImageService {


    @Autowired
    private QuestionImageMapper questionImageMapper;

    @Override
    public void add(QuestionImage questionImage) {
        questionImageMapper.insert(questionImage);
    }
}
