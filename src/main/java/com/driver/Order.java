package com.driver;

public class Order {

    private String id;
    private String deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        this.deliveryTime= deliveryTime;
    }

    public String getId() {
        return id;
    }

    public String getDeliveryTime() {return deliveryTime;}

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
