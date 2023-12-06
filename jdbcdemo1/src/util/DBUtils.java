package util;

import java.sql.*;

public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username="root";
        String password="wu123456";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeAll(Connection conn, Statement sm, ResultSet rs)
    {
        try {
            if(rs!=null)
                rs.close();
            if(sm!=null)
                sm.close();
            if(conn!=null)
                conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
