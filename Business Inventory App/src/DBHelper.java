package edu.qc.seclass.fim;
import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Inventory.db";
    public static final String tableName = "inventory_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CATEGORY";
    public static final String COL_3 = "TYPE";
    public static final String COL_4 = "COLOR";
    public static final String COL_5 = "BRAND";
    public static final String COL_6 = "PRICE";
    public static final String COL_7 = "SIZE";



    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
            execSql method executes sql statements that don't return data
         */
        db.execSQL("create table " + tableName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY TEXT, TYPE TEXT, COLOR TEXT, BRAND TEXT, PRICE TEXT, SIZE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }


    public Boolean insertData(String category, String type, String color, String brand, String price, String size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, category);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, color);
        contentValues.put(COL_5, brand);
        contentValues.put(COL_6, price);
        contentValues.put(COL_7, size);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        else
            return true;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + tableName, null);
        return result;
    }

    public Cursor getAllByCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + tableName + " WHERE " + COL_2 + " LIKE category " ,null );
        return result;
    }

    public Cursor getAllByType(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + tableName + " WHERE " + COL_3 + " LIKE type " ,null );
        return result;
    }



    public boolean UpdateData(String id, String category, String type, String color, String brand, String price, String size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, category);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, color);
        contentValues.put(COL_5, brand);
        contentValues.put(COL_6, price);
        contentValues.put(COL_7, size);

        db.update(tableName, contentValues, "ID=?", new String[] {id});
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "ID = ?", new String[]{id});
    }
}
