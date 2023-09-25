package com.example.ecommerce;

public class Admin {
    int id;
    String name,email;
    public Admin(int id,String name,String email)
    {
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
