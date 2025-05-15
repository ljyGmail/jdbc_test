package com.atguigu.jdbc.c_prepared_statement.crud;

import com.atguigu.jdbc.d_util.JDBCUtils;
import com.atguigu.jdbc.e_bean.Customer;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ClassName: CustomerForQuery
 * Package: com.atguigu.jdbc.c_prepared_statement.crud
 * Description:
 * 针对于customers表的查询操作
 *
 * @Author: ljy
 * @Create: 2025. 5. 15. 오후 9:23
 * @Version 1.0
 */
public class B_CustomerForQuery {

    @Test
    public void testQuery1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth from customers where id = ?";
            ps = conn.prepareStatement(sql);

            // 填充占位符
            ps.setObject(1, 1);

            // 执行并返回结果集
            resultSet = ps.executeQuery();
            // 处理结果集
            // next(): 判断结果集的下一条是否有数据，如果有数据返回true，并指针下移; 如果返回false，指针不会下移。
            if (resultSet.next()) {
                // 获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                // 方式一:
                // System.out.println("id = " + id + ", name = " + name + ", email = " + email + ", birth = " + birth);
                // 方式二:
                Object[] data = new Object[]{id, name, email, birth};
                // 方式三: 将数据封装为一个对象(推荐)
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.closeResource(conn, ps, resultSet);
        }
    }
}
