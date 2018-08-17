package com.example.android.inventorymaster.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;

//this file is used to provide a secure means for other apps to access the data from this app
//it leverages the use of URIs for CRUD operations of the database
//before this file existed, CRUD operations were in the DbHelper file
//they will be moved to this file
//the activities will then need to be updated to use the URI instead of mDbHelper

public class InventoryProvider extends ContentProvider {

    //Tags for logging
    private static final String PRODUCTS_LOG_TAG = com.example.android.inventorymaster.database.InventoryContract.ProductEntry.class.getSimpleName();


    //setup the URI matcher codes for each table
    private static final int PRODUCTS = 100;
    private static final int PRODUCT_ID = 101;

    //UriMatcher object to match a content URI to a corresponding code
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    //this will bind the above int values to a specific uri path
    //then, when uris are called, rather then looking at the full path for a match
    //the content provider will match the int values
    //add a uri matcher for each code above
    static{
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_PRODUCTS, PRODUCTS);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
    }

    //getType runs a switch statement to check the incoming calls to a URI to see if they
    //match the above values, if do, the calls goes through, if not, an exception is thrown
    //it's not likely that this app will cause an exception however calls from outside this app could trigger an exception
    //if the correct URI path is not called
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return InventoryContract.ProductEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return InventoryContract.ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    /** Database helper object */
    private InventoryDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        //initializing the dbHelper object
        mDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    //***** This is where the CRUD operations are created/handled **********

    //Setup a cursor for querying. The Cursor holds the result of the query and will be
    //used in the Activities for the views
    //have switch cases for each possible query path, such as:
    //getAll for each table, get a single row for each table, basically any data the app will need
    //should have a path specified

    //***** READ method ****
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            //queries the products table to return results for one or more items
            case PRODUCTS:
                cursor = database.query(com.example.android.inventorymaster.database.InventoryContract.ProductEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            //queries the table for a single row
            case PRODUCT_ID:
                selection = com.example.android.inventorymaster.database.InventoryContract.ProductEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(com.example.android.inventorymaster.database.InventoryContract.ProductEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }

    //***** CREATE method ****
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return insertProduct(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertProduct(Uri uri, ContentValues values){
        //TODO: add the validation for the values before writing to the database
        //add values to the db
        //open a connection to the DB
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        // Insert the new product with the given values
        long id = database.insert(com.example.android.inventorymaster.database.InventoryContract.ProductEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(PRODUCTS_LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the product content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    //***** UPDATE method ****
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return updateProduct(uri, contentValues, selection, selectionArgs);
            case PRODUCT_ID:
                selection = com.example.android.inventorymaster.database.InventoryContract.ProductEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateProduct(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    //update a product
    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link ProductEntry#COLUMN_Product_NAME} key is present,
        // check that the name value is not null.
        if (values.containsKey(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME)) {
            String name = values.getAsString(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
            if (name == null || name.equals("")) {
                return 0;
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(InventoryContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }


    //***** DELETE method ****
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(com.example.android.inventorymaster.database.InventoryContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PRODUCT_ID:
                // Delete a single row given by the ID in the URI
                selection = com.example.android.inventorymaster.database.InventoryContract.ProductEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(com.example.android.inventorymaster.database.InventoryContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }
}
