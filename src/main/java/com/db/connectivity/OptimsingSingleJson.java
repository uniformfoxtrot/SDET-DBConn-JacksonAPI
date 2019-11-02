package com.db.connectivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OptimsingSingleJson {
    public static void main(String[] a) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        String dummy = null;
        ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();
        JSONArray jsonArray = new JSONArray();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "Samsung@123");
        // Create object of Statement Class
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where location='ASIA';");
        while (rs.next())
        {
            CustomerDetails c1 = new CustomerDetails();
            // here we need to mention the number of column mapping
            c1.setCourseName(rs.getString(1));
            c1.setPurchaseDate(rs.getString(2));
            c1.setAmount(rs.getInt(3));
            c1.setLocation(rs.getString(4));
            al.add(c1);
        }

        for(int i=0;i<al.size();i++)
        {
            ObjectMapper om = new ObjectMapper();
            om.writeValue(new File("//Users//lee//Documents//DB_connection//customerInfo"+i+".json"),al.get(i));
            Gson gson = new Gson();
            String jsonString = gson.toJson(al.get(i));
            jsonArray.add(jsonString);
        }
        // Creating Single json from multiple java objects.

        JSONObject jo = new JSONObject();
        jo.put("data",jsonArray);
        System.out.println("JSON Object:"+jo.toString());

        String unescapeString = StringEscapeUtils.unescapeJson(jo.toString());
        System.out.println("JSON Object unescaped:"+unescapeString);
        String str1 = unescapeString.replace("\"{","{");
        String finalString = str1.replace("}\"","}");
        System.out.println(finalString);
        // Try with resource usage
        try (FileWriter file = new FileWriter("//Users//lee//Documents//DB_connection//FinalResult.json")) {
            file.write(finalString);

        }

        ObjectMapper om = new ObjectMapper();
        om.writeValue(new File("//Users//lee//Documents//DB_connection//customerInfo"+".json"),jo);
        conn.close();


        // Written for reference not part of this...
        for(int i=0;i<al.size();i++)
        {
            ObjectMapper om1 = new ObjectMapper();

            dummy = om1.writeValueAsString(al.get(i));
            jsonArray.add(dummy);
        }
        System.out.println("\n"+jsonArray.toString());

    }
}
