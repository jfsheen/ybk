package cc.aisc.ybk.content.model;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private Date createAt;

    private String status;

    private String enable;

    private String nickname;

    private Date expiredAt;

    public User(Integer id, String username, String password, Date createAt, String status, String enable, String nickname, Date expiredAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
        this.status = status;
        this.enable = enable;
        this.nickname = nickname;
        this.expiredAt = expiredAt;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public String getStatus() {
        return status;
    }

    public String getEnable() {
        return enable;
    }

    public String getNickname() {
        return nickname;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }
}