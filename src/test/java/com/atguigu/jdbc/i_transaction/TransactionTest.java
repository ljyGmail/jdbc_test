package com.atguigu.jdbc.i_transaction;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: TransactionTest
 * Package: com.atguigu.jdbc.h_batch.i_transaction
 * Description:
 * 1. 什么叫数据库事务？
 * 事务: 一组逻辑操作单元，使数据从一种状态变换到两一种状态。
 * > 一组逻辑操作单元: 一个或多个DML操作
 * <p>
 * 2. 事务处理的原则:
 * 保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。
 * 当在一个事务中执行多个操作时，要么所有的事务都被提交(commit)，那么这些修改就永久地被保存下来;
 * 要么数据库管理系统将放弃所有修改，整个事务回滚(rollback)到最初状态。
 * <p>
 * 3. 数据一旦提交，就不可回滚。
 * <p>
 * 4. 哪些操作会导致数据的自动提交？
 * > DDL操作一旦执行，都会自动提交。
 * ----> set autocommit = false对DDL操作失效。
 * > DML默认情况下，一旦执行，就会自动提交。
 * ----> 我们可以通过set autocommit = false的方式取消DML操作的自动提交。
 * > 默认在关闭连接时，会自动地提交数据。
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오후 6:16
 * @Version 1.0
 */
public class TransactionTest {

    // ********************未考虑数据库事务情况下的转账操作********************
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

    // ********************考虑数据库事务情况下的转账操作********************
    // 通用的增删改操作 -- version2.0 (考虑上事务)
    public int update(Connection conn, String sql, Object... args) { // sql中占位符的个数与可变形参的长度相同
        PreparedStatement ps = null;
        try {
            // 1. 预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            // 2. 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]); // 小心参数声明错误
            }
            // 3. 执行
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复其为自动提交数据
                // 主要针对于使用数据库连接池的使用
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // 4. 资源的关闭
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    @Test
    public void testUpdateWithTx() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            System.out.println(conn.getAutoCommit());
            // 1. 取消数据的自动提交
            conn.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql1, "AA");

            // 模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql2, "BB");

            // 2. 提交数据
            conn.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 3. 回滚数据
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}
