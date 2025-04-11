/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author VuxD4t
 */
public class DaoCategory extends DBContext {

    public List< Category> getAll() {
        List< Category> list = new ArrayList<>();
        String sql = "select * from Categories";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Category c = new Category(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Category getCategoryById(int id) {

        String sql = "select * from Categories where id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

  public void insertCategory(Category category) {
    String sql = "INSERT INTO Categories (id, name, description) VALUES (?, ?, ?)";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, category.getId());
        st.setString(2, category.getName());
        st.setString(3, category.getDescribe());
        st.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}

    public void updateCategory(Category category) {
        String sql = "UPDATE Categories SET name = ?, description = ? WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category.getName());
            st.setString(2, category.getDescribe());
            st.setInt(3, category.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM Categories WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

     public boolean checkCategoryExistsByName(String name) {
        String sql = "SELECT COUNT(*) FROM Categories WHERE name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public int getLastCategoryId() {
        int lastCategoryId = 0;
        String sql = "SELECT TOP 1 id FROM Categories ORDER BY id DESC";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lastCategoryId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lastCategoryId + 1;
    }

    public List<Product> getProductById(int cid) {

        List<Product> list = new ArrayList<>();
        String sql = "select *  from products where 1=1 ";
        if (cid != 0) {
            sql += " and cid=" + cid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuanity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setReleaseDate(rs.getDate("releaseDate"));
                p.setDescribe(rs.getString("description"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public Product getProduct(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setQuanity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.setReleaseDate(rs.getDate("releaseDate"));
                product.setDescribe(rs.getString("description"));
                product.setImage(rs.getString("image"));
                Category category = getCategoryById(rs.getInt("cid"));
                product.setCategory(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setQuanity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getDouble("price"));
                product.setReleaseDate(resultSet.getDate("releaseDate"));
                product.setDescribe(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                Category category = getCategoryById(resultSet.getInt("cid"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }
    
    //home
    public List<Product> getHome() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 4 Image, Name, Description, Price\n"
                + "FROM Products\n"
                + "ORDER BY Quantity DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Product h = new Product();
                h.setImage(rs.getString("Image"));
                h.setName(rs.getString("Name"));
                h.setDescribe(rs.getString("Description"));
                h.setPrice(rs.getFloat("Price"));
                list.add(h);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product checkProductExists(String name, String description) {
        String sql = "SELECT * FROM Products WHERE name = ? OR description = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setQuanity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getDouble("price"));
                product.setReleaseDate(resultSet.getDate("releaseDate"));
                product.setDescribe(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                Category category = new Category();
                category.setId(resultSet.getInt("cid"));
                product.setCategory(category);
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> search(String key, int cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE 1=1 ";

        if (key != null) {
            sql += "AND (name LIKE ? OR description LIKE ?)";
        }
        if (cid != 0) {
            sql += " AND cid = ?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int parameterIndex = 1;
            if (key != null) {
                st.setString(parameterIndex++, "%" + key + "%");
                st.setString(parameterIndex++, "%" + key + "%");
            }
            if (cid != 0) {
                st.setInt(parameterIndex, cid);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuanity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setReleaseDate(rs.getDate("releaseDate"));
                p.setDescribe(rs.getString("description"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // CRUD products for Admin
    public void insertProduct(Product product) {
        String sql = "INSERT INTO Products (name, quantity, price, "
                + "releaseDate, description, image, cid) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setInt(2, product.getQuanity());
            st.setDouble(3, product.getPrice());
            st.setDate(4, new java.sql.Date(product.getReleaseDate().getTime()));
            st.setString(5, product.getDescribe());
            st.setString(6, product.getImage());
            st.setInt(7, product.getCategory().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET name = ?, "
                + "quantity = ?, price = ?, "
                + "releaseDate = ?, description = ?, "
                + "image = ?, cid = ? WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setInt(2, product.getQuanity());
            st.setDouble(3, product.getPrice());
            st.setDate(4, new java.sql.Date(product.getReleaseDate().getTime()));
            st.setString(5, product.getDescribe());
            st.setString(6, product.getImage());
            st.setInt(7, product.getCategory().getId());
            st.setInt(8, product.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Sắp xếp sản phẩm từ cao đến thấp
    public void sortDescending(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
    }

    // Sắp xếp sản phẩm từ thấp đến cao
    public void sortAscending(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
    }

    public List<Product> selectProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE price BETWEEN ? AND ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setQuanity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getDouble("price"));
                product.setReleaseDate(resultSet.getDate("releaseDate"));
                product.setDescribe(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                Category category = getCategoryById(resultSet.getInt("cid"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }

    public int countProductsByPriceRange(double minPrice, double maxPrice) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Products WHERE price BETWEEN ? AND ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return count;
    }
    
    
    
 public List<String> findProductsOutOfStock() {
    List<String> outOfStockProductNames = new ArrayList<>();
    String sql = "SELECT name FROM Products WHERE quantity <= 0";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String productName = resultSet.getString("name");
            outOfStockProductNames.add(productName);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return outOfStockProductNames;
}


}
