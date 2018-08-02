//this helped me better understand how to use object models for a cleaner wasy of accessing
//the data in the database - https://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/

package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.android.inventoryapp.database.CategoryModel;

import com.example.android.inventoryapp.database.InventoryContract;
import com.example.android.inventoryapp.database.InventoryDbHelper;


public class ViewActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new InventoryDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.insert_category_data:
                //call the insert mock data method to add many products and categories to the DB
                insertCategories();
                //once new data is added, refresh the display
                displayCategoryData();
                return true;

            case R.id.insert_supplier_data:
                Log.i("View activity", "you clicked insert mock data");

                //call the insert mock data method to add many products and categories to the DB
                insertSuppliers();
                //once new data is added, refresh the display
                displaySupplierData();
                return true;

            case R.id.insert_product_data:
            Log.i("View activity", "you clicked insert mock data");

            //call the insert mock data method to add many products and categories to the DB
            insertProducts();
            displayProductData();
            return true;


            // Respond to a click on the "Delete all entries" menu option
            case R.id.delete_all_data:
                // Respond to a click on the "Delete data" menu option
                Log.i("View activity", "you clicked delet data");

                //delete all products but don't remove the table
                deleteAllData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void displayCategoryData() {
        Log.i("get all data", "the get all data method was called");

        TextView viewToHide1 = (TextView) findViewById(R.id.text_view_supplier);
        viewToHide1.setVisibility(View.INVISIBLE);

        TextView viewToHide2 = (TextView) findViewById(R.id.text_view_product);
        viewToHide2.setVisibility(View.INVISIBLE);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                InventoryContract.CategoryEntry._ID,
                InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                InventoryContract.CategoryEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_category);
        displayView.setVisibility(View.VISIBLE);

        try {
            // Create a header in the Text View that looks like this:
            //
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The cateogories table contains " + cursor.getCount() + " categories.\n\n");
            displayView.append(InventoryContract.CategoryEntry._ID + " - " +
                    InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InventoryContract.CategoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void displaySupplierData() {
        Log.i("get all data", "the get all data method was called");
        TextView viewToHide1 = (TextView) findViewById(R.id.text_view_category);
        viewToHide1.setVisibility(View.INVISIBLE);

        TextView viewToHide2 = (TextView) findViewById(R.id.text_view_product);
        viewToHide2.setVisibility(View.INVISIBLE);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                InventoryContract.SupplierEntry._ID,
                InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME,
                InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                InventoryContract.SupplierEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_supplier);
        displayView.setVisibility(View.VISIBLE);

        try {
            // Create a header in the Text View that looks like this:
            //
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The supplier table contains " + cursor.getCount() + " suppliers.\n\n");
            displayView.append(InventoryContract.SupplierEntry._ID + " - " +
                    InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME + " - " +
                    InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InventoryContract.SupplierEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME);
            int phoneColumnIndex = cursor.getColumnIndex(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPhone = cursor.getString(phoneColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " + currentName + " - " + currentPhone));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

   private void displayProductData() {
        Log.i("get all data", "the get all data method was called");

       TextView viewToHide1 = (TextView) findViewById(R.id.text_view_category);
       viewToHide1.setVisibility(View.INVISIBLE);

       TextView viewToHide2 = (TextView) findViewById(R.id.text_view_supplier);
       viewToHide2.setVisibility(View.INVISIBLE);
       SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                InventoryContract.ProductEntry._ID,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                InventoryContract.ProductEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_product);
        displayView.setVisibility(View.VISIBLE);

        try {
            // Create a header in the Text View that looks like this:
            //
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The product table contains " + cursor.getCount() + " products.\n\n");
            displayView.append(InventoryContract.ProductEntry._ID + " - " +
                    InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME + " - " +
                    InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);


                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " + currentName + " - " + currentQuantity));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void deleteAllData() {
        Log.i("delete all data", "the delee all data method was called");
    }

    private void insertCategories() {
        //add some mock data here using models
        //create a category
        CategoryModel category1 = new CategoryModel("generic category");
        //insert into db
        long category1_id = mDbHelper.createCategory(category1);

        /*SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, append the column values
        //this will create 5 mock categories

        ContentValues values = new ContentValues();
        int i = 0;
        while(i<5) {
            values.put(InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME, "Mock Category_" + i);

            // Insert a new rows into the database, returning the ID of that new row.
            long cat_newRowId = db.insert(InventoryContract.CategoryEntry.TABLE_NAME, null, values);
            //insertSuppliers(cat_newRowId);
            Log.i("insert categories", "the insert categories method was called: " + cat_newRowId);
            i++;
        }*/
    }

   private void insertSuppliers() {
        //add some mock data here without using models
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, append the column values
        //this will create 3 mock suppliers
       ContentValues values = new ContentValues();
       int i = 0;
       while(i<3) {
            values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME, "Mock Supplier Name_" + i);
            values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_EMAIL, "supplier_email_" + i + "@fake.com");
            values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE, "555-555-000" + i);
            values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_WEB, "www.supplier_" + i + ".com");
            values.put(InventoryContract.SupplierEntry.COLUMN_CATEGORY_ID, "category_" + i);

            // Insert a new row into the database, returning the ID of that new row.
            // The first argument for db.insert() is the category table name.
            // The second argument provides the name of a column in which the framework
            // can insert NULL in the event that the ContentValues is empty (if
            // this is set to "null", then the framework will not insert a row when
            // there are no values).
            // The third argument is the ContentValues object containing the info being inserted.
            long newRowId = db.insert(InventoryContract.SupplierEntry.TABLE_NAME, null, values);
            Log.i("insert suppliers", "the insert suppliers method was called: " + newRowId);
            i++;
        }
    }

    private void insertProducts() {
        //add some mock data here without using models
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, append the column values
        //this will create 2 mock products
        ContentValues values = new ContentValues();
        int i = 0;
        while(i<5) {
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME, "Mock Product Name " + i);
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION, "This is the description for a mock product.");
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE, i + "00");
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, i);
            values.put(InventoryContract.ProductEntry.COLUMN_SUPPLIER_ID, i);
            values.put(InventoryContract.ProductEntry.COLUMN_CATEGORY_ID, i);

            // Insert a new row into the database, returning the ID of that new row.
            // The first argument for db.insert() is the category table name.
            // The second argument provides the name of a column in which the framework
            // can insert NULL in the event that the ContentValues is empty (if
            // this is set to "null", then the framework will not insert a row when
            // there are no values).
            // The third argument is the ContentValues object containing the info being inserted.
            long newRowId = db.insert(InventoryContract.ProductEntry.TABLE_NAME, null, values);
            Log.i("insert suppliers", "the insert suppliers method was called: " + newRowId);
            i++;
        }

    }
}
