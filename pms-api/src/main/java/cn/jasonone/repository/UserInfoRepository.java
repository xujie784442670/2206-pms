package cn.jasonone.repository;

import cn.jasonone.model.index.UserInfoIndex;
import org.apache.catalina.User;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xujie
 * @version 1.0.0
 */
@Repository
public interface UserInfoRepository extends ElasticsearchRepository<UserInfoIndex,String> {

    @Highlight(fields = {
            @HighlightField(name = "rolename"),
            @HighlightField(name = "permissionname"),
    },
            parameters =
            @HighlightParameters(
                    postTags = "</span>",
                    preTags = "<span class='hm'>",
                    numberOfFragments = 0
            )

    )
    List<SearchHit<UserInfoIndex>> findByAccountLikeOrRolenameLikeOrPermissionnameLike(String account, String rolename, String permissionname);
}
