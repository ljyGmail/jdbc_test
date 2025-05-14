package com.atguigu.jdbc;


import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: ConnectionTest
 * Package: com.atguigu.jdbc
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 14. 오후 5:20
 * @Version 1.0
 */
public class ConnectionTest {

    public static void main(String[] args) throws SQLException {
        Driver driver = new Driver();

        String url = "jdbc:mysql://localhost:3306/info_pro";

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "445566hh");

        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }
}
