package com.cs442.hravikum.sqlite;

/**
 * Created by Hari on 2/28/2016.
 */
import java.sql.Timestamp;
import java.util.Date;


public class Order {
    public Integer id;
    public String name;
    public Double price;
    public Timestamp timestamp;

    public Order(Integer id,String name,Double price,Timestamp timestamp)
    {
        super();
        this.id=id;
        this.name=name;
        this.price=price;
        this.timestamp=timestamp;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price=price;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp=timestamp;
    }
}
