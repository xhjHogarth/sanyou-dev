package com.sanyou.controller.app;

import com.sanyou.pojo.Question;
import com.sanyou.service.QuestionService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * User: asus
 * Date: 2021-10-29
 * Time: 16:35
 * Version:V1.0
 */
@Api(value = "问题反馈相关的接口",tags = {"问题反馈相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public JSONResult addQuestion(@RequestBody Question question){
        if(question == null || StringUtils.isBlank(question.getTitle()) || StringUtils.isBlank(question.getDescription()))
            return JSONResult.errorMsg("问题标题或描述不能为空!");

        question.setCreatetime(new Date());
        questionService.addQuestion(question);

        return JSONResult.ok();
    }
}
