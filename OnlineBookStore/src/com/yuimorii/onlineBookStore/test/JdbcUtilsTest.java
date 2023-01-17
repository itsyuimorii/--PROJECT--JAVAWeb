package com.yuimorii.onlineBookStore.test;

import com.yuimorii.onlineBookStore.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @program: Javaweb
 * @description:
 * @author: yuimorii
 * @create: 2022-12-27 13:21
 **/
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }
    }
}