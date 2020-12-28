package com.crowd.test;

import com.crowd.entity.Admin;
import com.crowd.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//spring整合junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testConn() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void getmapper(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date date = new Date();
        String format1 = format.format(date);
        Admin admin = new Admin(null, "tom", "123123", "汤姆", "1242@qq.com", format1);
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }
}
