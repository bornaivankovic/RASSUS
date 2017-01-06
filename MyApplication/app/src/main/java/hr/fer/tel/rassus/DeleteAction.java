package hr.fer.tel.rassus;

import android.os.AsyncTask;

/**
 * Created by Borna Ivankovic on 14.12.2016..
 */

public class DeleteAction extends AsyncTask<String,Void,String> {

    public  interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public DeleteAction(AsyncResponse delegate){
        this.delegate=delegate;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpDeleteHandler handler = new HttpDeleteHandler(strings[0], strings[1], strings[2]);
        String json = handler.makeServiceCall();
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }
}
