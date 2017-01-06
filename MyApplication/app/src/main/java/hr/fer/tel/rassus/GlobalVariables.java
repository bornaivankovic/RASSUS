package hr.fer.tel.rassus;

import android.app.Application;

/**
 * Created by Fika on 6.1.2017..
 */

public class GlobalVariables extends Application {
    private String email;
    private String password;
    private boolean isAdmin;
    private String host;
    private String uri;

    public GlobalVariables() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
        this.uri = "http://" + host + "/api/v0.2/projects";
    }

    public String getUri() {
        return uri;
    }
}
