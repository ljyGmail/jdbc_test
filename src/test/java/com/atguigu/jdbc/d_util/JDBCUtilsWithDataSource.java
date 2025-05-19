package com.atguigu.jdbc.d_util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

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
    private static ComboPooledDataSource dataSouceC3P0 = new ComboPooledDataSource("helloc3p0");

    public static Connection getConnection1() throws SQLException {
        Connection conn = dataSouceC3P0.getConnection();
        return conn;
    }

    /**
     * 使用DBCP数据库连接池技术获取数据库连接
     *
     * @return
     * @throws Exception
     */
    // 创建一个DBCP数据库连接池

    private static DataSource dataSourceDBCP;

    static {
        try {
            Properties props = new Properties();
            FileInputStream is = new FileInputStream(new File("src/main/resources/dbcp.properties"));
            props.load(is);
            dataSourceDBCP = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws Exception {
        return dataSourceDBCP.getConnection();
    }

    /**
     * 使用Druid数据库连接池技术
     */
    private static DataSource dataSourceDruid;
    static{
        try {
            Properties props = new Properties();

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            props.load(is);
            dataSourceDruid = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection3() throws SQLException {
        return dataSourceDruid.getConnection();
    }
}
