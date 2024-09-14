package com.amstlan.ai.hunyuan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("message_log")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MessageLog", description = "消息日志表")
public class MessageLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "唯一标识")
    @TableId("id")
    private Long id;
    @ApiModelProperty(value = "聊天的角色")
    private String role;
    @ApiModelProperty(value = "聊天的内容")
    private String content;
    @TableField("UUID")
    private String UUID;
    @ApiModelProperty(value = "是否隐藏")
    private Integer isHidden;
    @ApiModelProperty(value = "用户id（暂时未使用）")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
    @ApiModelProperty(value = "修改时间")
    private String createUser;
    @ApiModelProperty(value = "修改时间")
    private Long updateTime;
    @ApiModelProperty(value = "修改人")
    private String updateUser;
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

}
