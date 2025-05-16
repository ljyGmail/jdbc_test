package com.atguigu.jdbc.i_transaction;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * ClassName: TransactionTest
 * Package: com.atguigu.jdbc.h_batch.i_transaction
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오후 6:16
 * @Version 1.0
 */
public class TransactionTest {

    // 通用的增删改操作 -- version1.0
    public int update(String sql, Object... args) { // sql中占位符的个数与可变形参的长度相同
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库的连接
            conn = JDBCUtils.getConnection();
            // 2. 预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            // 3. 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]); // 小心参数声明错误
            }
            // 4. 执行
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    /*
    针对于数据表user_table来说:
    AA用户给BB用户转账100

    update user_table set balance = balance - 100 where user = 'AA';
    update user_table set balance = balance + 100 where user = 'BB';
     */
    @Test
    public void testUpdate() {
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        // 模拟网络异常
        System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");
    }
}
