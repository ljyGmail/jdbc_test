package com.atguigu.jdbc.e_bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User
 * Package: com.atguigu.jdbc.e_bean
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 17. 오전 3:37
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String user;
    private String password;
    private int balance;
}
