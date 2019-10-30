package com.db.connectivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class single_row_result_fetch
{
    public static void main(String [] l) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn =null;
        CustomerDetails cd = new CustomerDetails();
        /*getConnection(url,user,password) for url->jdbc:mysql/jdbc:oracle -- always give the DB name that needs to be
        connected */
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","Samsung@123");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where Location='Asia';");
        while(rs.next())
        {
            /*  where we need to create multiple java objects that suites the need of our query...some times the
            query may fetch 1 record but some times it may fetch 100's of record  */
            cd.setCourseName(rs.getString(1));
            cd.setPurchaseDate(rs.getString(2));
            cd.setAmount(rs.getInt(3));
            cd.setLocation(rs.getString(4));
            System.out.print(cd.getCourseName()+",");
            System.out.print(cd.getPurchaseDate()+",");
            System.out.print(cd.getAmount()+",");
            System.out.print(cd.getLocation()+"\n");
        }
        ObjectMapper om = new ObjectMapper();
        om.writeValue(new File("//Users//lee//Documents//DB_connection//firstRow.json"),cd);
        conn.close();

    }
}
