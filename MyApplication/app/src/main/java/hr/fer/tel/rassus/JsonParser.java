package hr.fer.tel.rassus;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Borna Ivankovic on 14.12.2016..
 */

public class JsonParser {

    private JSONArray array;
    private JSONObject object;
    private String output;

    public JsonParser(String str){
        this.output = str;
        try {
            if(str.startsWith("["))
                this.array=new JSONArray(str);
            else
                this.object=new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<HashMap<String,String>> parse() {
        ArrayList<HashMap<String ,String >> res=new ArrayList<>();
        if(object!=null) {
            Iterator<String> keys = object.keys();
            HashMap<String, String> tmp = new HashMap<String, String>();
            try {
                while (keys.hasNext()) {
                    String key = keys.next();
                    tmp.put(key, object.getString(key));
                }
                res.add(tmp);
            } catch (final JSONException e) {
                Log.e(JsonParser.class.getSimpleName(), e.getMessage());
            }
        }
        else {
            try {
                for(int i=0;i<array.length();i++){
                    JSONObject o=array.getJSONObject(i);
                    Iterator<String> keys = o.keys();
                    HashMap<String, String> tmp = new HashMap<String, String>();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        tmp.put(key, o.getString(key));
                    }
                    res.add(tmp);
                }
            } catch (final JSONException e) {
                Log.e(JsonParser.class.getSimpleName(), e.getMessage());
            }
        }
        return res;
    }

    public ArrayList<String> JSONtoStringArray() {
        ArrayList<String> child = new ArrayList<String>();
        String tmp = "";
        for(HashMap<String, String> map : this.parse()) {
            tmp = "";
            for(String txt : map.keySet()) {
                tmp = tmp + txt + ":\n" + map.get(txt) + "\n\n";
            }
            child.add(tmp);
        }
        return child;
    }

    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<String>();
        for(HashMap<String, String> map : this.parse()) {
            titles.add(map.get("title"));
        }
        return titles;
    }

    public HashMap<String, String> getObject(int i) {
        return this.parse().get(i);
    }

    public String getOutput() {
        return output;
    }
}
