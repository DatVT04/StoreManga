package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import model.User;

public class UserDao extends DBContext {

    public User check(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("UserID");
                String email = rs.getString("Email");
                Role role = getRoleById(rs.getInt("RoleID"));
                User user = new User(userID, rs.getString("Username"),
                        rs.getString("Password"), email, role);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Role getRoleById(int id) {
        String sql = "SELECT * FROM Roles WHERE RoleID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setRoleID(rs.getInt("RoleID"));
                    role.setRoleName(rs.getString("RoleName"));
                    return role;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(User user) {
        String sql = "INSERT INTO Users (Username, Password, Email, RoleID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getRole().getRoleID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE Username=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String email = rs.getString("Email");
                    Role role = getRoleById(rs.getInt("RoleID"));
                    return new User(userID, username, rs.getString("Password"), email, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String username = rs.getString("Username");
                    Role role = getRoleById(rs.getInt("RoleID"));
                    return new User(userID, username, rs.getString("Password"), email, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getRoleIdByRoleName(String roleName) {
        String sql = "SELECT RoleID FROM Roles WHERE RoleName=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, roleName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("RoleID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int userID = rs.getInt("UserID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                Role role = getRoleById(rs.getInt("RoleID"));
                User user = new User(userID, username, password, email, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

  
    public void deleteUser(int userID) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
  public void updateUser(User user) {
    String sql = "UPDATE Users SET Password = ?, Email = ?, RoleID = ? WHERE UserID = ?";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, user.getPassword());
        st.setString(2, user.getEmail());
        st.setInt(3, user.getRole().getRoleID());
        st.setInt(4, user.getUserID());
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public List<User> getUserById(int userID) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String email = rs.getString("Email");
                    Role role = getRoleById(rs.getInt("RoleID"));
                    userList.add(new User(userID, username, password, email, role));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUser(int userID) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String email = rs.getString("Email");
                    Role role = getRoleById(rs.getInt("RoleID"));
                    user = new User(userID, username, password, email, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
     public boolean checkPasswordById(String username, String password) {
        String sql = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePasswordById(String username, String newPassword) {
        String sql = "UPDATE Users SET Password = ? WHERE UserName = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, newPassword);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public int countAdmins(int roleID) {
        int adminCount = 0;
        String sql = "SELECT COUNT(*) AS admin_count FROM Users WHERE RoleID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roleID); 
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                adminCount = rs.getInt("admin_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminCount;
    }
}
