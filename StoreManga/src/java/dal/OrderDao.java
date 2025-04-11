/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Order;
import model.OrderDetail;
import model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author VuxD4t
 */
public class OrderDao extends DBContext {

    public void saveOrderDetail(OrderDetail od) {
        String sql = "INSERT INTO OrderDetails (orderid, productid, quantity, "
                + "unitprice) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, od.getOrder().getOrderID());
            st.setInt(2, od.getProductID());
            st.setInt(3, od.getQuantity());
            st.setDouble(4, od.getUnitPrice());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateOrderDetailQuantity(OrderDetail od) {
        String updateSql = "UPDATE OrderDetails SET quantity = ? WHERE orderid = ? AND productid = ?";
        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, od.getQuantity());
            updateStatement.setInt(2, od.getOrder().getOrderID());
            updateStatement.setInt(3, od.getProductID());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertOrder(Order order) {
        String sql = "INSERT INTO Orders (date, username, "
                + "totalmoney, status) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(order.getDate().getTime()));
            st.setString(2, order.getUser().getUsername());
            st.setDouble(3, order.getTotalMoney());
            st.setBoolean(4, order.isStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Integer getLastOrderId() {
        String sql = "SELECT TOP 1 OrderID FROM Orders ORDER BY OrderID DESC";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public void deleteOrderDetail(int orderId) {
        String sql = "DELETE FROM OrderDetails WHERE OrderDetailID= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isOrderTableEmpty() {
        boolean isEmpty = true;
        String sql = "SELECT COUNT(*) AS count FROM Orders";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                isEmpty = (count == 0);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return isEmpty;
    }

    //  CHECK PRODUCT, ACCOUNT, CATEGORY KHI CRUD
    public boolean checkProductExist(int productID) {
        String sql = "SELECT COUNT(*) AS count FROM OrderDetails WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");

                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public boolean checkUserExist(String username) {
        String sql = "SELECT COUNT(*) AS count FROM Orders WHERE UserName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public int countUsersOrderedProduct(int categoryId) {
        int userCount = 0;
        String sql = "SELECT COUNT(DISTINCT o.UserName) AS user_count "
                + "FROM Products p "
                + "JOIN Categories c ON p.cid = c.id "
                + "JOIN OrderDetails od ON p.id = od.ProductID "
                + "JOIN Orders o ON o.OrderID = od.OrderID "
                + "WHERE c.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userCount = resultSet.getInt("user_count");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userCount;
    }

    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE Username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                Date date = resultSet.getDate("Date");
                Double totalMoney = resultSet.getDouble("TotalMoney");
                boolean status = resultSet.getBoolean("Status");
                UserDao userDao = new UserDao();
                User user = userDao.getUserByUsername(username);

                Order order = new Order(orderId, date, user, totalMoney, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orders;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                Date date = resultSet.getDate("Date");
                Double totalMoney = resultSet.getDouble("TotalMoney");
                boolean status = resultSet.getBoolean("Status");
                UserDao userDao = new UserDao();
                User user = userDao.getUserByUsername(resultSet.getString("Username"));

                Order order = new Order(orderId, date, user, totalMoney, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orders;
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean checkOrderStatusById(int orderId) {
    String sql = "SELECT Status FROM Orders WHERE OrderID = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBoolean("Status");
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}

public void updateOrderStatus(int orderId) {
    String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, true);
        statement.setInt(2, orderId);
        statement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}

}
