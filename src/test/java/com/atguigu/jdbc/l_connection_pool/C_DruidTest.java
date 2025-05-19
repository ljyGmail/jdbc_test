package com.atguigu.jdbc.l_connection_pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ClassName: C_DruidTest
 * Package: com.atguigu.jdbc.l_connection_pool
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 19. 오후 1:37
 * @Version 1.0
 */
public class C_DruidTest {

    @Test
    public void getConnection() throws Exception {
        Properties props = new Properties();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        props.load(is);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
