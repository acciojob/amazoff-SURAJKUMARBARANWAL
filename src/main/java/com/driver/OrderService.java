package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;
    public  String addOrder(Order order){
        return repository.addOrder(order);
    }
    public String addDeliveryPartner(String partner){
        return repository.addPartner(partner);
    }
    public String addOrderPartnerPair(String orderId,String partnerId){
        return repository.addOrderPartnerPair(orderId,partnerId);
    }
    public Order  getOrderById(String orderId){
        return repository.getOrderById(orderId);
    }
    public DeliveryPartner   getPartnerById(String partnerId){
        return repository.getPartnerById(partnerId);
    }
    public int getOrderCountByPartnerId(String partnerId){
        return repository.getOrderCountByPartnerId(partnerId);
    }
    //
    public List<Order> getOrdersByPartnerId(String partnerId){
        return repository.getOrdersByPartnerId(partnerId);
    }
    public List<Order> getAllOrders(){
        return repository.getAllOrders();
    }
    public  int getCountOfUnassignedOrders(){
        return repository.getCountOfUnassignedOrders();
    }
    public  int getOrdersLeftAfterGivenTimeByPartnerId(String partnerId,String T){
       return repository.getOrdersLeftAfterGivenTimeByPartnerId(partnerId,T);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
       return repository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public  String deletePartnerById(String  partnerId){
       return repository.deletePartnerById(partnerId);
    }
    public String deleteOrderById(String orderId){
       return repository.deleteOrderById(orderId);

    }


}
