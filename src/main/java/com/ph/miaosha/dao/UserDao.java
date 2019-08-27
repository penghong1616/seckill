package com.ph.miaosha.dao;

import com.ph.miaosha.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select * from sk_user where id=#{id}")
    public User getById(@Param("id") long id);
    @Update("update sk_user set password=#{password} where id=#{id}")
    void update(User userUpdate);
}
