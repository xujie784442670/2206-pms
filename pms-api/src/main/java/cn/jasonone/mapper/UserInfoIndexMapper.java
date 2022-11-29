package cn.jasonone.mapper;

import cn.jasonone.model.index.UserInfoIndex;

import java.util.List;

/**
* @author xujie
* @description 针对表【user_info_index_view】的数据库操作Mapper
* @createDate 2022-11-29 15:34:31
* @Entity cn.jasonone.model.index.UserInfoIndex
*/
public interface UserInfoIndexMapper {
    List<UserInfoIndex> findAll();
}




