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
public class Product {

    private int id;
    private String name;
    private int quanity;
    private double price;
    private Date releaseDate;
    private String describe, image;
    private Category category;

    public Product(int id) {
        this.id = id;
    }

    public Product() {
    }

    public Product(int id, String name, int quanity, double price, Date releaseDate, String describe, String image, Category category) {
        this.id = id;
        this.name = name;
        this.quanity = quanity;
        this.price = price;
        this.releaseDate = releaseDate;
        this.describe = describe;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

   public String getFormattedPrice(double discountRate) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        double discountedPrice = price * (discountRate);
        return formatter.format(discountedPrice);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", quanity=" + quanity + ", price=" + price + ", releaseDate=" + releaseDate + ", describe=" + describe + ", image=" + image + ", category=" + category + '}';
    }
   
   
}
