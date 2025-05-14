package com.atguigu.jdbc.a_connection;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: ConnectionTest
 * Package: com.atguigu.jdbc.a_connection
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 14. 오후 5:41
 * @Version 1.0
 */
public class ConnectionTest {
    // 方式一
    @Test
    public void testConnection1() throws SQLException {
        // 获取Driver的实现类对象
        Driver driver = new Driver();

        // jdbc:mysql协议
        // localhost: ip地址
        // 3306: 默认mysql的端口号
        // test: test数据库
        String url = "jdbc:mysql://localhost:3306/test";

        // 将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "445566hh");

        Connection conn = driver.connect(url, info);
        System.out.println("方式一: " + conn);
    }

    // 方式二: 对方式一对迭代
    @Test
    public void testConnection2() throws Exception {
        // 1. 获取Driver实现类的对象，使用反射
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        // 2. 提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        // 3. 提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "445566hh");

        // 4. 获取连接
        Connection conn = driver.connect(url, info);
        System.out.println("方式二: " + conn);

    }

}
