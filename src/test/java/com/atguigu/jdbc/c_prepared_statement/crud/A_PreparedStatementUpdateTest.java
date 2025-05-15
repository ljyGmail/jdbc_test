package com.atguigu.jdbc.c_prepared_statement.crud;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * ClassName: PreparedStatementUpdateTest
 * Package: com.atguigu.jdbc.c_prepared_statement.crud
 * Description:
 * 使用PreparedStatement来替换Statement，实现对数据表的增删改查操作
 * <p>
 * 增删改 : 查
 *
 * @Author ljy
 * @Create 2025. 5. 15. 오후 1:14
 * @Version 1.0
 */
public class A_PreparedStatementUpdateTest {

    // 向customers表中添加一条记录
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // 1. 读取配置文件中的4个基本信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties props = new Properties();
            props.load(is);

            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String url = props.getProperty("url");
            String driverClass = props.getProperty("driverClass");

            // 2. 加载驱动
            Class.forName(driverClass);

            // 3. 获取连接
            conn = DriverManager.getConnection(url, user, password);

            // 4. 预编译sql语句，返回PreparedStatement的实例
            String sql = "insert into customers(name, email,birth) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            // 5. 填充占位符
            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@gmail.com");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new Date(date.getTime()));

            // 6. 执行操作
            ps.execute();

            System.out.println("插入数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 资源的关闭
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 修改customers表中的一条记录
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库的连接
            conn = JDBCUtils.getConnection();

            // 2. 预编译sql语句，返回PreparedStatement的实例
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);

            // 3. 填充占位符
            ps.setObject(1, "莫扎特");
            ps.setObject(2, 18);

            // 4. 执行
            ps.execute();
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
