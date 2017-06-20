package edu.aku.hassannaqvi.mapps_s1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
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
import edu.aku.hassannaqvi.mapps_s1.contracts.FormsContract;
import edu.aku.hassannaqvi.mapps_s1.core.AppMain;
import edu.aku.hassannaqvi.mapps_s1.core.DatabaseHelper;

import static android.content.ContentValues.TAG;

public class SectionBActivity extends Activity {

    @BindView(R.id.activity_section_b)
    ScrollView activitySectionB;
    @BindView(R.id.mps1b01)
    RadioGroup mps1b01;
    @BindView(R.id.mps1b0101)
    RadioButton mps1b0101;
    @BindView(R.id.mps1b0102)
    RadioButton mps1b0102;
    @BindView(R.id.mps1b0103)
    RadioButton mps1b0103;
    @BindView(R.id.fldGrpmps1b01)
    LinearLayout fldGrpmps1b01;
    @BindView(R.id.mps1b02)
    RadioGroup mps1b02;
    @BindView(R.id.mps1b0201)
    RadioButton mps1b0201;
    @BindView(R.id.mps1b0202)
    RadioButton mps1b0202;
    @BindView(R.id.mps1b0203)
    RadioButton mps1b0203;
    @BindView(R.id.mps1b03)
    RadioGroup mps1b03;
    @BindView(R.id.mps1b0301)
    RadioButton mps1b0301;
    @BindView(R.id.mps1b0302)
    RadioButton mps1b0302;
    @BindView(R.id.fldGrpmps1b02)
    LinearLayout fldGrpmps1b02;
    @BindView(R.id.mps1b04)
    EditText mps1b04;
    @BindView(R.id.fldGrpbtn)
    LinearLayout fldGrpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_b);
        ButterKnife.bind(this);

        ///================ Q1 Skip pattern================

        mps1b01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mps1b0101.isChecked()) {
                    fldGrpmps1b01.setVisibility(View.VISIBLE);

                } else {
                    fldGrpmps1b01.setVisibility(View.GONE);
                    mps1b02.clearCheck();

                }
            }
        });

        ///================ Q3 Skip pattern================

        mps1b03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mps1b0301.isChecked()) {
                    fldGrpmps1b02.setVisibility(View.VISIBLE);

                } else {
                    fldGrpmps1b02.setVisibility(View.GONE);
                    mps1b04.setText(null);
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

                Intent secC = new Intent(this, SectionCActivity.class);
                startActivity(secC);


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

        //Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);

        // 1. INSERT FORM
        //rowId = db.addForm(AppMain.fc);

        //AppMain.fc.setID(String.valueOf(rowId));

        //if (rowId != null) {

        /*AppMain.fc.setUID(
                (AppMain.fc.getDeviceID() + AppMain.fc.getID()));*/
        //Toast.makeText(this, "Current Form No: " + AppMain.fc.getUID(), Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSB();


        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

        /*} else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

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

        JSONObject sb = new JSONObject();

        sb.put("mps1b01", mps1b0101.isChecked() ? "1" : mps1b0102.isChecked() ? "2" : mps1b0103.isChecked() ? "3" : "0");
        sb.put("mps1b02", mps1b0201.isChecked() ? "1" : mps1b0202.isChecked() ? "2" : mps1b0203.isChecked() ? "3" : "0");
        sb.put("mps1b03", mps1b0301.isChecked() ? "1" : mps1b0302.isChecked() ? "2" : "0");
        sb.put("mps1b04", mps1b04.getText().toString());

        AppMain.fc.setsB(String.valueOf(sb));


        // setGPS();

        //   AppMain.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    public boolean ValidateForm() {

        //=================== mps1b01 ==============
        if (mps1b01.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1b01), Toast.LENGTH_SHORT).show();
            mps1b0103.setError("This data is Required!");
            Log.i(TAG, "mps1b01: This Data is Required!");
            return false;
        } else {
            mps1b0103.setError(null);
        }

        if (mps1b0101.isChecked()) {

            //=================== mps1b02 ==============
            if (mps1b02.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1b02), Toast.LENGTH_SHORT).show();
                mps1b0203.setError("This data is Required!");
                Log.i(TAG, "mps1b02: This Data is Required!");
                return false;
            } else {
                mps1b0203.setError(null);
            }
        }
        if (mps1b03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1b03), Toast.LENGTH_SHORT).show();
            mps1b0302.setError("This data is Required!");
            Log.i(TAG, "mps1b03: This Data is Required!");
            return false;
        } else {
            mps1b0302.setError(null);
        }

        if (mps1b0301.isChecked()) {
            // =================== mps1b04 ====================
            if (mps1b04.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1b04), Toast.LENGTH_SHORT).show();
                mps1b04.setError("This data is required");
                Log.d(TAG, " mps1b04 : empty ");
                return false;
            } else {
                mps1b04.setError(null);
            }

        }


        return true;
    }


}

