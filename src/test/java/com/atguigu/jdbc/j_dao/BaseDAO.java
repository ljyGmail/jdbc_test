package com.atguigu.jdbc.j_dao;

import com.atguigu.jdbc.d_util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: com.atguigu.jdbc.j_dao
 * Description:
 * DAO: Data(base) Access Object
 * 封装了针对于数据表的通用操作。
 *
 * @Author: ljy
 * @Create: 2025. 5. 18. 오후 11:29
 * @Version 1.0
 */
public abstract class BaseDAO {
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
            // 4. 资源的关闭
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    // 通用的查询操作，用于返回数据表中的一条记录(Version 2.0: 考虑上事务)
    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

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
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给对象指定的columnName属性，赋值为columnValue，通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    // 通用的查询操作，用于返回数据表中的多条记录构成的集合(Version 2.0: 考虑上事务)
    public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            // 创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列: 给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给cust对象指定的columnName属性，赋值为columnValue，通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    // 用于查询特殊值的通用方法
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
