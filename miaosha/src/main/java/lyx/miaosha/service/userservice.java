package lyx.miaosha.service;

import lyx.miaosha.dao.userdao;
import lyx.miaosha.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title userservice
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 17:33
 */

@Service
public class userservice {

    @Autowired
    private userdao userdao;

    public user getbyid(long id){
        return userdao.getbyid(id);
    }

    public int insert(user user){
        return userdao.insert(user);
    }

    @Transactional
    public boolean tx(){
        user user1=new user();
        user1.setId(2);
        user1.setName("222");
        userdao.insert(user1);

        user user2=new user();
        user2.setId(3);
        user2.setName("333");
        userdao.insert(user2);

        return true;
    }

}
