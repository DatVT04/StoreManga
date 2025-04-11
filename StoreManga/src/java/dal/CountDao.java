/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author VuxD4t
 */
public class CountDao extends DBContext {

    public int getTotalUsers() {
        int totalUsers = 0;
        String sql = "SELECT COUNT(*) AS TotalUsers FROM Users";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalUsers = resultSet.getInt("TotalUsers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalUsers;
    }

    public int getTotalProducts() {
        int totalProducts = 0;
        String sql = "SELECT COUNT(*) AS TotalProducts FROM Products";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalProducts = resultSet.getInt("TotalProducts");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProducts;
    }

    public int getTotalCart() {
        int totalCart = 0;
        String sql = "SELECT COUNT(*) AS TotalItemsInCart FROM Cart";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalCart = resultSet.getInt("TotalItemsInCart");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCart;
    }

    public double TotalMoney() {
        double total = 0;
        String sql = "SELECT SUM(TotalMoney) AS TotalRevenue FROM Orders";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getDouble("TotalRevenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

 public int countProductsInCartByUsername(String username) {
    int count = 0;
    String sql = "SELECT COUNT(CartID) AS Count FROM Cart WHERE Username = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, username);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("Count");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }

    return count;
}
 
 
 public int getQuantityByProductId(int productId) {
    int quantity = 0;
    String sql = "SELECT quantity FROM Products WHERE id = ?";

    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            quantity = resultSet.getInt("quantity");
        }
    } catch (SQLException e) {
        System.out.println(e);
    }

    return quantity;
}
   public int countProductsInCartByCategoryId(int categoryId) {
    int count = 0;
    String sql = "SELECT COUNT(*) AS TotalProductsInCart "
               + "FROM Products p "
               + "JOIN Cart c ON p.id = c.ProductID "
               + "WHERE p.cid = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, categoryId);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("TotalProductsInCart");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
}
   public double getTotalMoneyByStatus(boolean status) {
    double totalMoney = 0;
    String sql = "SELECT SUM(TotalMoney) AS TotalRevenue FROM Orders WHERE Status = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setBoolean(1, status);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            totalMoney = resultSet.getDouble("TotalRevenue");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalMoney;
}

   
}
