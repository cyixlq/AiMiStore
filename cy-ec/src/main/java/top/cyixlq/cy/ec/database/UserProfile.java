package top.cyixlq.cy.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user_profile")
public class UserProfile {
    private String token;
    private String username;
    private String image;
    private String phone;
    private String email;
    @Generated(hash = 1677407353)
    public UserProfile(String token, String username, String image, String phone,
            String email) {
        this.token = token;
        this.username = username;
        this.image = image;
        this.phone = phone;
        this.email = email;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
