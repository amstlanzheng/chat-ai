package com.amstlan.ai.service.impl;

import com.amstlan.ai.hunyuan.entity.MessageLog;
import com.amstlan.ai.hunyuan.mapper.MessageLogMapper;
import com.amstlan.ai.service.MessageLogService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.tencentcloudapi.hunyuan.v20230901.models.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageLogServiceImpl extends ServiceImpl<MessageLogMapper, MessageLog> implements MessageLogService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveMessageLogs(List<Message> messages) {
        String uuid = UUID.randomUUID().toString();
        List<MessageLog> messageLogList = new ArrayList<>();
        int size = 0;
        for (Message message : messages) {
            MessageLog messageLog = new MessageLog();
            messageLog.setUUID(uuid);
            messageLog.setContent(message.getContent());
            messageLog.setRole(message.getRole());
            //初始化的对话不需要给用户
            messageLog.setIsHidden(1);
            long timeMillis = System.currentTimeMillis();
            messageLog.setCreateTime(timeMillis + size++);
            messageLog.setUpdateTime(timeMillis + size++);
            messageLogList.add(messageLog);
        }
        this.saveBatch(messageLogList);
        return uuid;
    }

    @Override
    public void updateMessageLog(List<Message> messages, String uuid) {
        MessageLog messageLog = new MessageLog();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(messages);
        messageLog.setContent(jsonStr);

        UpdateWrapper<MessageLog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("UUID", uuid);
        this.update(messageLog, updateWrapper);
    }

    @Override
    public void saveMessageLog(Message message, String uuid) {
        MessageLog messageLog = new MessageLog();
        messageLog.setUUID(uuid);
        messageLog.setContent(message.getContent());
        messageLog.setRole(message.getRole());
        //初始化的对话不需要给用户
        messageLog.setIsHidden(0);
        long timeMillis = System.currentTimeMillis();
        messageLog.setCreateTime(timeMillis);
        messageLog.setUpdateTime(timeMillis);
        this.save(messageLog);
    }
}
