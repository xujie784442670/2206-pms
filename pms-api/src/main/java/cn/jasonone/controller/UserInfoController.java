package cn.jasonone.controller;

import cn.jasonone.model.index.UserInfoIndex;
import cn.jasonone.model.vo.HttpResult;
import cn.jasonone.repository.UserInfoRepository;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xujie
 * @version 1.0.0
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoRepository userInfoRepository;
    // 1. 搜索关键字有哪些?
    // 2. 显示哪些字段
    @GetMapping
    public HttpResult findAll(String keyWords){
        List<SearchHit<UserInfoIndex>> list = userInfoRepository.findByAccountLikeOrRolenameLikeOrPermissionnameLike(keyWords, keyWords, keyWords);
        return HttpResult.ok(list);
    }
}
