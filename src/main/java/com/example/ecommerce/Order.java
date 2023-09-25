package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {
    public static boolean placeOrder(Customer customer,Product product)
    {
        String groupOrderId="select max(Group_OrderId)+1 id from orders";
        DbConnection dbConnection=new DbConnection();
        try
        {
            ResultSet rs=dbConnection.getQueryTable(groupOrderId);
            if(rs.next())
            {
                String placeOrder="insert into orders(Group_OrderId,Customer_Id,Product_Id) values("+rs.getInt("id")+","+customer.getId()+","+product.getId()+");";
                return dbConnection.updateDatabase(placeOrder)!=0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static int placeMultipleOrder(Customer customer, ObservableList<Product> productList)
    {
        String groupOrderId="select max(Group_OrderId)+1 id from orders";
        DbConnection dbConnection=new DbConnection();
        try
        {
            ResultSet rs=dbConnection.getQueryTable(groupOrderId);
            int count=0;
            if(rs.next())
            {
                for(Product product: productList)
                {
                    String placeOrder="insert into orders(Group_OrderId,Customer_Id,Product_Id) values("+rs.getInt("id")+","+customer.getId()+","+product.getId()+");";
                    count+=dbConnection.updateDatabase(placeOrder);
                }
                return count;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
