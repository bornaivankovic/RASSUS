package hr.fer.tel.rassus;

import android.os.AsyncTask;

/**
 * Created by Fika on 6.1.2017..
 */

public class PostAction extends AsyncTask<String,Void,String> {

    public  interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public PostAction(AsyncResponse delegate){
        this.delegate=delegate;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpPostHandler handler=new HttpPostHandler(strings[0], strings[1], strings[2], strings[3]);
        String json=handler.makeServiceCall();
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }
}
