/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VuxD4t
 */
public class Cart {

    private int cardId;
    private User user;
    private Product product;
    private int quantity;
   

    public Cart() {
    }

    public Cart(int cardId, User user, Product product, int quantity) {
        this.cardId = cardId;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
      
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" + "cardId=" + cardId + ", user=" + user + ", product=" + product + ", quantity=" + quantity + '}';
    }

}
