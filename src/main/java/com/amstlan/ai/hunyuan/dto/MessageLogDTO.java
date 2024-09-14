package com.amstlan.ai.hunyuan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageLogDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String role;
    private String content;
    private String UUID;

}
