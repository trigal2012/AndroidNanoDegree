package com.example.android.inventorymaster.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventorymaster.database.InventoryContract.ProductEntry;
import com.example.android.inventorymaster.database.InventoryContract.SupplierEntry;
import com.example.android.inventorymaster.database.InventoryContract.CategoryEntry;

import static com.example.android.inventorymaster.database.InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME;
import static com.example.android.inventorymaster.database.InventoryContract.CategoryEntry._ID;
import static com.example.android.inventorymaster.database.InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME;
import static com.example.android.inventorymaster.database.InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY;
import static com.example.android.inventorymaster.database.InventoryContract.SupplierEntry.COLUMN_SUPPLIER_EMAIL;
import static com.example.android.inventorymaster.database.InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME;
import static com.example.android.inventorymaster.database.InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE;
import static com.example.android.inventorymaster.database.InventoryContract.SupplierEntry.COLUMN_SUPPLIER_WEB;


public class InventoryDbHelper extends SQLiteOpenHelper {

    //Name of the database file
    private static final String DATABASE_NAME = "inventory.db";

    //database version - used when the database schema is updated
    private static final int DATABASE_VERSION = 1;

    //constructor for a new instance
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //when the app starts, create a DB if one does not exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute the SQL statements to actually create the specified tables
        String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME + " ("
                + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + ProductEntry.COLUMN_PRODUCT_DESCRIPTION + " TEXT NOT NULL, "
                + ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0, "
                + COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + ProductEntry.COLUMN_PRODUCT_IMAGE_ID + " INTEGER, "
                + ProductEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL DEFAULT 0, "
                + ProductEntry.COLUMN_SUPPLIER_ID + " INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
        String SQL_CREATE_SUPPLIERS_TABLE = "CREATE TABLE " + SupplierEntry.TABLE_NAME + " ("
                + SupplierEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + COLUMN_SUPPLIER_EMAIL + " TEXT, "
                + COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL, "
                + COLUMN_SUPPLIER_WEB + " TEXT, "
                + SupplierEntry.COLUMN_CATEGORY_ID + " INTEGER);";
        db.execSQL(SQL_CREATE_SUPPLIERS_TABLE);
        String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CategoryEntry.TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY_NAME + " TEXT NOT NULL);";
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

}
