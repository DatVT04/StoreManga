/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VuxD4t
 */
public class Profile {

    private int profileID;
    private User user;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private String phone;
    private String adress;

    public Profile() {
    }

    public Profile(int profileID, User user, String firstName, String lastName, Boolean gender, String phone, String adress) {
        this.profileID = profileID;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.adress = adress;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    
    @Override
    public String toString() {
        return "Profile{" + "profileID=" + profileID + ", user=" + user + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + '}';
    }

}
