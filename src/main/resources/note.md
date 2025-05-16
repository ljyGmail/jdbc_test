# 3.6 JDBC API小结

- 两种思想
    - 面向接口编程的思想
    - ORM思想(Object Relational Mapping)
        - 一个数据表对应一个Java类
        - 表中的一条记录对应Java类的一个对象
        - 表中的一个字段对应Java类的一个属性

> SQL是需要结合列明和类的属性名来写，注意起别名。

- 两种技术
    - JDBC结果集的元数据: ResultSetMetaData
        - 获取列数: getColumnCount()
        - 获取列的别名: getColumnLabel()
    - 通过反射，创建指定类的对象，获取指定的属性并赋值

# 章节练习

![img.png](images/exer1.png)

![img.png](images/exer2_a.png)
![img_1.png](images/exer2_b.png)
![img_2.png](images/exer2_c.png)
![img.png](images/exer2_d.png)

- 对于如下异常，需要在`my.ini`配置文件中加上`[mysqld] max_allowed_packet=16M`
  ![img.png](images/blob_insert_exception.png)

## 事务的ACID属性及4种隔离级别

![img.png](images/acid_a.png)
![img_1.png](images/acid_b.png)
![img_2.png](images/acid_c.png)

### 6.3.3 在MySql中设置隔离级别

- 每启动一个mysql程序，就会获得一个单独的数据库连接，每个数据库连接都有一个全局变量`@@transaction_isolation`，表示当前的事务隔离级别。
- 查看当前的隔离级别: `select @@transaction_isolation;`
- 设置当前mysql连接的隔离级别: `set transaction isolation level read committed;`
- 设置数据库系统的全局隔离级别: `set global transaction isolation level read committed;`
- 补充操作
    - 创建mysql数据库用户: `create user tom identified by '123456';`
    - 授予权限: `grant select, insert, update, delete on test.* to tom@localhost;`
