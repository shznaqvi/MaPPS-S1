package edu.aku.hassannaqvi.mapps_s1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.mapps_s1.R;
import edu.aku.hassannaqvi.mapps_s1.core.DatabaseHelper;

import static android.content.ContentValues.TAG;

public class SectionCActivity extends Activity {
    @BindView(R.id.activity_section_c)
    ScrollView activitySectionC;
    @BindView(R.id.mps1c0101)
    EditText mps1c0101;
    @BindView(R.id.mps1c0102)
    EditText mps1c0102;
    @BindView(R.id.mps1c0103)
    EditText mps1c0103;
    @BindView(R.id.mps1c0104)
    EditText mps1c0104;

    @BindView(R.id.mps1c0201)
    EditText mps1c0201;
    @BindView(R.id.fldGrpbtn)
    LinearLayout fldGrpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_c);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btnNext)
    void onBtnNextClick() {

        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();

                Intent endSec = new Intent(this, EndingActivity.class);
                startActivity(endSec);


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @OnClick(R.id.btnEnd)
    void onBtnEndClick() {
        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                Toast.makeText(this, "Starting Form Ending Section", Toast.LENGTH_SHORT).show();
                Intent endSec = new Intent(this, EndingActivity.class);
                endSec.putExtra("complete", false);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

       /* long updcount = db.addForm(AppMain.fc);

        AppMain.fc.setID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            AppMain.fc.setUID(
                    (AppMain.fc.getDeviceID() + AppMain.fc.getID()));
            db.updateFormID();

        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }*/

        return true;

    }

   /* public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

//        String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            AppMain.fc.setGpsLat(GPSPref.getString("Latitude", "0"));
            AppMain.fc.setGpsLng(GPSPref.getString("Longitude", "0"));
            AppMain.fc.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            AppMain.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            AppMain.fc.setGpsTime(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
         //   Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }*/

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

      /*  AppMain.VillageName = cravillage.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

       AppMain.fc = new FormsContract();

        AppMain.fc.setUserName(AppMain.username);
        AppMain.fc.setDeviceID(deviceId);
        AppMain.fc.setHhDT(dtToday);
        AppMain.fc.setTehsil(AppMain.tehsilCode);
        AppMain.fc.sethFacility(AppMain.hfCode);
        AppMain.fc.setLhwCode(AppMain.lhwCode);
        AppMain.fc.setUccode(getAllUCs.get(crauc.getSelectedItem().toString()));
        AppMain.fc.setVillagename(AppMain.VillageName);
        AppMain.fc.setChildId(cra03.getText().toString());
        AppMain.fc.setTagId(sharedPref.getString("tagName", ""));*/

        JSONObject sc = new JSONObject();

        sc.put("mps1c0101", mps1c0101.getText().toString());
        sc.put("mps1c0102", mps1c0102.getText().toString());
        sc.put("mps1c0103", mps1c0103.getText().toString());
        sc.put("mps1c0104", mps1c0104.getText().toString());
        sc.put("mps1c02", mps1c0201.getText().toString());


        // setGPS();

        //   AppMain.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }


    public boolean ValidateForm() {

        // =================== mps1c0101 ====================
        if (mps1c0101.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1c0101), Toast.LENGTH_SHORT).show();
            mps1c0101.setError("This data is required");
            Log.d(TAG, " mps1c0101 : empty ");
            return false;
        } else {
            mps1c0101.setError(null);
        }

        // =================== mps1c0102 ====================
        if (mps1c0102.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1c0102), Toast.LENGTH_SHORT).show();
            mps1c0102.setError("This data is required");
            Log.d(TAG, " mps1c0102 : empty ");
            return false;
        } else {
            mps1c0102.setError(null);
        }

        // =================== mps1c0103 ====================
        if (mps1c0103.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1c0103), Toast.LENGTH_SHORT).show();
            mps1c0103.setError("This data is required");
            Log.d(TAG, " mps1c0103 : empty ");
            return false;
        } else {
            mps1c0103.setError(null);
        }

        // =================== mps1c0104 ====================
        if (mps1c0104.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1c0104), Toast.LENGTH_SHORT).show();
            mps1c0104.setError("This data is required");
            Log.d(TAG, " mps1c0104 : empty ");
            return false;
        } else {
            mps1c0104.setError(null);
        }

        // =================== mps1c0201 ====================
        if (mps1c0201.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1c02), Toast.LENGTH_SHORT).show();
            mps1c0201.setError("This data is required");
            Log.d(TAG, " mps1c0201 : empty ");
            return false;
        } else {
            mps1c0201.setError(null);
            if (Double.parseDouble(mps1c0201.getText().toString()) < 0.0 || Double.parseDouble(mps1c0201.getText().toString()) > 99.9) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.mps1c02), Toast.LENGTH_SHORT).show();
                mps1c0201.setError("Invalid: Range 0.0 - 99.9");
                Log.i(TAG, "mps1c0201: Invalid Range 0.0 - 99.9");
                return false;
            } else {
                mps1c0201.setError(null);
            }
        }

        return true;

    }


}
