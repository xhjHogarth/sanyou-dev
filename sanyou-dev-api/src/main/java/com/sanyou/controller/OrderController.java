package com.sanyou.controller;

import com.sanyou.pojo.vo.OrderVo;
import com.sanyou.service.OrderService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: asus
 * Date: 2021-12-13
 * Time: 15:38
 * Version:V1.0
 */
@Api(value = "订单相关的接口",tags = {"订单相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public JSONResult addOrder(@RequestBody OrderVo orderVo){
        String checkMsg = checkOrder(orderVo);
        if(StringUtils.isNotBlank(checkMsg))
            return JSONResult.errorMsg(checkMsg);

        orderService.addOrder(orderVo);

        return JSONResult.ok();
    }

    @GetMapping("/query")
    public JSONResult query(Integer page, Integer pageSize,String query,Integer projectId){
        OrderVo orderVo = new OrderVo();
        orderVo.setQuery(query);
        orderVo.setProjectId(projectId);

        PagedResult result = orderService.query(page,pageSize,orderVo);
        return JSONResult.ok(result);
    }

    @PostMapping("/updateOrder")
    public JSONResult updateOrder(@RequestBody OrderVo orderVo){
        if(orderVo == null || orderVo.getId() == null){
            return JSONResult.errorMsg("订单不存在!");
        }

        String checkMsg = checkOrder(orderVo);
        if(StringUtils.isNotBlank(checkMsg))
            return JSONResult.errorMsg(checkMsg);

        orderService.updateOrder(orderVo);

        return JSONResult.ok();
    }

    private String checkOrder(OrderVo orderVo){
        if(orderVo == null || StringUtils.isBlank(orderVo.getOrderCode()))
            return "订单编码不能为空!";

        if(StringUtils.isBlank(orderVo.getOrderName()))
            return "订单名称不能为空!";

        if(StringUtils.isBlank(orderVo.getUserId()))
            return "用户Id不能为空!";

        if(orderVo.getProjectId() == null)
            return "请选择项目!";

        if(orderService.checkOrderExist(orderVo)){
            return "当前项目已经存在相同名称或编码的订单!";
        }

        return "";
    }

    @PostMapping("/deleteOrder")
    public JSONResult deleteOrder(Integer oid){
        if(oid == null)
            return JSONResult.errorMsg("订单不存在!");

        orderService.deleteOrder(oid);

        return JSONResult.ok();
    }

    @PostMapping("/distrProduct")
    public JSONResult distrProduct(@RequestBody OrderVo orderVo){
        if(orderVo == null || orderVo.getId() == null)
            return JSONResult.errorMsg("订单不存在!");

        if(StringUtils.isBlank(orderVo.getStartProductCode()))
            return JSONResult.errorMsg("阴极板起始编码不能为空!");

        if(StringUtils.isBlank(orderVo.getEndProductCode()))
            return JSONResult.errorMsg("阴极板截止编码不能为空!");

        if(Long.valueOf(orderVo.getStartProductCode()) > Long.valueOf(orderVo.getEndProductCode()))
            return JSONResult.errorMsg("起始编码不能大于截止编码!");

        boolean flag = orderService.checkCodeRange(orderVo.getStartProductCode(),orderVo.getEndProductCode());
        if(flag)
            return JSONResult.errorMsg("当前阴极板已被分配!");

        orderService.distrProduct(orderVo);

        return JSONResult.ok();
    }

    @GetMapping("/getOrderProduct")
    public JSONResult getOrderProduct(Integer oid,Integer page,Integer pageSize){
        PagedResult result = orderService.getOrderProduct(oid,page,pageSize);
        return JSONResult.ok(result);
    }
}
