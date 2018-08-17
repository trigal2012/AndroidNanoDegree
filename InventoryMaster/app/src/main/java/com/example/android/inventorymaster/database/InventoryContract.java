package com.example.android.inventorymaster.database;

//this class is used to define the tables and columns in the Inventory database that is used with the inventory app

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {

    //constant to hold the content authority, used with the URI, usually set this to the package name of the app
    public static final String CONTENT_AUTHORITY = "com.example.android.inventorymaster";
    //path to append to the URI
    //one for each table
    public static final String PATH_PRODUCTS = "products";
    //Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    //the content provider.
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {
    }

    //each table should be defined here. for each table, specify the table name as well as the data that is to be stored
    //each piece of data should have it's own column.
    //this file is used to setup a naming convention that can be used through out the app to access items in the database

    public static final class ProductEntry implements BaseColumns {

        //URI path for access, need for each table in the DB that should be accessible
        /**
         * The content URI to access the product data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        //name of table
        public final static String TABLE_NAME = "products";

        //columns in the table
        //this is the unique key value for each product row
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "product_name";
        public final static String COLUMN_PRODUCT_DESCRIPTION = "product_description";
        public final static String COLUMN_PRODUCT_QUANTITY = "product_quantity";
        public final static String COLUMN_PRODUCT_PRICE = "product_price";
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "product_supplier";
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE = "product_supplier_phone";

    }
}
