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
import com.example.android.inventoryapp.database.ProductModel;
import com.example.android.inventoryapp.database.SupplierModel;

import java.util.List;


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
    protected void onStart(){
        super.onStart();
        displayCategoryData();
        displaySupplierData();
        displayProductData();
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
                insertCategories();
                //once new data is added, refresh the display
                displayCategoryData();
                return true;

            case R.id.insert_supplier_data:
                //call the insert mock data method to add supplier data to the DB
                insertSuppliers();
                //once new data is added, refresh the display
                displaySupplierData();
                return true;

            case R.id.insert_product_data:
            Log.i("View activity", "you clicked insert mock data");

            //call the insert mock data method to add products to the DB
            insertProducts();
            displayProductData();
            return true;

            case R.id.delete_all:
                Log.i("View activity", "you clicked delete data");

                //call the insert mock data method to add many products and categories to the DB
                deleteData();
                displayCategoryData();
                displaySupplierData();
                displayProductData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void displayCategoryData() {
        //use the model to get the category column names
        List<String> categoryColumns = mDbHelper.getCategoryColumnNames();

        //use the model to get all categories from the database
        List<CategoryModel> allCategories = mDbHelper.getAllCategories();

        //find the category view and make it visible
        TextView displayView = (TextView) findViewById(R.id.text_view_category);
        displayView.setVisibility(View.VISIBLE);

        //number of rows in the category table
        displayView.setText("The category table contains " + allCategories.size() + " categories.\n\n");

        //create the header row for the category list
        for(int i = 0; i < categoryColumns.size(); i++) {
            if(i<categoryColumns.size()-1) {
                displayView.append(categoryColumns.get(i) + " - ");
            }else{
                displayView.append(categoryColumns.get(i) + "\n");
            }
        }

        //add each category row to the view
        for(CategoryModel category: allCategories){
            displayView.append("\n" + category.getId() + " - " + category.getName());
        }
    }

    private void displaySupplierData() {
        //use the model to get the supplier column names
        List<String> supplierColumns = mDbHelper.getSupplierColumnNames();

        //use the model to get all suppliers from the database
        List<SupplierModel> allSuppliers = mDbHelper.getAllSuppliers();

        //find supplier view and make it visible
        TextView displayView = (TextView) findViewById(R.id.text_view_supplier);
        displayView.setVisibility(View.VISIBLE);

        //number of rows in supplier table
        displayView.setText("The supplier table contains " + allSuppliers.size() + " suppliers.\n\n");

        //create the header row for the supplier list
        for(int i = 0; i < supplierColumns.size(); i++) {
            if(i<supplierColumns.size()-1) {
                displayView.append(supplierColumns.get(i) + " - ");
            }else{
                displayView.append(supplierColumns.get(i) + "\n");
            }
        }

        //add each supplier to the view
        for(SupplierModel supplier: allSuppliers) {
            displayView.append(("\n" + supplier.getId() + " - " + supplier.getName() + " - " + supplier.getPhone()));
        }
    }

    private void displayProductData() {
        //use the model to get the producy column names
        List<String> productColumns = mDbHelper.getProductColumnNames();

        //use the model to get all suppliers from the database
        List<ProductModel> allProducts = mDbHelper.getAllProducts();

        //find products view and make it visible
        TextView displayView = (TextView) findViewById(R.id.text_view_product);
        displayView.setVisibility(View.VISIBLE);

        //number of rows in product table
        displayView.setText("The product table contains " + allProducts.size() + " products.\n\n");

        //create the header row for the product list
        for(int i = 0; i < productColumns.size(); i++) {
            if(i<productColumns.size()-1) {
                displayView.append(productColumns.get(i) + " - ");
            }else{
                displayView.append(productColumns.get(i) + "\n");
            }
        }

        //add each product to the view
        for(ProductModel product: allProducts) {
            displayView.append(("\n" + product.getId() + " - " + product.getName() + " - " + product.getQuantity()));
        }
    }


    private void getCategories(){
        //get all of the categories in the DB using the Category Model

    }

    private void insertCategories() {
        //add some mock data here using models
        //create a category
        CategoryModel category1 = new CategoryModel("generic category");
        //insert into db
        long category1_id = mDbHelper.createCategory(category1);
        displayCategoryData();

    }

    private void insertSuppliers() {
        //add some mock data here without using models
        //create a supplier
        SupplierModel supplier1 = new SupplierModel("Mock Supplier Name", "supplier_email@fake.com","555-555-1212","www.supplier.com",0);
        //insert into db
        long supplier1_id = mDbHelper.createSupplier(supplier1);

    }

    private void insertProducts() {
        //add some mock data here without using models
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, append the column values
        //create a supplier
        ProductModel product1 = new ProductModel("Product Name", "Product Description", 5, 100, 0, 0);
        //insert into db
        long product_id = mDbHelper.createProduct(product1);

    }

    private void deleteData(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.deleteData(db);
    }
}
