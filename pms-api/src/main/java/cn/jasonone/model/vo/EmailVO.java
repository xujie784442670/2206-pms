package cn.jasonone.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujie
 * @version 1.0.0
 */
@Data
public class EmailVO implements Serializable {
    /**
     * 收件人
     */
    private String to;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
}
