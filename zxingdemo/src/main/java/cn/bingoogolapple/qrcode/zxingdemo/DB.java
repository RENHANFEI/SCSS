package cn.bingoogolapple.qrcode.zxingdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by renhanfei on 17/4/5.
 */

public class DB {
    DBHelper DBHelper;
    SQLiteDatabase db;
    final Context context;

    public DB(Context ctx) {
        this.context = ctx;
        DBHelper = new DBHelper(this.context);
    }


    public DB open() {
        db = DBHelper.getWritableDatabase();

        // Toast.makeText(context, Environment.getDataDirectory().toString(), Toast.LENGTH_SHORT).show();

        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public long insertLocation(String record_time, String record_position, String record_latitude,
                               String record_longitude, String record_address) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DBHelper.columnName_itemTime, record_time);
        initialValues.put(DBHelper.columnName_itemPosition, record_position);
        initialValues.put(DBHelper.columnName_itemLatitude, record_latitude);
        initialValues.put(DBHelper.columnName_itemLongitude, record_longitude);
        initialValues.put(DBHelper.columnName_itemAddress, record_address);

        return db.insert(DBHelper.tableName, null, initialValues);
    }

    public int deleteLocation(long id) {
        return db.delete(DBHelper.tableName, DBHelper.columnName_itemID + "=" + id, null);
    }

    public int deleteAllLocation() {
        return db.delete(DBHelper.tableName, "1", null);    // delete all records
    }

    public int updateLocation(long id, String record_position, String record_address) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DBHelper.columnName_itemPosition, record_position);
        initialValues.put(DBHelper.columnName_itemAddress, record_address);
        return db.update(DBHelper.tableName, initialValues,
                DBHelper.columnName_itemID + "=" + id, null);
    }

    public Cursor getAllRecords() {
        return db.query(
                DBHelper.tableName,
                new String[]{
                        DBHelper.columnName_itemID,
                        DBHelper.columnName_itemTime,
                        DBHelper.columnName_itemPosition,
                        DBHelper.columnName_itemLatitude,
                        DBHelper.columnName_itemLongitude,
                        DBHelper.columnName_itemAddress},
                null, null, null, null, null);
    }


    public Cursor getLocation(long id) {
        Cursor mCursor = db.query(DBHelper.tableName,
                new String[]{
                        DBHelper.columnName_itemID,
                        DBHelper.columnName_itemPosition,
                        DBHelper.columnName_itemLatitude,
                        DBHelper.columnName_itemLongitude,
                        DBHelper.columnName_itemAddress},
                DBHelper.columnName_itemID + "=" + id,
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
