package edu.aku.hassannaqvi.mapps_s1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();

    @BindView(R.id.activity_section_a)
    ScrollView activityEnding;
    @BindView(R.id.mps1a12)
    RadioGroup mps1a12;
    @BindView(R.id.mps1a1201)
    RadioButton mps1a1201;
    @BindView(R.id.mps1a1202)
    RadioButton mps1a1202;
    @BindView(R.id.mps1a1203)
    RadioButton mps1a1203;
    @BindView(R.id.mps1a1204)
    RadioButton mps1a1204;
    @BindView(R.id.mps1a1205)
    RadioButton mps1a1205;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        ButterKnife.bind(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            mps1a1201.setEnabled(true);
            mps1a1202.setEnabled(false);
            mps1a1203.setEnabled(false);
            mps1a1204.setEnabled(false);
            mps1a1205.setEnabled(false);
        } else {
            mps1a1201.setEnabled(false);
            mps1a1202.setEnabled(true);
            mps1a1203.setEnabled(true);
            mps1a1204.setEnabled(true);
            mps1a1205.setEnabled(true);
        }


    }

    @OnClick(R.id.btnEnd)
    void onBtnEndClick() {
        Toast.makeText(this, "Processing Closing Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //if (UpdateDB()) {
            finish();
            Toast.makeText(this, "Closing Form!", Toast.LENGTH_SHORT).show();
            Intent endSec = new Intent(this, MainActivity.class);
            //AppMain.mnb1 = "TEST";
            startActivity(endSec);
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
        //}
    }

    private boolean UpdateDB() {
        /*DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnd();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();*/
        return false;
        //}
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

        JSONObject sE = new JSONObject();

        sE.put("mps1a12", mps1a1201.isChecked() ? "1" : mps1a1202.isChecked() ? "2" : mps1a1203.isChecked() ? "3"
                : mps1a1204.isChecked() ? "4" : mps1a1205.isChecked() ? "5" : "0");

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating Closing Section", Toast.LENGTH_SHORT).show();
//=================== mps1b01 ==============
        if (mps1a12.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a12), Toast.LENGTH_SHORT).show();
            mps1a1205.setError("This data is Required!");
            Log.i(TAG, "mps1a12: This Data is Required!");
            return false;
        } else {
            mps1a1205.setError(null);
        }
        return true;
    }
}