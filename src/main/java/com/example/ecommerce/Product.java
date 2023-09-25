package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleDoubleProperty Price;

    public Product(int id, String name, double price) {
        Id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty(name);
        Price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts()
    {
        String query="select Id,Name,Price from product";
        return fetchProductData(query);
    }

    public static ObservableList<Product> fetchProductData(String query)
    {
        ObservableList<Product> data= FXCollections.observableArrayList();
        DbConnection dbConnection=new DbConnection();
        try
        {
            ResultSet rs=dbConnection.getQueryTable(query);
            while(rs.next())
            {
                Product product=new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"));
                data.add(product);
            }
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public int getId() {
        return Id.get();
    }

    public String getName() {
        return Name.get();
    }

    public double getPrice() {
        return Price.get();
    }
}
