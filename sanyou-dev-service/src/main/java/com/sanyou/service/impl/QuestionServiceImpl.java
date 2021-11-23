package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.QuestionImageMapper;
import com.sanyou.mapper.QuestionMapper;
import com.sanyou.mapper.UserMapper;
import com.sanyou.pojo.Question;
import com.sanyou.pojo.QuestionImage;
import com.sanyou.pojo.User;
import com.sanyou.pojo.vo.QuestionVo;
import com.sanyou.service.QuestionService;
import com.sanyou.utils.PagedResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionImageMapper questionImageMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addQuestion(Question question) {
        questionMapper.insertOne(question);
        return question.getId();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<QuestionVo> questionList = questionMapper.query();

        int index = (page-1) * pageSize + 1;
        for (QuestionVo questionVo : questionList) {
            questionVo.setIndex(index++);
            if(questionVo.getIsHandled() !=null && questionVo.getIsHandled() == 1){
                String userId = questionVo.getHandleUserId();
                if(!StringUtils.isBlank(userId)){
                    User user = userMapper.selectByPrimaryKey(userId);
                    if(user != null)
                        questionVo.setHandleUserName(user.getUsername());
                }
            }else{
                questionVo.setHandleUserName("");
                questionVo.setHandleTime(null);
            }
            Example example = new Example(QuestionImage.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("questionId",questionVo.getId());
            List<QuestionImage> questionImages = questionImageMapper.selectByExample(example);
            if(questionImages != null && questionImages.size() > 0){
                QuestionImage questionImage = questionImages.get(0);
                String url = questionImage.getFilename();
                questionVo.setUrl(url);
            }
        }

        PageInfo<QuestionVo> pageList = new PageInfo<>(questionList);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(questionList);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void handleMessage(Question question) {
        Question updateQuestion = new Question();
        updateQuestion.setId(question.getId());
        updateQuestion.setIsHandled(1);
        updateQuestion.setHandleUserId(question.getHandleUserId());
        updateQuestion.setHandleTime(new Date());

        Example example = new Example(Question.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",question.getId());

        questionMapper.updateByExampleSelective(updateQuestion,example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<QuestionVo> getQuestionList(String userId) {
        List<QuestionVo> questionList = questionMapper.getQuestionList(userId);
        return questionList;
    }
}
