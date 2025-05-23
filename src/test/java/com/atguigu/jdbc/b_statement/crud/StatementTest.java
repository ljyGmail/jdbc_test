package com.atguigu.jdbc.b_statement.crud;

import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * ClassName: StatementTest
 * Package: com.atguigu.jdbc.b_prepared_statement.statement
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 15. 오전 10:25
 * @Version 1.0
 */
public class StatementTest {

    // 使用Statement的弊端，需要拼接sql语句，并且存在SQL注入的问题
    // 如何避免出现SQL注入，只要使用PreparedStatement(从Statement扩展而来)取代Statement
    @Test
    public void testLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名: ");
        String user = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        // 输入如下信息就会造成SQL注入
        // 请输入用户名: xxx' and
        // 请输入密码: = 1 or '1' = '1
        String sql = "SELECT user, password FROM user_table WHERE user = '" + user + "' AND password = '" + password + "'";
        System.out.println(sql);
        User returnUser = get(sql, User.class);
        if (returnUser != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名不存在或密码错误");
        }
    }

    // 使用Statement实现对数据库的查询操作
    public <T> T get(String sql, Class<T> clazz) {
        T t = null;

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1. 加载配置文件
            InputStream is = StatementTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties props = new Properties();
            props.load(is);

            // 2. 读取配置信息
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String url = props.getProperty("url");
            String driverClass = props.getProperty("driverClass");

            // 3. 加载驱动
            Class.forName(driverClass);

            // 4. 加载驱动
            conn = DriverManager.getConnection(url, user, password);

            st = conn.createStatement();

            rs = st.executeQuery(sql);

            // 获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            // 获取结果集的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    // 1. 获取列的别名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    // 2. 根据列名获取数据表中的数据
                    Object columnVal = rs.getObject(columnName);

                    // 3. 将数据表中得到的数据，封装进对象
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
