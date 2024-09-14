package com.amstlan.ai.hunyuan.utils;

import com.amstlan.ai.hunyuan.dto.MessageLogDTO;
import com.google.gson.*;
import com.tencentcloudapi.hunyuan.v20230901.models.Message;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * 将字符串转换为数组
     */
    public static  List<Message>  toArray(String content) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("Message");

        List<Message> messageList = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            Message message = gson.fromJson(element, Message.class);
            messageList.add(message);
        }

        return messageList;

    }


    /**
     * 读取指定资源文件的文本内容
     */
    public static String readFileContent(String filePath) {
        // 读取文件内容为字符串
        String content;
        try {
            ClassPathResource resource = new ClassPathResource("init" + File.separator + filePath);
            File file = resource.getFile();
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }


    /**
     * 获取返回值的数据部分
     */
    public static MessageLogDTO getResponseData(String response) {
        MessageLogDTO res = null;
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonArray choicesArray = jsonObject.getAsJsonArray("Choices");

        if (!choicesArray.isEmpty()) {
            JsonObject firstChoice = choicesArray.get(0).getAsJsonObject();
            JsonObject message = firstChoice.getAsJsonObject("Message");

            res = new MessageLogDTO();
            // 打印结果
            res.setRole(message.get("Role").getAsString());
            res.setContent(message.get("Content").getAsString());
        }
        return res;
    }
}
