package com.db.connectivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Json2JavaObject
{
    public static void main(String[] args) throws IOException {
        ObjectMapper om = new ObjectMapper();
        CustomerDetailsAppium cda = om.readValue
                (new File("//Users//lee//Documents//DB_connection//customerInfo0.json")
                        ,CustomerDetailsAppium.class);

        System.out.println(cda.getStudentName());
    }
}
