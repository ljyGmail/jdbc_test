package com.atguigu.jdbc.l_connection_pool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: B_DBCPTest
 * Package: com.atguigu.jdbc.l_connection_pool
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 19. 오후 1:11
 * @Version 1.0
 */
public class B_DBCPTest {

    /**
     * 测试DBCP的数据库连接池技术
     */
    // 方式1: 不推荐
    @Test
    public void testGetConection() throws SQLException {
        // 创建DBCP的数据库连接池
        BasicDataSource source = new BasicDataSource();

        // 设置基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/jdbc_learn");
        source.setUsername("root");
        source.setPassword("445566hh");

        // 还可以设置其他数据库连接池管理的相关属性
        source.setInitialSize(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    // 方式2: 推荐: 使用配置文件
    @Test
    public void testGetConection2() throws Exception {
        Properties props = new Properties();
        // 方式1:
        // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        // 方式2:
        FileInputStream is = new FileInputStream(new File("src/main/resources/dbcp.properties"));
        props.load(is);
        BasicDataSource source = BasicDataSourceFactory.createDataSource(props);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
