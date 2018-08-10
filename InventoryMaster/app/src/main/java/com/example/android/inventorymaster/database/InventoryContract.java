package com.example.android.inventorymaster.database;

//this class is used to define the tables and columns in the Inventory database that is used with the inventory app

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {}

    //constant to hold the content authority, used with the URI, usually set this to the package name of the app
    public static final String CONTENT_AUTHORITY = "com.example.android.inventorymaster";

    //Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    //the content provider.
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //path to append to the URI
    //one for each table
    public static final String PATH_PRODUCTS = "products";
    public static final String PATH_CATEGORIES = CategoryEntry.TABLE_NAME;
    public static final String PATH_SUPPLIERS = SupplierEntry.TABLE_NAME;


    //each table should be defined here. for each table, specify the table name as well as the data that is to be stored
    //each piece of data should have it's own column.
    //this file is used to setup a naming convention that can be used through out the app to access items in the database

    public static final class ProductEntry implements BaseColumns{

        //URI path for access, need for each table in the DB that should be accessible
        /** The content URI to access the product data in the provider */
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
        public final static String COLUMN_PRODUCT_NAME ="product_name";
        public final static String COLUMN_PRODUCT_DESCRIPTION ="product_description";
        public final static String COLUMN_PRODUCT_QUANTITY ="product_quantity";
        public final static String COLUMN_PRODUCT_PRICE ="product_price";
        public final static String COLUMN_PRODUCT_IMAGE_ID ="product_image_ref";

        //each product will belong to a category, this is the category id - links to the category table
        public final static String COLUMN_CATEGORY_ID ="category_id";

        //each product will have a supplier, this is the supplier id  - links to the supplier table
        public final static String COLUMN_SUPPLIER_ID ="supplier_id";

        //possible values for suppliers - until supplier data is grabbed from database
        public static final int SUPPLIER_UNKNOWN = 0;
        public static final int NIKE = 1;
        public static final int ARENA = 2;
        public static final int CERVELO= 3;

        //possible values for categories - until category data is grabbed from the database
        public static final int CATEGORY_UNKNOWN = 0;
        public static final int SWIM = 1;
        public static final int BIKE = 2;
        public static final int RUN = 3;

        //return true|false if the supplier matches
        public static boolean isValidSupplier(int supplier) {
            return supplier == SUPPLIER_UNKNOWN || supplier == NIKE || supplier == ARENA || supplier == CERVELO;
        }

        //return true|false if the category matches
        public static boolean isValidCategory(int category) {
            return category == CATEGORY_UNKNOWN || category == SWIM || category == BIKE || category == RUN;
        }
    }

    public static final class SupplierEntry implements BaseColumns {
        //URI path for access, need for each table in the DB that should be accessible
        /** The content URI to access the data in the supplier */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SUPPLIERS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of suppliers.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUPPLIERS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single supplier.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUPPLIERS;

        //name of table
        public final static String TABLE_NAME = "suppliers";

        //columns in the table
        //this is the unique key value for each supplier row
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SUPPLIER_NAME ="supplier_name";
        public final static String COLUMN_SUPPLIER_EMAIL ="supplier_email";
        public final static String COLUMN_SUPPLIER_PHONE ="supplier_phone";
        public final static String COLUMN_SUPPLIER_WEB ="supplier_web";

        //a supplier may have products in many different categories
        //we may want to list suppliers by category
        public final static String COLUMN_CATEGORY_ID ="category_id";
    }

    public static final class CategoryEntry implements BaseColumns {
        //URI path for access, need for each table in the DB that should be accessible
        /** The content URI to access the data in the supplier */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CATEGORIES);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of suppliers.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single supplier.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;


        //name of table
        public final static String TABLE_NAME = "categories";

        //columns in the table
        //this is the unique key value for each category row
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CATEGORY_NAME = "category_name";
    }
}
