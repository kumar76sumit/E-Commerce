package com.example.ecommerce;

public class Customer {
    int Id;
    String Name,Email,Mobile;

    public Customer(int Id, String Name, String Email, String Mobile) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Mobile = Mobile;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobile() {
        return Mobile;
    }
}
