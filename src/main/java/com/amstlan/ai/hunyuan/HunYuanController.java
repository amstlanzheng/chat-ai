package com.amstlan.ai.hunyuan;

import com.amstlan.ai.common.Result.ApiResponse;
import com.amstlan.ai.hunyuan.dto.MessageLogDTO;
import com.amstlan.ai.hunyuan.entity.MessageLog;
import com.amstlan.ai.hunyuan.utils.HunYuanUtil;
import com.amstlan.ai.hunyuan.utils.JsonUtils;
import com.amstlan.ai.hunyuan.utils.MessagesUtils;
import com.amstlan.ai.service.MessageLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencentcloudapi.hunyuan.v20230901.models.Message;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/hunyuan")
@Api(value = "腾讯混元模型", tags = "腾讯混元模型")
public class HunYuanController {

    @Autowired
    private MessageLogService messageLogService;

    @PostMapping("/init")
    public ApiResponse<MessageLogDTO> getHunYuan() {

        //读取预先生成的json文件
        String content = JsonUtils.readFileContent("neko");
        //将json字符串转换为Message数组
        List<Message> messages = JsonUtils.toArray(content);
        //初始化信息存入数据库
        String uuid = messageLogService.saveMessageLogs(messages);
        // 调用混元接口
        String hunYuan = HunYuanUtil.getHunYuan(messages.toArray(new Message[0]));
        // 将混元接口返回的json字符串转换为MessageLogDTO对象
        MessageLogDTO responseData = JsonUtils.getResponseData(hunYuan);
        Message message = new Message();
        BeanUtils.copyProperties(responseData, message);
        //存入数据库
        messageLogService.saveMessageLog(message, uuid);
        //封装返回数据
        MessageLogDTO messageLog = new MessageLogDTO();
        BeanUtils.copyProperties(message, messageLog);
        messageLog.setUUID(uuid);
        return new ApiResponse<>(200, "操作成功", messageLog);
    }


    /**
     * 根据UUID获取聊天记录
     */
    @GetMapping("/getByUUID")
    public ApiResponse<List<MessageLogDTO>> getHunYuan(@RequestParam String UUID) {
        QueryWrapper<MessageLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UUID", UUID);
        queryWrapper.orderByDesc("create_time");
        queryWrapper.eq("is_hidden", 0);

        List<MessageLog> list = messageLogService.list(queryWrapper);
        List<MessageLogDTO> messageLogDTOS = MessagesUtils.convertToMessageLogDTOList(list);
        return new ApiResponse<>(200, "操作成功", messageLogDTOS);
    }


    /**
     * 输入聊天信息
     */
    @PostMapping("chat")
    public ApiResponse<MessageLogDTO> chat(@RequestBody MessageLog messageLog) {

        QueryWrapper<MessageLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UUID", messageLog.getUUID());
        queryWrapper.orderByAsc("create_time");
        queryWrapper.last("limit 40");
        //查询之前的聊天记录
        List<MessageLog> list = messageLogService.list(queryWrapper);
        if (list.isEmpty()) {
            return new ApiResponse<>(400, "UUID不存在");
        }
        Message message = MessagesUtils.convertToMessage(messageLog);
        List<Message> messages = MessagesUtils.convertToMessageList(list);
        messages.add(message);
        // 调用混元接口
        String hunYuan = HunYuanUtil.getHunYuan(messages.toArray(new Message[0]));
        // 将混元接口返回的json字符串转换为MessageLogDTO对象
        MessageLogDTO responseData = JsonUtils.getResponseData(hunYuan);
        Message messageRes = new Message();
        BeanUtils.copyProperties(responseData, messageRes);
        //存入数据库
        Message messageReq = MessagesUtils.convertToMessage(messageLog);
        messageLogService.saveMessageLog(messageReq, messageLog.getUUID());
        messageLogService.saveMessageLog(messageRes, messageLog.getUUID());
        //封装返回数据
        MessageLogDTO messageLogRes = new MessageLogDTO();
        BeanUtils.copyProperties(message, messageLogRes);
        messageLogRes.setUUID(messageLog.getUUID());
        return new ApiResponse<>(200, "操作成功", responseData);
    }

}
