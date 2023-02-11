package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    Map<String,Order> orderMap=new HashMap<>();
    Map<String,DeliveryPartner> deliveryPartnerMap=new HashMap<>();
    Map<Order,DeliveryPartner> orderDeliveryPartnerMap=new HashMap<>();

    Map<String, List<Order>> partnersTotalOrder=new HashMap<>();

    Set<String> notAssigned=new HashSet<>();


    public String addOrder(Order order){
        orderMap.put(order.getId(),order);
        notAssigned.add(order.getId());
        return  "Order added successfully";
    }
    public String  addPartner(String deliveryPartner){
        DeliveryPartner partner=new DeliveryPartner(deliveryPartner);
        deliveryPartnerMap.put(partner.getId(),partner);
        return "Delivery Partner added successfully";
    }

    public String addOrderPartnerPair(String orderId,String partnerId){
        notAssigned.remove(orderId);
        int x=deliveryPartnerMap.get(partnerId).getNumberOfOrders();
        deliveryPartnerMap.get(partnerId).setNumberOfOrders(x+1);
        orderDeliveryPartnerMap.put(orderMap.get(orderId),deliveryPartnerMap.get(partnerId));
        if(!partnersTotalOrder.containsKey(partnerId)){
            partnersTotalOrder.put(partnerId,new ArrayList<>());
        }
        partnersTotalOrder.get(partnerId).add(orderMap.get(orderId));

        return "Order assignment to partner";
    }
    public Order  getOrderById(String orderId){
        return orderMap.get(orderId);
    }
    public DeliveryPartner   getPartnerById(String partnerId){
        return deliveryPartnerMap.get(partnerId);
    }
    public int getOrderCountByPartnerId(String partnerId){
        return partnersTotalOrder.get(partnerId).size();
    }
    public List<Order> getOrdersByPartnerId(String partnerId){
        return partnersTotalOrder.get(partnerId);
    }
    public List<Order> getAllOrders(){
        return new ArrayList<>(orderMap.values());
    }
    public  int getCountOfUnassignedOrders(){
        return  notAssigned.size();
    }
    public  int getOrdersLeftAfterGivenTimeByPartnerId(String partnerId,String T){
        int time=Integer.parseInt(T.substring(0,2))*60+
                Integer.parseInt(T.substring(3,5));
        int count=0;
        for(Order order:partnersTotalOrder.get(partnerId)){
            int deliveryTime=order.getDeliveryTime();
            if(deliveryTime>time){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int  lastDeliveryTime=0;
        for(Order order:partnersTotalOrder.get(partnerId)){
            int deliveryTime=order.getDeliveryTime();
            if(deliveryTime>lastDeliveryTime){
                lastDeliveryTime=deliveryTime;
            }
        }
        int hh=lastDeliveryTime/60;
        int mm=lastDeliveryTime%60;
        String HH="";
        String MM="";
        if(hh<10) HH="0";
        if(mm<10) MM="0";
        HH=HH+hh;
        MM=MM+mm;
        return HH+":"+MM;
    }
    public  String deletePartnerById(String  partnerId){
         deliveryPartnerMap.remove(partnerId);
         for(Order order:partnersTotalOrder.get(partnerId)){
             orderDeliveryPartnerMap.remove(order);
             notAssigned.add(order.getId());
         }
         partnersTotalOrder.remove(partnerId);
         return "DeliveryPartner has been deleted successfully";
    }
    public String deleteOrderById(String orderId){
        if(notAssigned.contains(orderMap.get(orderId)))
        {
            notAssigned.remove(orderMap.get(orderId));
            return "Order has been deleted successfully";
        }

        DeliveryPartner temp=orderDeliveryPartnerMap.get(orderMap.get(orderId));
        partnersTotalOrder.get(temp.getId()).remove(orderMap.get(orderId));
        orderDeliveryPartnerMap.remove(orderMap.get(orderId));
        orderMap.remove(orderId);
        return "Order has been deleted successfully";

    }



}
