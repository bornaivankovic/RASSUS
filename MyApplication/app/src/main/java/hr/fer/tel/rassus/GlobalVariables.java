package hr.fer.tel.rassus;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Fika on 6.1.2017..
 */

public class GlobalVariables extends Application {
    private String email;
    private String password;
    private boolean isAdmin;
    private JSONArray array;
    private JSONObject object;

    public GlobalVariables() {
        this.email = "admin";
        this.password = "lozinka";
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

    public JSONArray getArray() {
        return array;
    }

    public void setArray(JSONArray array) {
        this.array = array;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }
}
