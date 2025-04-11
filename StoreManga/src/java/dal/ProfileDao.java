package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Profile;
import model.User;

public class ProfileDao extends DBContext {

    public Profile getProfileByUsername(String username) {
        String sql = "SELECT * FROM Profile WHERE Username=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Profile profile = new Profile();
                profile.setProfileID(rs.getInt("ProfileID"));
                profile.setFirstName(rs.getString("FirstName"));
                profile.setLastName(rs.getString("LastName"));
                profile.setGender(rs.getBoolean("Gender"));
                profile.setPhone(rs.getString("PhoneNumber")); 
                profile.setAdress(rs.getString("Address")); 

                User user = new User();
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                profile.setUser(user);
                return profile;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean profileExists(Profile profile) {
        String sql = "SELECT COUNT(*) FROM Profile WHERE FirstName=? AND LastName=? AND Gender=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, profile.getFirstName());
            st.setString(2, profile.getLastName());
            st.setBoolean(3, profile.getGender());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void insertProfile(Profile profile) {
        String sql = "INSERT INTO Profile (Username, FirstName, LastName, Email, "
                + "Gender, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, profile.getUser().getUsername());
            st.setString(2, profile.getFirstName());
            st.setString(3, profile.getLastName());
            st.setString(4, profile.getUser().getEmail());
            st.setBoolean(5, profile.getGender());
            st.setString(6, profile.getPhone()); 
            st.setString(7, profile.getAdress()); 
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProfile(Profile profile) {
        String sql = "UPDATE Profile SET FirstName=?, LastName=?, Gender=?, Email=?,"
                + " PhoneNumber=?, Address=? WHERE ProfileID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, profile.getFirstName());
            st.setString(2, profile.getLastName());
            st.setBoolean(3, profile.getGender());
            st.setString(4, profile.getUser().getEmail());
            st.setString(5, profile.getPhone()); 
            st.setString(6, profile.getAdress()); 
            st.setInt(7, profile.getProfileID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void deleteOrderById(int orderId) {
    String sql = "DELETE FROM Orders WHERE OrderID = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderId);
        statement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}
}
