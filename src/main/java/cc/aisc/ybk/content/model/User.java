package cc.aisc.ybk.content.model;

public class User {
    private Long id;

    private Integer version;

    private String email;

    private String mobile;

    private String name;

    private String password;

    private String username;

    public User(Long id, Integer version, String email, String mobile, String name, String password, String username) {
        this.id = id;
        this.version = version;
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}