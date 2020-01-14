package cn.andios.jvm.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/14/19:23
 */
public class MyTest28 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc://localhost:3306/mytestdb", "username", "password");
    }
}
