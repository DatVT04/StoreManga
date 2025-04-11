/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author VuxD4t
 */
public class Order {
     private int orderID;
    private Date date;
    private User user;
    private Double totalMoney;
    private boolean status;

    public Order() {
    }

    
    public Order(int orderID, Date date, User user, Double totalMoney, boolean status) {
        this.orderID = orderID;
        this.date = date;
        this.user = user;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

      public String getFormattedPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));      
        return formatter.format(totalMoney);
    }
         @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", date=" + date + ", user=" + user + ", totalMoney=" + totalMoney + ", status=" + status + '}';
    }


}
