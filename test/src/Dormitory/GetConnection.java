/*连接数据库
**
**2019-7-2
*/
package Dormitory;

import java.sql.*;

public class GetConnection {
    private static final Connection conn = null;
    private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=student";
    private static final String USER = "sa";
    private static final String PASSWORD = "password";
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序,请检查是否导入驱动程序!");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("数据库连接失败, " + e.getMessage());
        }
        return conn;
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库连接失败, " + e.getMessage());
            }
        }
    }
}