package com.atguigu.jdbc.b_prepared_statement.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User
 * Package: com.atguigu.jdbc.b_prepared_statement.statement
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 15. 오전 10:18
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String user;
    private String password;
}
