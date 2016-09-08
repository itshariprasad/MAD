package com.cs442.hravikum.assignment2;

public class Hotel
{

    private String name;
    private String price;
    private String veg_nonv;
    private int iconId;

    public Hotel(String name,String price,String veg_nonv,int iconId)
    {
        this.name = name;
        this.price = price;
        this.veg_nonv = veg_nonv;
        this.iconId = iconId;
    }
    public String getName()
    {
        return name;
    }
    public String getPrice()
    {
        return price;
    }
    public String getVeg_nonv()
    {
        return veg_nonv;
    }

    public int getIconId()
    {
        return iconId;
    }
}
