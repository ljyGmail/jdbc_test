package com.atguigu.jdbc.e_bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ClassName: Student
 * Package: com.atguigu.jdbc.e_bean
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오후 2:34
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Student {
    private int flowID; // 流水号
    private int type; // 考试类型
    private String IDCard; // 身份证号
    private String examCard; // 准考证号
    private String name; // 学生姓名
    private String location; // 所在城市
    private int grade; // 成绩

    @Override
    public String toString() {
        System.out.println("========查询结果========");
        return info();
    }

    private String info() {
        return "流水号: " + flowID + "\n四级/六级: " + type + "\n身份证号: " + IDCard
                + "\n准考证号: " + examCard + "\n学生姓名: " + name + "\n区域: " + location
                + "\n成绩: " + grade;
    }
}
