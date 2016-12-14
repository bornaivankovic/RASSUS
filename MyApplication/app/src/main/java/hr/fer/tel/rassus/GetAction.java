package hr.fer.tel.rassus;

import android.os.AsyncTask;

/**
 * Created by Borna Ivankovic on 14.12.2016..
 */

public class GetAction extends AsyncTask<String,Void,String> {

    public  interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public GetAction(AsyncResponse delegate){
        this.delegate=delegate;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpHandler handler=new HttpHandler(strings[0]);
        String json=handler.makeServiceCall();
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }
}
