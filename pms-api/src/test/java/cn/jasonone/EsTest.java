package cn.jasonone;

import cn.jasonone.model.index.UserInfoIndex;
import cn.jasonone.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xujie
 * @version 1.0.0
 */
@SpringBootTest
public class EsTest {
    @Resource
    private UserInfoRepository userInfoRepository;
    @Test
    void test(){
        // mq+view
        UserInfoIndex userInfoIndex = new UserInfoIndex();
        userInfoIndex.setId("1");
        userInfoIndex.setAccount("admin");
        userInfoIndex.setRoleName("管理员");
        userInfoIndex.setPermissionName("权限管理");
        userInfoRepository.save(userInfoIndex);
    }
}
