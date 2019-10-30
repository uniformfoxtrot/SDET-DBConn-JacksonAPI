package com.db.connectivity;

import java.sql.*;

public class BasicJDBC_implementation
{
    public static void main(String [] l) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn =null;
        /*getConnection(url,user,password) for url->jdbc:mysql/jdbc:oracle -- always give the DB name that needs to be
        connected */
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","Samsung@123");
        Statement st = conn. createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where Location='Asia';");
        while(rs.next())
        {
            System.out.print(rs.getString(1)+",");
            System.out.print(rs.getString(2)+",");
            System.out.print(rs.getInt(3)+",");
            System.out.print(rs.getString(4)+"\n");
        }
        conn.close();

    }
}
