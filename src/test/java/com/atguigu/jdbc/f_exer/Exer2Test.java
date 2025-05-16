package com.atguigu.jdbc.f_exer;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * ClassName: Exer2Test
 * Package: com.atguigu.jdbc.f_exer
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오후 2:16
 * @Version 1.0
 */
// 课后练习2
public class Exer2Test {

    // 通用的增删改操作
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
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    // 问题1: 向examstudent表中添加一条记录
    /*
    Type
    IDCard
    ExamCard
    StudentName
    Location
    Grade
     */
    @Test
    public void testInsert() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("四级/六级: ");
        int type = scanner.nextInt();
        System.out.print("身份证号: ");
        String idCard = scanner.next();
        System.out.print("准考证号: ");
        String examCard = scanner.next();
        System.out.print("学生姓名: ");
        String studentName = scanner.next();
        System.out.print("所在城市: ");
        String location = scanner.next();
        System.out.print("考试成绩: ");
        int grade = scanner.nextInt();

        String sql = "insert into examstudent (type, IDCard, examCard, studentName, location, grade) values(?, ?, ?, ?, ?, ?)";
        int insertCount = update(sql, type, idCard, examCard, studentName, location, grade);

        if (insertCount > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

}
