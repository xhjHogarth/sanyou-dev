package com.sanyou.controller.app;

import com.sanyou.pojo.Question;
import com.sanyou.pojo.QuestionImage;
import com.sanyou.pojo.vo.QuestionVo;
import com.sanyou.service.QuestionImageService;
import com.sanyou.service.QuestionService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

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

    @Autowired
    private QuestionImageService questionImageService;

    @PostMapping("/addQuestion")
    public JSONResult addQuestion(HttpServletRequest request){

        MultipartFile image = ((MultipartHttpServletRequest) request).getFile("images");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String userId = request.getParameter("userId");
        if(StringUtils.isBlank(title))
            return JSONResult.errorMsg("标题为空!");
        if(StringUtils.isBlank(description))
            return JSONResult.errorMsg("内容为空!");
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id为空!");
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setCreatetime(new Date());
        question.setUserid(userId);
        question.setIsHandled(0);

        int questionId = questionService.addQuestion(question);

        if(image != null){
            try {
                //原始文件名
                String originalFilename = image.getOriginalFilename();
                //存储的文件名
                String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                //扩展名
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                //存储的相对路径
                String path = "/upload/";
                //绝对路径
                //String url = request.getSession().getServletContext().getRealPath("") + path;
                String url = "/home/data/www/web/sanyou" + path;
                File dir = new File(url);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                image.transferTo(new File(url + filename + extension));

                QuestionImage questionImage = new QuestionImage();
                questionImage.setQuestionId(questionId);
                questionImage.setExtension(extension);
                questionImage.setFilename(filename + extension);
                questionImage.setPath(url);
                questionImage.setRealname(originalFilename);
                questionImage.setUploadtime(new Date());

                questionImageService.add(questionImage);
            } catch (IOException e) {
                return JSONResult.errorMsg("文件上传失败");
            }
        }

        return JSONResult.ok();
    }

    @GetMapping("/query")
    public JSONResult query(Integer page, Integer pageSize){
        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        //TODO 查询消息数据是否要根据用户做筛选,后期再考虑?

        PagedResult pagedResult = questionService.query(page,pageSize);

        return JSONResult.ok(pagedResult);

    }

    @PostMapping("/handleMessage")
    public JSONResult handleMessage(@RequestBody Question question){
        if(question == null || question.getId() == null)
            return JSONResult.errorMsg("问题Id为空!");
        if(StringUtils.isBlank(question.getHandleUserId()))
            return JSONResult.errorMsg("用户Id为空!");

        questionService.handleMessage(question);

        return JSONResult.ok();
    }


    @GetMapping("/getQuestionList")
    public JSONResult getQuestionList(String userId){
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id为空!");

        List<QuestionVo> questionList = questionService.getQuestionList(userId);
        return JSONResult.ok(questionList);
    }
}
