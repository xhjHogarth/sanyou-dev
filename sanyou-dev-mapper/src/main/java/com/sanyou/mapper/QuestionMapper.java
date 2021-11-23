package com.sanyou.mapper;

import com.sanyou.pojo.Question;
import com.sanyou.pojo.vo.QuestionVo;
import com.sanyou.utils.MyMapper;

import java.util.List;

public interface QuestionMapper extends MyMapper<Question> {

    void insertOne(Question question);

    List<QuestionVo> query();

    List<QuestionVo> getQuestionList(String userId);
}