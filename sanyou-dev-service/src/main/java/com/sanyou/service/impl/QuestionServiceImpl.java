package com.sanyou.service.impl;

import com.sanyou.mapper.QuestionMapper;
import com.sanyou.pojo.Question;
import com.sanyou.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: asus
 * Date: 2021-10-29
 * Time: 16:39
 * Version:V1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void addQuestion(Question question) {
        questionMapper.insertSelective(question);
    }
}
