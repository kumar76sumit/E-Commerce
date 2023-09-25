package com.example.ecommerce;

import java.sql.ResultSet;

public class AdminLogin {
    public Admin adminLogin(String userName,String password)
    {
        String query="select * from admin where adminEmail='"+userName+"' and adminPassword='"+password+"'";
        DbConnection dbConnection=new DbConnection();

        try {
            ResultSet rs=dbConnection.getQueryTable(query);
            if(rs.next())
            {
                return new Admin(rs.getInt("adminId"),rs.getString("adminName"),rs.getString("adminEmail"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
