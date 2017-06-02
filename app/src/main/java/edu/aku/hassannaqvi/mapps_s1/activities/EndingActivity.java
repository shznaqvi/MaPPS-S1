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

        //mna7a.setEnabled(getIntent().getBooleanExtra("complete", true));

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

        /*switch (mna7.getCheckedRadioButtonId()) {
            case R.id.mna7a:
                AppMain.fc.setiStatus("1");
                break;
            case R.id.mna7b:
                AppMain.fc.setiStatus("2");
                break;
            case R.id.mna7c:
                AppMain.fc.setiStatus("3");
                break;
            case R.id.mna7d:
                AppMain.fc.setiStatus("4");
                break;
            default:
                AppMain.fc.setiStatus("default");
                break;
        }*/

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating Closing Section", Toast.LENGTH_SHORT).show();
//=================== mps1b01 ==============
        if (mps1a12.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.mps1a12), Toast.LENGTH_SHORT).show();
            mps1a1203.setError("This data is Required!");
            Log.i(TAG, "mps1a12: This Data is Required!");
            return false;
        } else {
            mps1a1203.setError(null);
        }
        return true;
    }
}