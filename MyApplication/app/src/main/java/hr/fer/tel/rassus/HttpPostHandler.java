package hr.fer.tel.rassus;

import android.util.Base64;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Fika on 6.1.2017..
 */

public class HttpPostHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();


    private static String url;
    private static String email;
    private static String password;
    private static String stringObject;

    public HttpPostHandler() {
    }

    public HttpPostHandler(String url, String email, String password, String stringObject){
        this.url = url;
        this.email = email;
        this.password = password;
        this.stringObject = stringObject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            String userpass = email + ":" + password;
            String basicAuth = "Basic " + Base64.encodeToString(userpass.getBytes(), 0);
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty ("Authorization", basicAuth);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("POST");
            String str = stringObject;
            byte[] outputInBytes = str.getBytes("UTF-8");
            conn.getOutputStream().write(outputInBytes);
            // read the response
            try {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            }
            finally {
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public String makeServiceCall(){
        return makeServiceCall(this.url);
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
