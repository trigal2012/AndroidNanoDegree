package com.example.android.inventoryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.database.InventoryContract.ProductEntry;
import com.example.android.inventoryapp.database.InventoryContract.SupplierEntry;
import com.example.android.inventoryapp.database.InventoryContract.CategoryEntry;


public class InventoryDbHelper extends SQLiteOpenHelper{

    //Name of the database file
    private static final String DATABASE_NAME = "inventory.db";

    //database version - used when the database schema is updated
    private static final int DATABASE_VERSION = 1;

    //constructor for a new instance
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create statements for tables - keeping seperate from onCreate so they can more easily be reused
    //create the string concatenation for the Products table
    String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME +  " ("
            + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
            + ProductEntry.COLUMN_PRODUCT_DESCRIPTION + " TEXT NOT NULL, "
            + ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0, "
            + ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
            + ProductEntry.COLUMN_PRODUCT_IMAGE_ID + " INTEGER, "
            + ProductEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL DEFAULT 0, "
            + ProductEntry.COLUMN_SUPPLIER_ID + " INTEGER NOT NULL DEFAULT 0);";

    //create the string concatenation for the Suppliers table
    String SQL_CREATE_SUPPLIERS_TABLE = "CREATE TABLE " + SupplierEntry.TABLE_NAME +  " ("
            + SupplierEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SupplierEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
            + SupplierEntry.COLUMN_SUPPLIER_EMAIL + " TEXT, "
            + SupplierEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL, "
            + SupplierEntry.COLUMN_SUPPLIER_WEB + " TEXT, "
            + SupplierEntry.COLUMN_CATEGORY_ID + " INTEGER);";

    //create the string concatenation for the Categories table
    String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CategoryEntry.TABLE_NAME +  " ("
            + CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoryEntry.COLUMN_CATEGORY_NAME + " TEXT NOT NULL);";

    //when the app starts, create a DB if one does not exist
    @Override
    public void onCreate(SQLiteDatabase db){
        //execute the SQL statements to actually create the specified tables
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
        db.execSQL(SQL_CREATE_SUPPLIERS_TABLE);
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do this on upgrade - drop the old tables
        db.execSQL("DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SupplierEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME);

        //create the new tables
        onCreate(db);
    }

    //adding various crude operations here so they are more easily accessed from all parts of the app
    //also better for using recycleView, view models, etc

    //deal with Category creation first
    public long createCategory(CategoryModel categoryModel){
        //access the database
        SQLiteDatabase db = this.getWritableDatabase();

        //set the parameters
        ContentValues values = new ContentValues();
        values.put(InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME, categoryModel.getName());

        //insert the row
        long category_id = db.insert(CategoryEntry.TABLE_NAME, null, values);

        return category_id;
    }

}


