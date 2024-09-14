package com.amstlan.ai.hunyuan.utils;

import com.amstlan.ai.hunyuan.dto.MessageLogDTO;
import com.amstlan.ai.hunyuan.entity.MessageLog;
import com.tencentcloudapi.hunyuan.v20230901.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesUtils {

    /**
     * 将传入的List<MessageLog> 转换为 List<Message>
     */
    public static List<Message> convertToMessageList(List<MessageLog> messageLogList) {
        List<Message> messageList = new ArrayList<>();
        for (MessageLog messageLog : messageLogList) {
            Message message = new Message();
            message.setRole(messageLog.getRole());
            message.setContent(messageLog.getContent());
            messageList.add(message);
        }
        return messageList;
    }

    /**
     * 将传入的MessageLog 转换为 Message
     */
    public static Message convertToMessage(MessageLog messageLog) {
        Message message = new Message();
        message.setRole(messageLog.getRole());
        message.setContent(messageLog.getContent());
        return message;
    }

    /**
     * 将传入的List<Message> 转换为 List<MessageLog>
     */
    public static List<MessageLog> convertToMessageLogList(List<Message> messageList, String uuid) {
        List<MessageLog> messageLogList = new ArrayList<>();
        for (Message message : messageList) {
            MessageLog messageLog = new MessageLog();
            messageLog.setUUID(uuid);
            messageLog.setRole(message.getRole());
            messageLog.setContent(message.getContent());
            messageLogList.add(messageLog);
        }
        return messageLogList;
    }

    /**
     * 将传入的Message 转换为 MessageLog
     */
    public static MessageLog convertToMessageLog(Message message, String uuid) {
        MessageLog messageLog = new MessageLog();
        messageLog.setUUID(uuid);
        messageLog.setRole(message.getRole());
        messageLog.setContent(message.getContent());
        return messageLog;
    }

    /**
     * 将List<MessageLog> 转换为 List<MessageLogDTO>
     */
    public static List<MessageLogDTO> convertToMessageLogDTOList(List<MessageLog> messageLogList) {
        List<MessageLogDTO> messageLogDTOList = new ArrayList<>();
        for (MessageLog messageLog : messageLogList) {
            MessageLogDTO messageLogDTO = new MessageLogDTO();
            messageLogDTO.setRole(messageLog.getRole());
            messageLogDTO.setContent(messageLog.getContent());
            messageLogDTO.setUUID(messageLog.getUUID());
            messageLogDTOList.add(messageLogDTO);
        }
        return messageLogDTOList;
    }

}
