package com.nhapis.argus.er.message.controller;

import com.github.pagehelper.Page;
import com.nhapis.argus.er.message.model.MessageBean;
import com.nhapis.argus.er.message.model.PageInfo;
import com.nhapis.argus.er.message.service.MessageService;
import com.nhapis.argus.er.message.util.CheckUtil;
import com.nhapis.argus.er.message.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/argus-service-eventretrieval/message")
public class MessageController {
    @Autowired
    private MessageService message_modelService;

//    @PostMapping(value = "")
//    @ApiOperation(value = "添加消息", notes = "根据MessageBean对象创建用户")
//    @ApiImplicitParam(dataType = "MessageBean")
//    public Result save(@RequestBody MessageBean message_model) {
//        try {
//            return Result.success(message_modelService.save(message_model));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.failure(e.toString());
//        }
//    }

    @PostMapping(value = "/multiple")
    @ApiOperation(value = "批量添加消息", notes = "根据MessageBean对象Json数组创建用户")
    @ApiImplicitParam(dataType = "List<MessageBean>")
    public Result saveList(@RequestBody List<MessageBean> message_models) {
        try {
            return Result.success(message_modelService.saveList(message_models));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.toString());
        }
    }

    @PutMapping(value = "")
    @ApiOperation(value = "修改消息", notes = "修改消息 ")
    @ApiImplicitParam(dataType = "MessageBean")
    public Result update(@RequestBody MessageBean message_model) {
        try {
            return Result.success(message_modelService.update(message_model));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.toString());
        }
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "单击弹窗时显示的列表(只有一条记录)", notes = "单击弹窗时显示的列表(只有一条记录)")
    @ApiImplicitParam(dataType = "MessageBean")
    public Result get(@PathVariable("id") Integer id) {
        try {
            MessageBean message_model = message_modelService.get(id);
            if (message_model == null) {
                throw new Exception("未找到该消息,id:" + id);
            }
            return Result.success(message_model);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    @PostMapping(value = "/list/query")
    @ApiOperation(value = "获取用户列表", notes = "所有列表")
    @ApiImplicitParam(dataType = "Map<String, Object>")
    public Result getAll(@RequestBody Map<String, Object> params) {
        try {
            Page<MessageBean> pageBean = message_modelService.getAll(params);
            PageInfo<MessageBean> pageInfo = new PageInfo<>(pageBean);
            return Result.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.toString());
        }
    }

    public static String[] getMsgConfig() {

        String[] str = null;
        try {
            Class model = Class.forName("com.nhapis.argus.er.message.util.ModuleConstant");
            Field[] fields = model.getFields();

            for (Field field : fields) {
                System.out.println(field.getName() + " " + field.get(null));
            }

//            for (Field f : fields) {
//                String name = f.getName();
//                System.out.println("attribute name:" + name);
//                name = name.substring(0, 1).toUpperCase() + name.substring(1);
//                String type = f.getGenericType().toString();
//                if (type.equals("class java.lang.String")) {
//                    Method m = model.getClass().getMethod("get" + name);
//                    String value = (String) m.invoke(model);
//                    if (value != null) {
//                        System.out.println("attribute value:" + value);
//                    }
//                }
//            }
        } catch (Exception e) {

        }
        return str;
    }

    @PostMapping(value = "/unread/query")
    @ApiOperation(value = "查询未读消息记录数", notes = "查询未读消息记录数")
    @ApiImplicitParam(dataType = "Map<String, Object>")
    public Result getUnreadAll(@RequestBody Map<String, Object> params) {
        try {
            String status = (String) params.get("status");
            return Result.success(message_modelService.getUnreadAll(status));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.toString());
        }
    }

    @PostMapping(value = "/testmsg1")
    @ApiOperation(value = "查询未读消息记录数", notes = "查询未读消息记录数")
    @ApiImplicitParam(dataType = "Map<String, Object>")
    public Result testMessage1(@RequestBody Map<String, Object> params) {
        try {
            getMsgConfig();
            String status = (String) params.get("status");
            return Result.success(CheckUtil.fail("nhapis.userid.is.null"));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.toString());
        }
    }

    @PostMapping(value = "/testmsg2")
    @ApiOperation(value = "查询未读消息记录数", notes = "查询未读消息记录数")
    @ApiImplicitParam(dataType = "Map<String, Object>")
    public Result testMessage2(@RequestBody Map<String, Object> params) {
        try {
            String status = (String) params.get("status");
            return Result.success(CheckUtil.fail("nhapis.userid.is.null"));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.toString());
        }
    }
}