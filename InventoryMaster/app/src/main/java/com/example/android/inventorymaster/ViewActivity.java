package com.example.android.inventorymaster;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.android.inventorymaster.database.InventoryContract;
import com.example.android.inventorymaster.database.InventoryDbHelper;


public class ViewActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    /**
     * Database helper that will provide us access to the database
     */
    private InventoryDbHelper mDbHelper;

    /** Identifier for the product data loader */
    private static final int PRODUCT_LOADER = 0;

    /** Adapter for the ListView */
    private InventoryCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        //find the listView and populate it with Products
        ListView productListView = findViewById(R.id.list);
        mCursorAdapter = new InventoryCursorAdapter(this, null);
        productListView.setAdapter(mCursorAdapter);

        //setup the click listener for each row
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ViewActivity.this, EditorActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(InventoryContract.ProductEntry.CONTENT_URI, id);
                intent.setData(currentProductUri);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(PRODUCT_LOADER, null, this);
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
                //call the insert mock data method to add many categories to the DB
                insertCategory();
                //once new data is added, refresh the display
                return true;

            case R.id.insert_supplier_data:
                //call the insert mock data method to add supplier data to the DB
                insertSupplier();
                //once new data is added, refresh the display
                return true;

            case R.id.insert_product_data:
                //call the insert mock data method to add products to the DB
                insertProduct();
                return true;

            case R.id.delete_all:
                //call the insert mock data method to add many products and categories to the DB
                deleteData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertProduct() {
        //add some mock data here with using models
        // Create a ContentValues object where column names are the keys, append the column values
        //TODO: add data validation here before parsing and sending to uri
        ContentValues values = new ContentValues();
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME, "Sample Product");
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION, "some stuff about the product will go here");
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE, 599);
        values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, 100);
        values.put(InventoryContract.ProductEntry.COLUMN_SUPPLIER_ID, 0);
        values.put(InventoryContract.ProductEntry.COLUMN_CATEGORY_ID, 0);

        //call the uri and send the data
        Uri newUri = getContentResolver().insert(InventoryContract.ProductEntry.CONTENT_URI, values);
    }

    private void insertCategory(){
        //add some mock data here with using models
        // Create a ContentValues object where column names are the keys, append the column values
        //TODO: add data validation here before parsing to send to uri
        ContentValues values = new ContentValues();
        values.put(InventoryContract.CategoryEntry.COLUMN_CATEGORY_NAME, "Sample category");

        //call the uri and send the data
        Uri newUri = getContentResolver().insert(InventoryContract.CategoryEntry.CONTENT_URI, values);
    }

    private void insertSupplier(){
        //add some mock data here with using models
        // Create a ContentValues object where column names are the keys, append the column values
        //TODO: add data validation here before parsing to send to uri
        ContentValues values = new ContentValues();
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_NAME, "Sample Supplier");
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_EMAIL, "suppliercontact@fakeEmail.com");
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_PHONE, "555-555-1212");
        values.put(InventoryContract.SupplierEntry.COLUMN_SUPPLIER_WEB, "www.supplier.com");
        values.put(InventoryContract.SupplierEntry.COLUMN_CATEGORY_ID, 0);

        //call the uri and send the data
        Uri newUri = getContentResolver().insert(InventoryContract.SupplierEntry.CONTENT_URI, values);
    }

    private void deleteData() {
        int rowsDeleted = getContentResolver().delete(InventoryContract.ProductEntry.CONTENT_URI, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                InventoryContract.ProductEntry._ID,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION,
                InventoryContract.ProductEntry.COLUMN_CATEGORY_ID,
                InventoryContract.ProductEntry.COLUMN_SUPPLIER_ID};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                InventoryContract.ProductEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update  with this new cursor containing updated product data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }
}
