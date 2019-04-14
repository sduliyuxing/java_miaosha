package lyx.miaosha.dao;


import lyx.miaosha.pojo.miaoshauser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface miaoshauserdao {

    @Select("select * from miaosha_user where id=#{id}")
    public miaoshauser getbyid(@Param("id") long id);
}
