package com.sanyou.controller.app;

import com.sanyou.pojo.Product;
import com.sanyou.pojo.vo.OrderVo;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.service.OrderService;
import com.sanyou.service.ProductService;
import com.sanyou.service.ProjectService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: asus
 * Date: 2021-12-16
 * Time: 15:33
 * Version:V1.0
 */
@Api(value = "App相关的接口",tags = {"App相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/project/getProjectList")
    public JSONResult getProjectList(String userId){
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("用户Id为空!");
        }

        List<ProjectVo> projectList = projectService.getProjectList(userId);

        return JSONResult.ok(projectList);
    }

    @GetMapping("/order/getOrderList")
    public JSONResult getOrderList(Integer projectId){
        if(projectId == null)
            return JSONResult.errorMsg("项目不存在!");

        ProjectVo projectVo = projectService.getProject(projectId);
        if(projectVo == null)
            return JSONResult.errorMsg("项目不存在!");

        List<OrderVo> orderList = orderService.getProjectOrder(projectId);
        projectVo.setOrderList(orderList);

        return JSONResult.ok(projectVo);
    }


    @GetMapping("/product/checkExist")
    public JSONResult checkExist(String code){
        if(StringUtils.isBlank(code))
            return JSONResult.errorMsg("阴极板不存在");

        Product product = productService.query(code);
        if(product != null)
            return JSONResult.ok();
        else
            return JSONResult.errorMsg("阴极板不存在");
    }

    @GetMapping("/scancode/getInfo")
    public JSONResult getInfo(String scanCode,String userId,String tag){
        if(StringUtils.isBlank(scanCode))
            return JSONResult.errorMsg("阴极板编码为空!");

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id不能为空!");

        boolean flag = false;
        if(StringUtils.isBlank(tag))
            flag = false;
        if("1".equals(tag))
            flag = true;

        ProductVo dataVo = productService.getInfo(scanCode,userId,flag);

        return JSONResult.ok(dataVo);
    }

    @PostMapping("/product/updateState")
    public JSONResult updateState(@RequestBody ProductVo productVo){
        if(StringUtils.isBlank(productVo.getProductCode()))
            return JSONResult.errorMsg("阴极板不存在!");

        if(StringUtils.isBlank(productVo.getUserid()))
            return JSONResult.errorMsg("用户id不能为空!");

        productService.updateState(productVo);
        return JSONResult.ok();
    }
}
