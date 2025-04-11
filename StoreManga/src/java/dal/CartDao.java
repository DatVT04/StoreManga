/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Category;
import model.Product;
import model.Role;
import model.User;

/**
 *
 * @author VuxD4t
 */
public class CartDao extends DBContext {

    public List<Cart> getAllByUsername(String username) {
        List<Cart> carts = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE Username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    DaoCategory d = new DaoCategory();
                    UserDao ud = new UserDao();

                    User user = ud.getUserByUsername(username);
                    Product product = d.getProduct(rs.getInt("ProductID"));

                    Cart cart = new Cart();
                    cart.setCardId(rs.getInt("CartID"));
                    cart.setUser(user);
                    cart.setProduct(product);
                    cart.setQuantity(rs.getInt("Quantity"));
                    carts.add(cart);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return carts;
    }

    public void insert(Cart cart) {
        String sql = "INSERT INTO Cart (Username, ProductID, Quantity) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cart.getUser().getUsername());
            statement.setInt(2, cart.getProduct().getId());
            statement.setInt(3, cart.getQuantity());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int cartId) {
        String sql = "DELETE FROM Cart WHERE CartID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Cart getCartById(int cartId) {
        String sql = "SELECT * FROM Cart WHERE CartID = ?";
        Cart cart = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    DaoCategory d = new DaoCategory();
                    UserDao ud = new UserDao();

                    User user = ud.getUserByUsername(rs.getString("Username"));
                    Product product = d.getProduct(rs.getInt("ProductID"));

                    cart = new Cart();
                    cart.setCardId(rs.getInt("CartID"));
                    cart.setUser(user);
                    cart.setProduct(product);
                    cart.setQuantity(rs.getInt("Quantity"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cart;
    }

    public int countQuantityInCart(int productId, String name) {
        List<Cart> listc = new ArrayList<>();
        CartDao cd = new CartDao();
        listc = cd.getAllByUsername(name);

        int totalQuantity = 0;
        for (Cart cart : listc) {
            if (cart.getProduct().getId() == productId) {
                totalQuantity += cart.getQuantity();
            }
        }

        return totalQuantity;
    }

    public boolean checkUsernameExistsInCart(String username) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) AS Count FROM Cart WHERE Username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("Count");
                    exists = count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return exists;
    }

    public void addOrUpdateCartItem(String username, int productId, int quantity) {
        CartDao cartDao = new CartDao();
        List<Cart> cartList = cartDao.getAllByUsername(username);
        boolean itemExists = false;

        for (Cart cartItem : cartList) {
            if (cartItem.getProduct().getId() == productId) {
                itemExists = true;
                int newQuantity = cartItem.getQuantity() + quantity;
                updateCartItem(cartItem.getCardId(), newQuantity);
                break;
            }
        }

        if (!itemExists) {
            Cart newCartItem = new Cart();
            User user = new User();
            user.setUsername(username);
            newCartItem.setUser(user);
            Product product = new Product();
            product.setId(productId);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cartDao.insert(newCartItem);
        }
    }

    public void updateCartItem(int cartId, int newQuantity) {
        String sql = "UPDATE Cart SET Quantity = ? WHERE CartID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, cartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isProductInCart(String username, int productId) {
        String sql = "SELECT COUNT(*) AS Count FROM Cart WHERE Username = ? AND ProductID = ?";
        boolean exists = false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, productId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("Count");
                    exists = count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return exists;
    }
public void deleteCartItemByProductId(String username, int productId) {
    String sql = "DELETE FROM Cart WHERE Username = ? AND ProductID = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, username);
        statement.setInt(2, productId);
        statement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}

}
