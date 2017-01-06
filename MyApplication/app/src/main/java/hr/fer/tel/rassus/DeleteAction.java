package hr.fer.tel.rassus;

import android.util.Base64;

/**
 * Created by Fika on 5.1.2017..
 */

public class DeleteAction {
    private String url;
    private String username;
    private String pass;
    private int id;

    public DeleteAction(String url, String username, String pass, int id) {
        this.url = url;
        this.username = username;
        this.pass = pass;
        this.id = id;
    }

    private String getB64Auth() {
        String source=username+":"+pass;
        String ret="Basic "+ Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
        return ret;
    }
}
