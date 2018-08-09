package com.example.android.inventoryapp.database;

//this class is used to define the tables and columns in the Inventory database that is used with the inventory app

import android.provider.BaseColumns;

public final class InventoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {}

    //each table should be defined here. for each table, specify the table name as well as the data that is to be stored
    //each piece of data should have it's own column.
    //this file is used to setup a naming convention that can be used thru out the app to access items in the database

    public static final class ProductEntry implements BaseColumns{
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
    }

    public static final class SupplierEntry implements BaseColumns {
        //name of table
        public final static String TABLE_NAME = "suppliers";

        //columns in the table
        //this is the unique key value for each product row
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
        //name of table
        public final static String TABLE_NAME = "categories";

        //columns in the table
        //this is the unique key value for each product row
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CATEGORY_NAME = "category_name";
    }
}
