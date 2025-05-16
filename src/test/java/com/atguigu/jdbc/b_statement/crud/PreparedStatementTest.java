package com.atguigu.jdbc.b_statement.crud;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * ClassName: PreparedStatementTest
 * Package: com.atguigu.jdbc.b_statement.crud
 * Description:
 * 演示使用PreparedStatement替换Statement，解决SQL注入问题
 * <p>
 * 除了解决Statement的拼串、SQL注入问题之外，PreparedStatement还有哪些好处呢？
 * 1. PreparedStatement可以操作Blob数据，而Statement是做不到的。
 * 2. PreparedStatement可以实现更高效的批量操作。
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오전 11:09
 * @Version 1.0
 */
public class PreparedStatementTest {
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
        // String sql = "SELECT user, password FROM user_table WHERE user = '" + user + "' AND password = '" + password + "'";
        String sql = "SELECT user, password FROM user_table WHERE user = ? AND password = ?";
        System.out.println(sql);
        User returnUser = getInstance(User.class, sql, user, password);
        if (returnUser != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名不存在或密码错误");
        }
    }

    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给cust对象指定的columnName属性，赋值为columnValue，通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
}
