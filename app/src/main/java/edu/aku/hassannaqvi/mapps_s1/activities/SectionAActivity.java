package edu.aku.hassannaqvi.mapps_s1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class SectionAActivity extends Activity {


    @BindView(R.id.activity_section_a)
    ScrollView activitySectionA;
    @BindView(R.id.mps1a01)
    EditText mps1a01;
    @BindView(R.id.mps1a02)
    EditText mps1a02;
    @BindView(R.id.mps1a03)
    EditText mps1a03;
    @BindView(R.id.mps1a04)
    EditText mps1a04;
    @BindView(R.id.mps1a05)
    EditText mps1a05;
    @BindView(R.id.mps1a06)
    EditText mps1a06;
    @BindView(R.id.mps1a07)
    EditText mps1a07;
    @BindView(R.id.mps1a08)
    EditText mps1a08;
    @BindView(R.id.mps1a11)
    RadioGroup mps1a11;
    @BindView(R.id.mps1a1101)
    RadioButton mps1a1101;
    @BindView(R.id.mps1a1102)
    RadioButton mps1a1102;
    @BindView(R.id.fldGrpbtn)
    LinearLayout fldGrpbtn;
    @BindView(R.id.btnNext)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_a);
        ButterKnife.bind(this);

        mps1a11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mps1a1101.isChecked()) {
                    btnNext.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.GONE);
                }
            }
        });

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

                Intent secB = new Intent(this, SectionBActivity.class);
                startActivity(secB);


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

        JSONObject sa = new JSONObject();

        sa.put("mps1a01", mps1a01.getText().toString());
        sa.put("mps1a02", mps1a02.getText().toString());
        sa.put("mps1a03", mps1a03.getText().toString());
        sa.put("mps1a04", mps1a04.getText().toString());
        sa.put("mps1a07", mps1a07.getText().toString());
        sa.put("mps1a08", mps1a08.getText().toString());
        sa.put("mps1a11", mps1a1101.isChecked() ? "1" : mps1a1102.isChecked() ? "2" : "0");


        // setGPS();

        //   AppMain.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    public boolean ValidateForm() {

        // =================== mps1a01====================
        if (mps1a01.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a01), Toast.LENGTH_SHORT).show();
            mps1a01.setError("This data is required");
            Log.d(TAG, " mps1a01:empty ");
            return false;
        } else {
            mps1a01.setError(null);
        }

        if (Double.parseDouble(mps1a01.getText().toString()) == 0) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.mps1a01), Toast.LENGTH_SHORT).show();
            mps1a01.setError("Invalid: Data cannot be Zero");
            Log.i(TAG, "studyID: Invalid data is 0");
            return false;
        } else {
            mps1a01.setError(null);
        }

        // =================== mps1a02====================
        if (mps1a02.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a02), Toast.LENGTH_SHORT).show();
            mps1a02.setError("This data is required");
            Log.d(TAG, " mps1a02:empty ");
            return false;
        } else {
            mps1a02.setError(null);
        }

        if (Double.parseDouble(mps1a02.getText().toString()) == 0) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.mps1a02), Toast.LENGTH_SHORT).show();
            mps1a02.setError("Invalid: Data cannot be Zero");
            Log.i(TAG, "mps1a02: Invalid data is 0");
            return false;
        } else {
            mps1a02.setError(null);
        }

        // =================== mps1a03====================
        if (mps1a03.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a03), Toast.LENGTH_SHORT).show();
            mps1a03.setError("This data is required");
            Log.d(TAG, " mps1a03:empty ");
            return false;
        } else {
            mps1a03.setError(null);
        }

        // =================== mps1a04====================
        if (mps1a04.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a04), Toast.LENGTH_SHORT).show();
            mps1a04.setError("This data is required");
            Log.d(TAG, " mps1a04:empty ");
            return false;
        } else {
            mps1a04.setError(null);
        }


        // =================== mps1a07====================
        if (mps1a07.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a07), Toast.LENGTH_SHORT).show();
            mps1a07.setError("This data is required");
            Log.d(TAG, " mps1a07:empty ");
            return false;
        } else {
            mps1a07.setError(null);
        }

        // =================== mps1a08====================
        if (mps1a08.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a08), Toast.LENGTH_SHORT).show();
            mps1a08.setError("This data is required");
            Log.d(TAG, " mps1a08:empty ");
            return false;
        } else {
            mps1a08.setError(null);
        }

        if (Double.parseDouble(mps1a08.getText().toString()) == 0) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.mps1a08), Toast.LENGTH_SHORT).show();
            mps1a08.setError("Invalid: Data cannot be Zero");
            Log.i(TAG, "mps1a08: Invalid data is 0");
            return false;
        } else {
            mps1a08.setError(null);
        }

        //=================== mps1a11==============
        if (mps1a11.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a11), Toast.LENGTH_SHORT).show();
            mps1a1102.setError("This data is Required!");
            Log.i(TAG, "mps1a11: This Data is Required!");
            return false;
        } else {
            mps1a1102.setError(null);
        }



        return true;

    }


}
