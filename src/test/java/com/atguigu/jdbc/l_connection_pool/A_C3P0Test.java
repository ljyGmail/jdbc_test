package com.atguigu.jdbc.l_connection_pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;

/**
 * ClassName: C3P0Test
 * Package: com.atguigu.jdbc.l_connection_pool
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 19. 오전 10:28
 * @Version 1.0
 */
public class A_C3P0Test {

    // 方式1:
    @Test
    public void testGetConnection() throws Exception {
        // 获取C3P0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_learn");
        cpds.setUser("root");
        cpds.setPassword("445566hh");

        // 通过设置相关的参数，对数据库连接池进行管理
        // 设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();
        System.out.println(conn);

        // 销毁C3P0数据库连接池，一般不会使用
        // DataSources.destroy(cpds);
    }

    // 方式2: 使用配置文件
    @Test
    public void testGetConnection2() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
