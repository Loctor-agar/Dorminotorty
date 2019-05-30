package ng.com.obkm.bottomnavviewwithfragments.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("username")
    public String username;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("role")
    public String role;
    @SerializedName("accessToken")
    public String accessToken;
    @SerializedName("refreshToken")
    public String refreshToken;
    @SerializedName("accessTokenExp")
    public int accessTokenExp;
    @SerializedName("refreshTokenExp")
    public int refreshTokenExp;

    public UserResponse() {
    }

    public UserResponse(String username, String firstName, String lastName, String role, String accessToken, String refreshToken, int accessTokenExp, int refreshTokenExp) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExp = accessTokenExp;
        this.refreshTokenExp = refreshTokenExp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getAccessTokenExp() {
        return accessTokenExp;
    }

    public void setAccessTokenExp(int accessTokenExp) {
        this.accessTokenExp = accessTokenExp;
    }

    public int getRefreshTokenExp() {
        return refreshTokenExp;
    }

    public void setRefreshTokenExp(int refreshTokenExp) {
        this.refreshTokenExp = refreshTokenExp;
    }
}
