package edu.aku.hassannaqvi.mapps_s1.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import edu.aku.hassannaqvi.mapps_s1.contracts.FormsContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.FormsContract.singleForm;
import edu.aku.hassannaqvi.mapps_s1.contracts.HFacilitiesContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.HFacilitiesContract.HFacilityTable;
import edu.aku.hassannaqvi.mapps_s1.contracts.LHWsContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.LHWsContract.LHWTable;
import edu.aku.hassannaqvi.mapps_s1.contracts.PSUsContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.PSUsContract.singleChild;
import edu.aku.hassannaqvi.mapps_s1.contracts.SourceNGOContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.SourceNGOContract.SourceTable;
import edu.aku.hassannaqvi.mapps_s1.contracts.TehsilsContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.TehsilsContract.TehsilTable;
import edu.aku.hassannaqvi.mapps_s1.contracts.UCsContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.UCsContract.UcTable;
import edu.aku.hassannaqvi.mapps_s1.contracts.UsersContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.UsersContract.singleUser;
import edu.aku.hassannaqvi.mapps_s1.contracts.VillagesContract;
import edu.aku.hassannaqvi.mapps_s1.contracts.VillagesContract.VillageTable;

/**
 * Created by hassan.naqvi on 10/29/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String SQL_CREATE_PSU = "CREATE TABLE " + singleChild.TABLE_NAME + "("
            + singleChild._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleChild.COLUMN_PSU + " TEXT,"
            + singleChild.COLUMN_LUID + " TEXT,"
            + singleChild.COLUMN_HH + " TEXT,"
            + singleChild.COLUMN_HH03 + " TEXT,"
            + singleChild.COLUMN_HH07 + " TEXT,"
            + singleChild.COLUMN_CHILD_NAME + " TEXT );";
    public static final String SQL_CREATE_USERS = "CREATE TABLE " + singleUser.TABLE_NAME + "("
            + singleUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleUser.ROW_USERNAME + " TEXT,"
            + singleUser.ROW_PASSWORD + " TEXT );";
    public static final String DATABASE_NAME = "mappss1.db";
    public static final String DB_NAME = "mappss1_copy.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + singleForm.TABLE_NAME + "("
            + singleForm.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            singleForm.COLUMN_UID + " TEXT," +
            singleForm.COLUMN_HHDT + " TEXT," +
            singleForm.COLUMN_TEHSIL + " TEXT," +
            singleForm.COLUMN_HFACILITY + " TEXT," +
            singleForm.COLUMN_LHWCODE + " TEXT," +
            singleForm.COLUMN_HOUSEHOLD + " TEXT," +
            singleForm.COLUMN_CHILDID + " TEXT," +
            singleForm.COLUMN_UCCODE + " TEXT," +
            singleForm.COLUMN_VILLAGENAME + " TEXT," +
            singleForm.COLUMN_ISTATUS + " TEXT," +
            singleForm.COLUMN_NAME_USERNAME + " TEXT," +
            singleForm.COLUMN_DEVICETAGID + " TEXT," +
            singleForm.COLUMN_SA + " TEXT," +
            singleForm.COLUMN_SB + " TEXT," +
            singleForm.COLUMN_SC + " TEXT," +
            singleForm.COLUMN_SD + " TEXT," +
            singleForm.COLUMN_SE + " TEXT," +
            singleForm.COLUMN_SF + " TEXT," +
            singleForm.COLUMN_SG + " TEXT," +
            singleForm.COLUMN_SH + " TEXT," +
            singleForm.COLUMN_SI + " TEXT," +
            singleForm.COLUMN_SJ + " TEXT," +
            singleForm.COLUMN_SK + " TEXT," +
            singleForm.COLUMN_SL + " TEXT," +
            singleForm.COLUMN_SM + " TEXT," +
            singleForm.COLUMN_SN + " TEXT," +
            singleForm.COLUMN_GPSLAT + " TEXT," +
            singleForm.COLUMN_GPSLNG + " TEXT," +
            singleForm.COLUMN_GPSTIME + " TEXT," +
            singleForm.COLUMN_GPSACC + " TEXT," +
            singleForm.COLUMN_DEVICEID + " TEXT," +
            singleForm.COLUMN_SYNCED + " TEXT," +
            singleForm.COLUMN_SYNCED_DATE + " TEXT"
            + " );";
    private static final String SQL_DELETE_FORMS = "DROP TABLE IF EXISTS " + singleForm.TABLE_NAME;
    private static final String SQL_DELETE_USERS = "DROP TABLE IF EXISTS " + singleUser.TABLE_NAME;
    private static final String SQL_DELETE_PSUS = "DROP TABLE IF EXISTS " + singleChild.TABLE_NAME;
    public static String DB_FORM_ID;
    public static String DB_IMS_ID;
    final String SQL_CREATE_TEHSIL_TABLE = "CREATE TABLE " + TehsilTable.TABLE_NAME + " (" +
            TehsilTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TehsilTable.COLUMN_TEHSIL_CODE + " TEXT, " +
            TehsilTable.COLUMN_TEHSIL_NAME + " TEXT " +
            ");";
    final String SQL_CREATE_H_FACILIY_TABLE = "CREATE TABLE " + HFacilityTable.TABLE_NAME + " (" +
            HFacilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            HFacilityTable.COLUMN_HFACILITY_CODE + " TEXT, " +
            HFacilityTable.COLUMN_TEHSIL_CODE + " TEXT, " +
            HFacilityTable.COLUMN_HFACILITY_NAME + " TEXT " +
            ");";
    final String SQL_CREATE_UC_TABLE = "CREATE TABLE " + UcTable.TABLE_NAME + " (" +
            UcTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            UcTable.COLUMN_TEHSIL_CODE + " TEXT, " +
            UcTable.COLUMN_UC_NAME + " TEXT, " +
            UcTable.COLUMN_UC_CODE + " TEXT " +
            ");";
    final String SQL_CREATE_SOURCE_TABLE = "CREATE TABLE " + SourceTable.TABLE_NAME + " (" +
            SourceTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SourceTable.COLUMN_SOURCE_CODE + " TEXT, " +
            SourceTable.COLUMN_SOURCE_NAME + " TEXT " +
            ");";
    final String SQL_CREATE_VILLAGE_TABLE = "CREATE TABLE " + VillageTable.TABLE_NAME + " (" +
            VillageTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VillageTable.COLUMN_VILLAGE_CODE + " TEXT, " +
            VillageTable.COLUMN_VILLAGE_NAME + " TEXT, " +
            VillageTable.COLUMN_UC_CODE + " TEXT " +
            ");";
    final String SQL_CREATE_LHW_TABLE = "CREATE TABLE " + LHWTable.TABLE_NAME + " (" +
            LHWTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            LHWTable.COLUMN_LHW_CODE + " TEXT, " +
            LHWTable.COLUMN_LHW_NAME + " TEXT, " +
            LHWTable.COLUMN_AREA_TYPE + " TEXT, " +
            LHWTable.COLUMN_STATUS + " TEXT, " +
            LHWTable.COLUMN_HF_CODE + " TEXT " +
            ");";
    private final String TAG = "DatabaseHelper";
    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_PSU);
        db.execSQL(SQL_CREATE_TEHSIL_TABLE);
        db.execSQL(SQL_CREATE_UC_TABLE);
        db.execSQL(SQL_CREATE_VILLAGE_TABLE);
        db.execSQL(SQL_CREATE_H_FACILIY_TABLE);
        db.execSQL(SQL_CREATE_LHW_TABLE);
        db.execSQL(SQL_CREATE_SOURCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_PSUS);

        db.execSQL(TehsilTable.TABLE_NAME);
        db.execSQL(UcTable.TABLE_NAME);
        db.execSQL(VillageTable.TABLE_NAME);
        db.execSQL(HFacilityTable.TABLE_NAME);
        db.execSQL(LHWTable.TABLE_NAME);
        db.execSQL(SourceTable.TABLE_NAME);

        onCreate(db);
    }

    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_HHDT, fc.getHhDT());
        values.put(singleForm.COLUMN_TEHSIL, fc.getTehsil());
        values.put(singleForm.COLUMN_HFACILITY, fc.gethFacility());
        values.put(singleForm.COLUMN_LHWCODE, fc.getLhwCode());
        values.put(singleForm.COLUMN_HOUSEHOLD, fc.getHouseHold());
        values.put(singleForm.COLUMN_CHILDID, fc.getChildId());
        values.put(singleForm.COLUMN_UCCODE, fc.getUccode());
        values.put(singleForm.COLUMN_VILLAGENAME, fc.getVillagename());
        values.put(singleForm.COLUMN_ISTATUS, fc.getiStatus());
        values.put(singleForm.COLUMN_NAME_USERNAME, fc.getUserName());
        values.put(singleForm.COLUMN_DEVICETAGID, fc.getTagId());
        values.put(singleForm.COLUMN_SA, fc.getsA());
        values.put(singleForm.COLUMN_SB, fc.getsB());
        values.put(singleForm.COLUMN_SC, fc.getsC());
        values.put(singleForm.COLUMN_SD, fc.getsD());
        values.put(singleForm.COLUMN_SE, fc.getsE());
        values.put(singleForm.COLUMN_SF, fc.getsF());
        values.put(singleForm.COLUMN_SG, fc.getsG());
        values.put(singleForm.COLUMN_SH, fc.getsH());
        values.put(singleForm.COLUMN_SI, fc.getsI());
        values.put(singleForm.COLUMN_SJ, fc.getsJ());
        values.put(singleForm.COLUMN_SK, fc.getsK());
        values.put(singleForm.COLUMN_SL, fc.getsL());
        values.put(singleForm.COLUMN_SM, fc.getsM());
        values.put(singleForm.COLUMN_SN, fc.getsN());
        values.put(singleForm.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(singleForm.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(singleForm.COLUMN_GPSTIME, fc.getGpsTime());
        values.put(singleForm.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(singleForm.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(singleForm.COLUMN_SYNCED, fc.getSynced());
        values.put(singleForm.COLUMN_SYNCED_DATE, fc.getSynced_date());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                singleForm.TABLE_NAME,
                singleForm.COLUMN_NAME_NULLABLE,
                values);
        DB_FORM_ID = String.valueOf(newRowId);
        return newRowId;
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_UID, AppMain.fc.getUID());

// Which row to update, based on the ID
        String selection = singleForm._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public void updateForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SYNCED, true);
        values.put(singleForm.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = singleForm._ID + " LIKE ?";
        String[] whereArgs = {id};

        int count = db.update(
                singleForm.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public Collection<FormsContract> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleForm.COLUMN_ID,
                singleForm.COLUMN_UID,
                singleForm.COLUMN_HHDT,
                singleForm.COLUMN_TEHSIL,
                singleForm.COLUMN_HFACILITY,
                singleForm.COLUMN_LHWCODE,
                singleForm.COLUMN_HOUSEHOLD,
                singleForm.COLUMN_CHILDID,
                singleForm.COLUMN_UCCODE,
                singleForm.COLUMN_VILLAGENAME,
                singleForm.COLUMN_ISTATUS,
                singleForm.COLUMN_NAME_USERNAME,
                singleForm.COLUMN_DEVICETAGID,
                singleForm.COLUMN_SA,
                singleForm.COLUMN_SB,
                singleForm.COLUMN_SC,
                singleForm.COLUMN_SD,
                singleForm.COLUMN_SE,
                singleForm.COLUMN_SF,
                singleForm.COLUMN_SG,
                singleForm.COLUMN_SH,
                singleForm.COLUMN_SI,
                singleForm.COLUMN_SJ,
                singleForm.COLUMN_SK,
                singleForm.COLUMN_SL,
                singleForm.COLUMN_SM,
                singleForm.COLUMN_SN,
                singleForm.COLUMN_GPSLAT,
                singleForm.COLUMN_GPSLNG,
                singleForm.COLUMN_GPSTIME,
                singleForm.COLUMN_GPSACC,
                singleForm.COLUMN_DEVICEID,
                singleForm.COLUMN_SYNCED,
                singleForm.COLUMN_SYNCED_DATE
        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleForm._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    singleForm.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleForm.COLUMN_ID,
                singleForm.COLUMN_UID,
                singleForm.COLUMN_HHDT,
                singleForm.COLUMN_TEHSIL,
                singleForm.COLUMN_HFACILITY,
                singleForm.COLUMN_LHWCODE,
                singleForm.COLUMN_HOUSEHOLD,
                singleForm.COLUMN_CHILDID,
                singleForm.COLUMN_UCCODE,
                singleForm.COLUMN_VILLAGENAME,
                singleForm.COLUMN_ISTATUS,
                singleForm.COLUMN_NAME_USERNAME,
                singleForm.COLUMN_DEVICETAGID,
                singleForm.COLUMN_SA,
                singleForm.COLUMN_SB,
                singleForm.COLUMN_SC,
                singleForm.COLUMN_SD,
                singleForm.COLUMN_SE,
                singleForm.COLUMN_SF,
                singleForm.COLUMN_SG,
                singleForm.COLUMN_SH,
                singleForm.COLUMN_SI,
                singleForm.COLUMN_SJ,
                singleForm.COLUMN_SK,
                singleForm.COLUMN_SL,
                singleForm.COLUMN_SM,
                singleForm.COLUMN_SN,
                singleForm.COLUMN_GPSLAT,
                singleForm.COLUMN_GPSLNG,
                singleForm.COLUMN_GPSTIME,
                singleForm.COLUMN_GPSACC,
                singleForm.COLUMN_DEVICEID,
                singleForm.COLUMN_SYNCED,
                singleForm.COLUMN_SYNCED_DATE
        };
        String whereClause = singleForm.COLUMN_SYNCED + " is null OR " + singleForm.COLUMN_SYNCED + " = ''";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleForm._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    singleForm.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getTodayForms() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleForm._ID
        };

        String whereClause = singleForm.COLUMN_HHDT + " LIKE ?";
        String[] whereArgs = {spDateT};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleForm._ID + " ASC";

        Collection<FormsContract> formList = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    singleForm.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                formList.add(fc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }


        // return contact list
        return formList;
    }


    public int updateSB() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SB, AppMain.fc.getsB());
        values.put(singleForm.COLUMN_UID, AppMain.fc.getUID());


// Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSC() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SC, AppMain.fc.getsC());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSD() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SD, AppMain.fc.getsD());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSE() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SE, AppMain.fc.getsE());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSF() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SF, AppMain.fc.getsF());

        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSG() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SG, AppMain.fc.getsG());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSH() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SH, AppMain.fc.getsH());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSI() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SI, AppMain.fc.getsI());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSJ() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SJ, AppMain.fc.getsJ());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSK() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SK, AppMain.fc.getsK());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSL() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SL, AppMain.fc.getsL());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSM() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SM, AppMain.fc.getsM());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSN() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(singleForm.COLUMN_SN, AppMain.fc.getsN());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateEnd() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
//        values.put(singleForm.COLUMN_MNA7, AppMain.fc.getiStatus());


        // Which row to update, based on the ID
        String selection = singleForm._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.getID())};

        int count = db.update(singleForm.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public void addUser(UsersContract userscontract) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();

            values.put(singleUser.ROW_USERNAME, userscontract.getUserName());
            values.put(singleUser.ROW_PASSWORD, userscontract.getPassword());
            db.insert(singleUser.TABLE_NAME, null, values);
            db.close();

        } catch (Exception e) {
        }
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersContract.singleUser.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                String userName = jsonObjectUser.getString("username");
                String password = jsonObjectUser.getString("password");


                ContentValues values = new ContentValues();

                values.put(singleUser.ROW_USERNAME, userName);
                values.put(singleUser.ROW_PASSWORD, password);
                db.insert(singleUser.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        }
    }

    public ArrayList<UsersContract> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UsersContract> userList = null;
        try {
            userList = new ArrayList<UsersContract>();
            String QUERY = "SELECT * FROM " + singleUser.TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            int num = cursor.getCount();
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    UsersContract user = new UsersContract();
                    user.setId(cursor.getInt(0));
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    userList.add(user);
                }
            }
            db.close();
        } catch (Exception e) {
        }
        return userList;
    }

    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + singleUser.TABLE_NAME + " WHERE " + singleUser.ROW_USERNAME + "=? AND " + singleUser.ROW_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public Collection<HFacilitiesContract> getAllHFacilitiesByTehsil(String tehsil_code) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                HFacilityTable._ID,
                HFacilityTable.COLUMN_HFACILITY_CODE,
                HFacilityTable.COLUMN_HFACILITY_NAME,
                HFacilityTable.COLUMN_TEHSIL_CODE,
        };

        String whereClause = HFacilityTable.COLUMN_TEHSIL_CODE + " = ?";
        String[] whereArgs = {tehsil_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                HFacilityTable._ID + " ASC";

        Collection<HFacilitiesContract> allHFC = new ArrayList<>();
        try {
            c = db.query(
                    HFacilityTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                HFacilitiesContract hfc = new HFacilitiesContract();
                allHFC.add(hfc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allHFC;
    }

    public Collection<UCsContract> getAllUCsByTehsil(String tehsil_code) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UcTable._ID,
                UcTable.COLUMN_UC_NAME,
                UcTable.COLUMN_UC_CODE,
                UcTable.COLUMN_TEHSIL_CODE,
        };

        String whereClause = UcTable.COLUMN_TEHSIL_CODE + " = ?";
        String[] whereArgs = {tehsil_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                UcTable._ID + " ASC";

        Collection<UCsContract> allUCsC = new ArrayList<>();
        try {
            c = db.query(
                    UcTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            UCsContract uc1 = new UCsContract();
            allUCsC.add(uc1.setDefaultVal("", "..."));

            while (c.moveToNext()) {
                UCsContract ucsc = new UCsContract();
                allUCsC.add(ucsc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allUCsC;
    }

    public Collection<VillagesContract> getAllVillagesByUc(String uc_code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillageTable._ID,
                VillageTable.COLUMN_VILLAGE_CODE,
                VillageTable.COLUMN_VILLAGE_NAME,
                VillageTable.COLUMN_UC_CODE
        };

        String whereClause = VillageTable.COLUMN_UC_CODE + " = ?";
        String[] whereArgs = {uc_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                VillageTable.COLUMN_VILLAGE_CODE + " ASC";

        Collection<VillagesContract> allPC = new ArrayList<VillagesContract>();
        try {
            c = db.query(
                    VillageTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            VillagesContract uc1 = new VillagesContract();
            allPC.add(uc1.setDefaultVal("", "..."));

            while (c.moveToNext()) {
                VillagesContract pc = new VillagesContract();
                allPC.add(pc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allPC;
    }

    public Collection<LHWsContract> getAllLhwsByHf(String hf_code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                LHWTable._ID,
                LHWTable.COLUMN_LHW_CODE,
                LHWTable.COLUMN_LHW_NAME,
                LHWTable.COLUMN_HF_CODE,
                LHWTable.COLUMN_AREA_TYPE,
                LHWTable.COLUMN_STATUS
        };

        String whereClause = LHWTable.COLUMN_HF_CODE + " = ?";
        String[] whereArgs = {hf_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                LHWTable.COLUMN_LHW_CODE + " ASC";

        Collection<LHWsContract> allLhwC = new ArrayList<LHWsContract>();
        try {
            c = db.query(
                    LHWTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                LHWsContract lhwc = new LHWsContract();
                allLhwC.add(lhwc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allLhwC;
    }


    public Collection<TehsilsContract> getAllTehsil() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                TehsilTable._ID,
                TehsilTable.COLUMN_TEHSIL_CODE,
                TehsilTable.COLUMN_TEHSIL_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                TehsilTable._ID + " ASC";

        Collection<TehsilsContract> allDC = new ArrayList<TehsilsContract>();
        try {
            c = db.query(
                    TehsilTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                TehsilsContract dc = new TehsilsContract();
                allDC.add(dc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }


    public Collection<VillagesContract> getAllVillage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillageTable._ID,
                VillageTable.COLUMN_VILLAGE_CODE,
                VillageTable.COLUMN_VILLAGE_NAME,
                VillageTable.COLUMN_UC_CODE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                VillageTable._ID + " ASC";

        Collection<VillagesContract> allDC = new ArrayList<VillagesContract>();
        try {
            c = db.query(
                    VillageTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                VillagesContract vc = new VillagesContract();
                allDC.add(vc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }


    public void syncChild(JSONArray childlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PSUsContract.singleChild.TABLE_NAME, null, null);
        Log.d(TAG, "PSU table deleted!");
        try {
            JSONArray jsonArray = childlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                String LUID = jsonObjectUser.getString("UID");
                String psu = jsonObjectUser.getString("hh02");
                String hh03 = jsonObjectUser.getString("hh03");
                String hh07 = jsonObjectUser.getString("hh07");
                String child_name = jsonObjectUser.getString("child_name");


                ContentValues values = new ContentValues();

                values.put(singleChild.COLUMN_LUID, LUID);
                values.put(singleChild.COLUMN_HH03, hh03);
                values.put(singleChild.COLUMN_HH07, hh07);
                values.put(singleChild.COLUMN_HH, hh03 + "-" + hh07);
                values.put(singleChild.COLUMN_PSU, psu);
                values.put(singleChild.COLUMN_CHILD_NAME, child_name);

                db.insert(singleChild.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        }
    }


    public void syncUc(JSONArray ucList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UcTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = ucList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUc = jsonArray.getJSONObject(i);

                UCsContract dc = new UCsContract();
                dc.sync(jsonObjectUc);

                ContentValues values = new ContentValues();

                values.put(UcTable.COLUMN_UC_CODE, dc.getUcCode());
                values.put(UcTable.COLUMN_UC_NAME, dc.getUcName());
                values.put(UcTable.COLUMN_TEHSIL_CODE, dc.getTehsilCode());

                db.insert(UcTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
            Log.e(TAG, "syncUc: " + e.toString());
        }
    }

    public void syncSources(JSONArray ucList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SourceTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = ucList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUc = jsonArray.getJSONObject(i);

                SourceNGOContract dc = new SourceNGOContract();
                dc.sync(jsonObjectUc);

                ContentValues values = new ContentValues();

                values.put(SourceTable.COLUMN_SOURCE_CODE, dc.getSourceCode());
                values.put(SourceTable.COLUMN_SOURCE_NAME, dc.getSourceName());

                db.insert(SourceTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
            Log.e(TAG, "syncUc: " + e.toString());
        }
    }


    public void syncTehsil(JSONArray pcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TehsilTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = pcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectTehsil = jsonArray.getJSONObject(i);

                TehsilsContract pc = new TehsilsContract();
                pc.sync(jsonObjectTehsil);

                ContentValues values = new ContentValues();

                values.put(TehsilTable.COLUMN_TEHSIL_CODE, pc.getTehsil_code());
                values.put(TehsilTable.COLUMN_TEHSIL_NAME, pc.getTehsil_name());

                db.insert(TehsilTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {

        }
    }

    public void syncHFacility(JSONArray pcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HFacilityTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = pcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectHFacility = jsonArray.getJSONObject(i);

                HFacilitiesContract hc = new HFacilitiesContract();
                hc.sync(jsonObjectHFacility);

                ContentValues values = new ContentValues();

                values.put(HFacilityTable.COLUMN_HFACILITY_CODE, hc.gethFacilityCode());
                values.put(HFacilityTable.COLUMN_HFACILITY_NAME, hc.gethFacilityName());
                values.put(HFacilityTable.COLUMN_TEHSIL_CODE, hc.getTehsilCode());

                db.insert(HFacilityTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {

        }
    }


    public void syncVillages(JSONArray pcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillageTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = pcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectVillage = jsonArray.getJSONObject(i);

                VillagesContract vc = new VillagesContract();
                vc.sync(jsonObjectVillage);

                ContentValues values = new ContentValues();

                values.put(VillageTable.COLUMN_VILLAGE_CODE, vc.getVillageCode());
                values.put(VillageTable.COLUMN_VILLAGE_NAME, vc.getVillageName());
                values.put(VillageTable.COLUMN_UC_CODE, vc.getUcCode());

                db.insert(VillageTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {

        }
    }

    public void syncLHW(JSONArray lcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LHWsContract.LHWTable.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = lcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectLHW = jsonArray.getJSONObject(i);

                LHWsContract lc = new LHWsContract();
                lc.sync(jsonObjectLHW);

                ContentValues values = new ContentValues();

                values.put(LHWTable.COLUMN_LHW_CODE, lc.getLHWCode());
                values.put(LHWTable.COLUMN_LHW_NAME, lc.getLHWName());
                values.put(LHWTable.COLUMN_AREA_TYPE, lc.getAreaType());
                values.put(LHWTable.COLUMN_HF_CODE, lc.getHfCode());
                values.put(LHWTable.COLUMN_STATUS, lc.getStatus());

                db.insert(LHWTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {

        }
    }


    public ArrayList<PSUsContract> getAllChildren() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<PSUsContract> childList = null;
        try {
            childList = new ArrayList<PSUsContract>();
            String QUERY = "SELECT * FROM " + singleChild.TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            int num = cursor.getCount();
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    PSUsContract child = new PSUsContract(cursor);

                    childList.add(child);

                }
            }
            db.close();
        } catch (Exception e) {
        }
        return childList;
    }

    public String getChildByHH(String hh, String psu) {
        SQLiteDatabase db = this.getReadableDatabase();
        PSUsContract child = null;
        try {
            String QUERY = "SELECT * FROM " + singleChild.TABLE_NAME
                    + " where " + singleChild.COLUMN_HH + " = '" + hh.toUpperCase().replaceFirst("^0+(?!$)", "") + "' and " + singleChild.COLUMN_PSU + " = '" + psu + "' Limit 1";
            Cursor cursor = db.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    child = new PSUsContract(cursor);
                    return child.getChild_name();

                }
            } else {
                return "No Child Found";
            }
            db.close();
        } catch (Exception e) {
        }
        return "No Child Found";
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }
}