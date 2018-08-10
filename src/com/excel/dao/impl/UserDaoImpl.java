package com.excel.dao.impl;

import com.excel.dao.UserDao;
import com.excel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class UserDaoImpl implements UserDao {
//    @Autowired(required = true)
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public int insertUser(String name, String sex, int age) {
//        String sql = "insert into user(name,sex,age) values('"+ name +"','"+ sex +"',"+age+")";
//        int ret = 0;
//        try {
//            ret = jdbcTemplate.update(sql);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public int insertUser(String name, String sex, String age) {
        User user = new User(name, sex, age);
        System.out.println("*******************************************user="+user);
        Session session = this.getSession();
        try {
            session.save(user);
        }catch (Exception e){

        } finally {
            session.close();
        }
        return 1;
    }
}
