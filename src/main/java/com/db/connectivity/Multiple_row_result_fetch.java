package com.db.connectivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Multiple_row_result_fetch
{
    public static void main(String[] a) throws ClassNotFoundException, SQLException, IOException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "Samsung@123");
        // Create object of Statement Class
        Statement st = conn.createStatement();
        ResultSet rs =st.executeQuery("select * from CustomerInfo where location='ASIA';");
        while(rs.next())
        {
            CustomerDetails c = new CustomerDetails();
            // here we need to mention the number of column mapping
            c.setCourseName(rs.getString(1));
            c.setPurchaseDate(rs.getString(2));
            c.setAmount(rs.getInt(3));
            c.setLocation(rs.getString(4));
            al.add(c);
        }
        for(int i=0;i<al.size();i++)
        {
            ObjectMapper om = new ObjectMapper();
            om.writeValue(new File("//Users//lee//Documents//DB_connection//customerInfo"+i+".json"),al.get(i));
        }
        conn.close();
    }
}
