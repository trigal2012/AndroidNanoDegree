package com.example.android.inventorymaster;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.inventorymaster.database.InventoryContract;

import java.util.Currency;
import java.util.Locale;

class InventoryCursorAdapter extends CursorAdapter {

    //use for formatting quantity and currency values
    private final Locale locale = Locale.getDefault();
    private final String currencySymbol = Currency.getInstance(locale).getSymbol();

    private Context context;


    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView quantityTextView = view.findViewById(R.id.quantity);
        final ImageButton plusBtn = view.findViewById(R.id.plus_button);
        final ImageButton minusBtn = view.findViewById(R.id.minus_button);


        // Find the columns of product attributes that we're interested in
        int idColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the product attributes from the Cursor for the current product
        int productDbId = cursor.getInt(idColumnIndex);
        String productName = cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        String productQuantity = cursor.getString(quantityColumnIndex);

        // Update the TextViews with the attributes for the current product
        nameTextView.setText(productName);
        quantityTextView.setText(productQuantity);

        //make sure priceString has 2 decimals
        if (productPrice.contains(".") && productPrice.substring(productPrice.indexOf(".") + 1).length() == 1) {
            //only 1 number after the decimal, so add a 0
            productPrice = productPrice + "0";
        }
        if (!productPrice.contains(".")) {
            //there are no decimals in this number, so add one decimal and two 0's
            productPrice = productPrice + ".00";
        }

        //add currency symbol
        productPrice = currencySymbol + productPrice;
        priceTextView.setText(productPrice);

        //setup tag for plus and minus buttons
        plusBtn.setTag(productDbId);
        minusBtn.setTag(productDbId);

        plusBtn.setEnabled(true);
        plusBtn.setColorFilter(context.getResources().getColor(R.color.green));


        if (productQuantity.equals("0") || productQuantity.isEmpty() || productQuantity.equals("")) {
            minusBtn.setEnabled(false);
            minusBtn.setColorFilter(context.getResources().getColor(R.color.disable_gray));
        } else {
            minusBtn.setEnabled(true);
            minusBtn.setColorFilter(context.getResources().getColor(R.color.red));
        }

        //seton click listeners for quantity buttons
        plusBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof ViewActivity) {
                    ((ViewActivity) context).buttonClicked_plus(view);
                }
            }
        });

        minusBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof ViewActivity) {
                    ((ViewActivity) context).buttonClicked_minus(view);
                }
            }
        });
    }

}
