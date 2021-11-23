package com.sanyou.service;

import com.sanyou.pojo.Question;
import com.sanyou.pojo.vo.QuestionVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021-10-29
 * Time: 16:39
 * Version:V1.0
 */
public interface QuestionService {

    int addQuestion(Question question);

    PagedResult query(Integer page, Integer pageSize);

    void handleMessage(Question question);

    List<QuestionVo> getQuestionList(String userId);
}
