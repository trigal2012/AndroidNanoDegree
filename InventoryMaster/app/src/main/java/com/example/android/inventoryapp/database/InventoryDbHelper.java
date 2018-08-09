package com.example.android.inventoryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import com.example.android.inventoryapp.database.InventoryContract.ProductEntry;
import com.example.android.inventoryapp.database.InventoryContract.SupplierEntry;
import com.example.android.inventoryapp.database.InventoryContract.CategoryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.android.inventoryapp.database.InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME;
import static com.example.android.inventoryapp.database.InventoryContract.CategoryEntry._ID;


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
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CATEGORY_NAME + " TEXT NOT NULL);";

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

    //adding various CRUD operations here so they are more easily accessed from all parts of the app
    //also better for using recycleView, view models, etc


    //--------- CREATE -----------//
    //create a new Category
    public long createCategory(CategoryModel categoryModel){
        //access the database
        SQLiteDatabase db = this.getWritableDatabase();

        //set the parameters - add a values.put line for each property in the object
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, categoryModel.getName());

        //insert the row
        long category_id = db.insert(CategoryEntry.TABLE_NAME, null, values);

        return category_id;
    }

    //create a new Supplier
    public long createSupplier(SupplierModel supplierModel){
        //access the database
        SQLiteDatabase db = this.getWritableDatabase();

        //set the parameters - add a values.put line for each property in the object
        ContentValues values = new ContentValues();
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME, supplierModel.getName());
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_EMAIL, supplierModel.getEmail());
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE, supplierModel.getPhone());
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_WEB, supplierModel.getWeb());
        values.put(InventoryContract.SupplierEntry.COLUMN_CATEGORY_ID, supplierModel.getCategoryId());

        //insert the row
        long supplier_id = db.insert(SupplierEntry.TABLE_NAME, null, values);

        return supplier_id;
    }

    //create a new Product
    public long createProduct(ProductModel productModel){
        //access the database
        SQLiteDatabase db = this.getWritableDatabase();

        //set the parameters - add a values.put line for each property in the object
        ContentValues values = new ContentValues();
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME, productModel.getName());
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION, productModel.getDescription());
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE, productModel.getPrice());
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, productModel.getQuantity());
        values.put(InventoryContract.ProductEntry.COLUMN_SUPPLIER_ID, productModel.getSupplierId());
        values.put(InventoryContract.ProductEntry.COLUMN_CATEGORY_ID, productModel.getCategoryId());

        //insert the row
        long product_id = db.insert(ProductEntry.TABLE_NAME, null, values);

        return product_id;
    }


    //--------- READ -----------//
    //access the database for reading
    //get all Categories
    public List<CategoryModel> getAllCategories(){
        //create the array list to hold the category objects
        List<CategoryModel> categories = new ArrayList<CategoryModel>();

        //connect to the database
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                _ID,
                COLUMN_CATEGORY_NAME};

        // Perform a query on the category table
        Cursor cursor = db.query(
                CategoryEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryModel category = new CategoryModel();
                category.setId(cursor.getInt((cursor.getColumnIndex(_ID))));
                category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));

                // adding to tags list
                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return categories;
    }


    public void deleteData(SQLiteDatabase db) {
        //this will handlie deleting all data - it will not remove the DB, just delete all rows on all tables
        //create the delete string
        String SQL_DELETE_ALL_CATEGORIES = "DELETE FROM " + CategoryEntry.TABLE_NAME;
        String SQL_DELETE_ALL_SUPPLIERS = "DELETE FROM " + SupplierEntry.TABLE_NAME;
        String SQL_DELETE_ALL_PRODUCTS = "DELETE FROM " + ProductEntry.TABLE_NAME;

        //run the sql execute
        db.execSQL(SQL_DELETE_ALL_CATEGORIES);
        db.execSQL(SQL_DELETE_ALL_SUPPLIERS);
        db.execSQL(SQL_DELETE_ALL_PRODUCTS);

    }
}

