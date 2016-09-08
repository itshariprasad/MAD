package com.cs442.hravikum.sqlite;

/**
 * Created by Hari on 2/28/2016.
 */
import android.graphics.drawable.Drawable;


public class FoodMenuItem implements java.io.Serializable {
    String name=null;
    double Price=0;
    String Type=null;
    String Description =null;
    boolean selected = false;



    public FoodMenuItem(String name, double Price, String Type,String Description)
    {
        super();
        this.name=name;
        this.Price=Price;
        this.Type=Type;
        this.Description=Description;
        this.selected = false;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }
    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getType() {
        return Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }


    public String getDescription() {
        return Description;
    }
    public void setDescription(String Type) {
        this.Type = Description;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
