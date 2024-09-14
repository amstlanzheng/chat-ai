package com.amstlan.ai.hunyuan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MessageLogDTO", description = "消息日志表DTO")
public class MessageLogDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "聊天的角色")
    private String role;

    @ApiModelProperty(value = "聊天的内容")
    private String content;

    @ApiModelProperty(value = "唯一标识")
    private String UUID;

}
