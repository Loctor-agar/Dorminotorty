package ng.com.obkm.bottomnavviewwithfragments.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("accessToken")
    public String accessToken;
    @SerializedName("refreshToken")
    public String refreshToken;

    public String getFirstName() {
        return firstName;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @SerializedName("role")
    public String role;

    public User() {
    }
    
    public User(String username, String firstName, String lastName, String accessToken, String refreshToken, String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
