package com.atguigu.jdbc.d_util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: JDBCUtilsWithDataSource
 * Package: com.atguigu.jdbc.d_util
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 19. 오전 11:26
 * @Version 1.0
 */
public class JDBCUtilsWithDataSource {
    /**
     * 使用C3P0的数据库连接池技术
     *
     * @return
     * @throws SQLException
     */
    // 数据库连接池只需提供一个即可。
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection1() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }
}
