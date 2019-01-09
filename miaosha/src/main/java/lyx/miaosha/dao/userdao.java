package lyx.miaosha.dao;

import lyx.miaosha.pojo.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @Title userdao
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 16:57
 */

@Mapper
public interface userdao {

    @Select("select * from user where id=#{id}")
    public user getbyid(@Param("id") long id);

    @Insert("insert into user(id,name)values(#{id},#{name})")
    public int insert(user user);
}
