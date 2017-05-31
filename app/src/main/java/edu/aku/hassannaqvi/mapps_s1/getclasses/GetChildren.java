package edu.aku.hassannaqvi.mapps_s1.getclasses;

/**
 * Created by hassan.naqvi on 11/5/2016.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import edu.aku.hassannaqvi.mapps_s1.contracts.PSUsContract;
import edu.aku.hassannaqvi.mapps_s1.core.AppMain;
import edu.aku.hassannaqvi.mapps_s1.core.DatabaseHelper;

/**
 * Created by hassan.naqvi on 4/28/2016.
 */
public class GetChildren extends AsyncTask<String, String, String> {

    private final String TAG = "GetChildren()";
    HttpURLConnection urlConnection;
    private Context mContext;
    private ProgressDialog pd;

    public GetChildren(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(mContext);
        pd.setTitle("Syncing Children");
        pd.setMessage("Getting connected to server...");
        pd.show();

    }

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(AppMain.PROJECT_URI + "leap/api/children.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestProperty("Accept-Encoding", "gzip");


            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                Log.i(TAG, "Children In: " + line);
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();


        } finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {

        //Do something with the JSON string

        String json = result;
        //json = json.replaceAll("\\[", "").replaceAll("\\]","");
        Log.d(TAG, result);
        if (json.length() > 0) {
            ArrayList<PSUsContract> psuArrayList;
            DatabaseHelper db = new DatabaseHelper(mContext);
            try {
                psuArrayList = new ArrayList<PSUsContract>();
                //JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = new JSONArray(json);
                db.syncChild(jsonArray);
                pd.setMessage("Received: " + jsonArray.length());
                pd.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            db.getAllChildren();
        } else {
            pd.setMessage("Received: " + json.length() + "");
            pd.show();
        }
    }


/*        try {
            JSONObject obj = new JSONObject(json);
            Log.d("My App", obj.toString());
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
        }*/

//        ArrayList<String> listdata = new ArrayList<String>();
//        JSONArray jArray = (JSONArray)jsonObject;
//        if (jArray != null) {
//            for (int i=0;i<jArray.length();i++){
//                listdata.add(jArray.get(i).toString());
//            }
//        }

}