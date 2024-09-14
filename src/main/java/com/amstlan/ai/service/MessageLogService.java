package com.amstlan.ai.service;

import com.amstlan.ai.hunyuan.entity.MessageLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencentcloudapi.hunyuan.v20230901.models.Message;

import java.util.List;

public interface MessageLogService extends IService<MessageLog> {
    /**
     * 保存消息日志
     * @param messages 消息列表
     */
    String saveMessageLogs(List<Message> messages);

    /**
     * 更新消息日志
     * @param messages 消息列表
     * @param uuid 唯一标识
     */
    void updateMessageLog(List<Message> messages, String uuid);

    /**
     * 保存消息日志
     * @param message 消息列表
     * @param uuid 唯一标识
     */
    void saveMessageLog(Message message, String uuid);
}
