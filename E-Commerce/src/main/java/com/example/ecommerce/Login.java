package com.example.ecommerce;

import java.sql.ResultSet;
import java.sql.SQLOutput;

public class Login {
    public Customer customerLogin(String userName,String password)
    {
        String query="select * from customer where Email= '"+userName+"'and Password= '"+password+"'";
        DbConnection connection=new DbConnection();
        try
        {
            ResultSet rs=connection.getQueryTable(query);
            if(rs.next())
            {
                return new Customer(rs.getInt("Id"), rs.getString("Name"),
                        rs.getString("Email"), rs.getString("Mobile"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Login login=new Login();
        Customer customer=login.customerLogin("kumar76sumit@gmail.com","Kumar76sum@");
        System.out.println("Welcome : " + customer.getName());
//        System.out.println(login.customerLogin("kumar76sumit@gmail.com","Kumar76sum@"));
    }
}
