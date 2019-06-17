package app.m.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";
    //CREAREMOS LA CONEXION A LA BASE DE DATOS
    //BASE DE DATOS LOCAL
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "productosdb";
    private static final String TABLE = "productos";
    //DECLARAMOS LA COLUMNAS DE LA TABLA products
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";

    public DataBaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE ="CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PRICE + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    public boolean insertProduct(String name, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME,name);
        cValues.put(KEY_PRICE,price);

        long newRowId = db.insert(TABLE, null, cValues);
        db.close();
        //if date as inserted incorrectly it will return -1
        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}