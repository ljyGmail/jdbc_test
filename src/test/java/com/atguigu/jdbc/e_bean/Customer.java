package com.atguigu.jdbc.e_bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * ClassName: Customer
 * Package: com.atguigu.jdbc.e_bean
 * Description:
 * ORM编程思想 (Object Relational Mapping)
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类的一个对象
 * 表中的一个字段对应java类的一个属性
 *
 * @Author: ljy
 * @Create: 2025. 5. 15. 오후 9:36
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
}
