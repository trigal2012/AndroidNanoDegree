package com.example.android.inventorymaster;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventorymaster.database.InventoryContract;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //use for formatting quantity and currency values
    static Locale locale = Locale.getDefault();
    String currencySymbol = Currency.getInstance(locale).getSymbol();
    NumberFormat nf = NumberFormat.getInstance(locale);

    //identifier for the loader
    private static final int EXISTING_PRODUCT_LOADER = 0;

    //content uri for existing product
    private Uri mCurrentProductUri;

    //vars for the edit fields;
    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private EditText mPrice;
    private EditText mQuantity;
    private TextView mCurrencySymbol;
    private EditText mSupplierName;
    private EditText mSupplierPhone;
    private ImageButton mDelete;
    private ImageButton mPlusBtn;
    private ImageButton mMinusBtn;
    private ImageButton mPhoneBtn;
    private TextView mRequied;
    private int errorNum = 0;

    int quantity;

    //boolean to track if product has been edited
    private boolean mProductHasChanged = false;

    private static boolean mError = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mProductHasChanged boolean to true.
     */
    //this is used to determine when someone access the view by touching the screen
    private final View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            return false;
        }
    };

    //this is used to determine if changes were made from the keyboard without touching the screen first
    //see this for reference
    //https://github.com/udacity/ud845-Pets/commit/bea7d9080f06d447892c634f6271cb83eef9762b#commitcomment-29958129
    private final View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            mProductHasChanged = true;
            return false;
        }
    };


    //launch the editor activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        //Find all views to read data from
        mNameEditText = findViewById(R.id.name);
        mDescriptionEditText = findViewById(R.id.description);
        mPrice = findViewById(R.id.price);
        mQuantity = findViewById(R.id.quantity);
        mSupplierName = findViewById(R.id.supplierName);
        mSupplierPhone = findViewById(R.id.supplierPhone);
        mCurrencySymbol = findViewById(R.id.currency_symbol);
        mPhoneBtn = findViewById(R.id.phone_icon);
        mPlusBtn = findViewById(R.id.plus_button);
        mMinusBtn = findViewById(R.id.minus_button);
        mDelete = findViewById(R.id.delete_product);
        mRequied = findViewById(R.id.required_label);

        //set on touch listeners for these fields
        mNameEditText.setOnKeyListener(mKeyListener);
        mNameEditText.setOnTouchListener(mTouchListener);

        mDescriptionEditText.setOnKeyListener(mKeyListener);
        mDescriptionEditText.setOnTouchListener(mTouchListener);

        mPrice.setOnKeyListener(mKeyListener);
        mPrice.setOnTouchListener(mTouchListener);

        mQuantity.setOnKeyListener(mKeyListener);
        mQuantity.setOnTouchListener(mTouchListener);


        mSupplierName.setOnKeyListener(mKeyListener);
        mSupplierName.setOnTouchListener(mTouchListener);

        mSupplierPhone.setOnKeyListener(mKeyListener);
        mSupplierPhone.setOnTouchListener(mTouchListener);

        //to make sure the price field is formatted to 2 decimals
        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tmpNumber = s.toString();
                //if the user entered a decimal
                // limit number of digits after it to 2 places
                if(tmpNumber.contains(".") && tmpNumber.substring(tmpNumber.indexOf(".")+1).length()>2){
                    mPrice.setText(tmpNumber.substring(0,tmpNumber.length()-1));
                    mPrice.setSelection(mPrice.getText().length());
                }
            }
        });

        //no leading zeros for quantity and handling button status
        mQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //quantity can't start with 0
                if (s.toString().length() > 1 && s.toString().startsWith("0")) {
                    s.clear();
                }
                //enable minus/plus buttons if quantity is not equal to 0 and is not null
                if (!s.toString().equals("0") || !s.toString().equals("")){
                    mPlusBtn.setEnabled(true);
                    mPlusBtn.setColorFilter(getResources().getColor(R.color.green));
                    mMinusBtn.setEnabled(true);
                    mMinusBtn.setColorFilter(getResources().getColor(R.color.red));
                }
                //if there is no value for quantity, disable plus and minus buttons
                if(s.toString().equals("") || s.toString().equals("0") ){
                    mPlusBtn.setEnabled(false);
                    mPlusBtn.setColorFilter(getResources().getColor(R.color.disable_gray));
                    mMinusBtn.setEnabled(false);
                    mMinusBtn.setColorFilter(getResources().getColor(R.color.disable_gray));
                }
            }
        });

        //phone number formatting
        mSupplierPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        PhoneNumberUtils.formatNumber(s.toString(), "US");
                    } else {
                        PhoneNumberUtils.formatNumber(s.toString());
                    }
                }
        });

        //set onClicklistener for phone icon
        mPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToPhoneApp();
            }
        });

        //onclicklistener for quantity buttons
        mPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked_quantity(view);
            }
        });

        mMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked_quantity(view);
            }
        });

        //set on click listener for delete product
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteConfirmationDialog();
            }
        });

        // Examine the intent that was used to launch this activity
        // in order to figure out if we're creating a new product or editing an existing one.
        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();
        if (mCurrentProductUri == null) {
            // This is a new product, so change the app bar to say "Add a product"
            setTitle(getString(R.string.add_product));

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a product that hasn't been created yet.)
            invalidateOptionsMenu();

            //set minus/plu button to gray and disable since there is not data yet for the quantity
            mPlusBtn.setEnabled(false);
            mPlusBtn.setColorFilter(getResources().getColor(R.color.disable_gray));
            mMinusBtn.setEnabled(false);
            mMinusBtn.setColorFilter(getResources().getColor(R.color.disable_gray));

        } else {
            // Otherwise this is an existing product, so change app bar to say "Edit product"
            setTitle(getString(R.string.edit_product));

            // Initialize a loader to read the product data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
        }



    }

    //the supplier phone number is filled in based on values from the DB
    //the user can change the phone number and elect to call the supplier
    //via the phone icon before saving their changes
    //this method will use the phone number entered in the UI
    public void sendToPhoneApp() {
        Intent sendToDialer = new Intent(Intent.ACTION_DIAL);
        sendToDialer.setData(Uri.parse("tel:" + mSupplierPhone.getText().toString().trim()));
        startActivity(sendToDialer);
    }

    public void buttonClicked_quantity(View view) {
        //do the math to increase the quantity value, only allow 999999 as the max
        quantity = Integer.parseInt(mQuantity.getText().toString());
        if(view.getId() == mPlusBtn.getId()) {
                quantity = quantity + 1;
        }else if(view.getId() == mMinusBtn.getId()){
            if (quantity > 0)
                quantity = quantity - 1;
        }

        //update quantity field with new value
        mQuantity.setText(Integer.toString(quantity));

        //update button action
        if (quantity == 0 || mQuantity.getText().toString().isEmpty() || mQuantity.getText().toString().equals("")) {
            mMinusBtn.setEnabled(false);
            mMinusBtn.setColorFilter(getResources().getColor(R.color.disable_gray));
        } else {
            mMinusBtn.setEnabled(true);
            mMinusBtn.setColorFilter(getResources().getColor(R.color.red));
        }
    }

    private void saveProduct() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim().toLowerCase();
        String descriptionString = mDescriptionEditText.getText().toString().trim().toLowerCase();
        String priceString = mPrice.getText().toString().trim();
        String quantityString = mQuantity.getText().toString().trim();
        String supplierName = mSupplierName.getText().toString().trim();
        String supplierPhone = mSupplierPhone.getText().toString().trim();

        //make sure fields are not null or empty string, if so, post error to user
        validateField(nameString, mNameEditText);
        validateField(priceString, mPrice);
        validateField(quantityString, mQuantity);
        validateField(supplierName, mSupplierName);
        validateField(supplierPhone, mSupplierPhone);

        if(errorNum > 0){
            mRequied.setTextColor(getResources().getColor(R.color.red));
        }else {
            mRequied.setTextColor(getResources().getColor(R.color.secondaryTextColor));


            // Create a ContentValues object where column names are the keys,
            // and product attributes from the editor are the values.
            ContentValues values = new ContentValues();
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME, nameString);
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION, descriptionString);
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE, priceString);
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantityString);
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierName);
            values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE, supplierPhone);


            // Determine if this is a new or existing product by checking if product uri is null or not
            if (mCurrentProductUri == null) {
                // This is a NEW product, so insert a new product into the provider,
                // returning the content URI for the new product.


                Uri newUri = getContentResolver().insert(InventoryContract.ProductEntry.CONTENT_URI, values);


                // Show a toast message depending on whether or not the insertion was successful.
                if (newUri == null) {
                    // If the new content URI is null, then there was an error with insertion.
                    Toast.makeText(this, getString(R.string.editor_insert_product_failed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the insertion was successful and we can display a toast.
                    Toast.makeText(this, getString(R.string.editor_insert_product_successful),
                            Toast.LENGTH_SHORT).show();
                }
            } else {

                // Otherwise this is an EXISTING product, so update the product with content URI: product uri
                // and pass in the new ContentValues. Pass in null for the selection and selection args
                // because mCurrentProductUri will already identify the correct row in the database that
                // we want to modify.

                int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);


                // Show a toast message depending on whether or not the update was successful.
                if (rowsAffected == 0) {
                    // If no rows were affected, then there was an error with the update.
                    Toast.makeText(this, getString(R.string.editor_update_product_failed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(this, getString(R.string.editor_update_product_successful),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new product, hide the "Delete" menu item.
        if (mCurrentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save product to database
                saveProduct();
                // Exit activity if there are no input errors
                if(errorNum == 0 ) {
                    finish();
                }
                errorNum = 0;
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Pop up confirmation dialog for deletion
                deleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the product hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                unsavedChangesDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        // If the product hasn't changed, continue with handling back button press
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }
        // Show dialog that there are unsaved changes
        unsavedChangesDialog();
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
                InventoryContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                InventoryContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentProductUri,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of product attributes that we're interested in
            int productIdIndex = cursor.getColumnIndex(InventoryContract.ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int descriptionColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String description = cursor.getString(descriptionColumnIndex);
            String price = cursor.getString(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            String supplierName = cursor.getString(supplierNameColumnIndex);
            String supplierPhone = cursor.getString(supplierPhoneColumnIndex);

            //configure price text
            //make sure priceString has 2 decimals
            if(price.contains(".")&& price.substring(price.indexOf(".")+1).length()==1){
                //only 1 number after the decimal, so add a 0
                price = price + "0";
            }
            if(!price.contains(".")){
                //there are no decimals in this number, so add one decimal and two 0's
                price = price + ".00";
            }

            // Update the views on the screen with the values from the database
            mNameEditText.setText(name);
            mDescriptionEditText.setText(description);
            mPrice.setText(price);
            mQuantity.setText(Integer.toString(quantity));
            mSupplierName.setText(supplierName);
            mSupplierPhone.setText(supplierPhone);
            mCurrencySymbol.setText(currencySymbol);

            //disable minus button if quantity is 0
            if(quantity == 0){
                mMinusBtn.setEnabled(false);
                mMinusBtn.setColorFilter(getResources().getColor(R.color.disable_gray));

            }else{
                mPlusBtn.setEnabled(true);
                mPlusBtn.setColorFilter(getResources().getColor(R.color.green));
                mMinusBtn.setEnabled(true);
                mMinusBtn.setColorFilter(getResources().getColor(R.color.red));
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mNameEditText.setText("");
        mDescriptionEditText.setText("");
        mPrice.setText("");
        mQuantity.setText("");
        mSupplierName.setText("");
        mSupplierPhone.setText("");
    }


     //Show a dialog that warns the user there are unsaved changes that will be lost
     //if they continue leaving the editor.
    private void unsavedChangesDialog(){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //2. add buttons
        builder.setPositiveButton(R.string.discard, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked discard button
                NavUtils.navigateUpFromSameTask(EditorActivity.this);
            }
        });
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked keep editing
            }
        });
        // 3. set message and title
        builder.setMessage(R.string.unsaved_changes_dialog_msg);

        // 4. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Prompt the user to confirm that they want to delete this product.
     */
    private void deleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked the "Delete" button, so delete the Product.

                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the product.
                dialog.dismiss();
            }
        });

        builder.setMessage(R.string.delete_dialog_msg);

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the product in the database.
     */
    private void deleteProduct() {
        // Only perform the delete if this is an existing product.
        if (mCurrentProductUri != null) {
            // Call the ContentResolver to delete the product at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentProductUri
            // content URI already identifies the product that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentProductUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }

    //checks that field is not empty
    private void validateField(String input, EditText view){
        if(input.isEmpty() || input.equals("")) {
            view.setHintTextColor(getResources().getColor(R.color.red));
            errorNum = errorNum + 1;
        }
    }

    //used to format the supplier phone number
    public static String formatPhone(String phone) {
        String formattedNumber = null;
        Log.i("format phone", "build version: " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            formattedNumber = PhoneNumberUtils.formatNumber(phone, "US");
            return formattedNumber;
        } else {
            formattedNumber = PhoneNumberUtils.formatNumber(phone);
            return formattedNumber;
        }
    }

}
