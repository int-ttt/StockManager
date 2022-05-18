package net.intt.stock.server;


public class Users {
    private String id;
    private String password;

    public Users(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
