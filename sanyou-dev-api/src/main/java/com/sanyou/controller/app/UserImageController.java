package com.sanyou.controller.app;

import com.sanyou.pojo.UserImage;
import com.sanyou.service.UserImageService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: asus
 * Date: 2021-11-19
 * Time: 13:38
 * Version:V1.0
 */
@Api(value = "用户头像相关的接口",tags = {"用户头像相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/userImage")
public class UserImageController {

    @Autowired
    private UserImageService userImageService;

    @PostMapping("/upload")
    public JSONResult upload(HttpServletRequest request){
        MultipartFile image = ((MultipartHttpServletRequest) request).getFile("image");

        String userId = request.getParameter("userId");
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id为空!");

        if(image == null){
            return JSONResult.errorMsg("图片为空!");
        }else{
            UserImage userImage = new UserImage();
            userImage.setUserid(userId);

            try{
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

                userImage.setFilename(filename + extension);
                userImage.setRealname(originalFilename);
                userImage.setExtension(extension);
                userImage.setPath(url);
                userImage.setUploadtime(new Date());

                userImageService.add(userImage);

                return JSONResult.ok(userImage);
            }catch (Exception e){
                return JSONResult.errorMsg("头像上传失败!");
            }
        }
    }

    @GetMapping("/getUserImage")
    public JSONResult getUserImage(String userId){
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id为空!");

        UserImage userImage = userImageService.get(userId);
        if(userImage == null)
            userImage = new UserImage();
        return JSONResult.ok(userImage);
    }
}
