package cn.jasonone.model.index;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 
 * @TableName user_info_index_view
 */
@Data
@Document(indexName = "user_info_index")
public class UserInfoIndex implements Serializable {
    /**
     * 
     */
    @Id
    private String id;

    /**
     * 账号;用户登录的唯一凭证
     */
    private String account;

    /**
     * 名称
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String rolename;

    /**
     * 权限名称
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String permissionname;

    private static final long serialVersionUID = 1L;
}
