package com.example.android.inventorymaster;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.inventorymaster.database.InventoryContract;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

class InventoryCursorAdapter extends CursorAdapter{

    //use for formatting quantiy and currency values
    Locale locale = Locale.getDefault();
    String currencySymbol = Currency.getInstance(locale).getSymbol();
    NumberFormat nf = NumberFormat.getInstance(locale);

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item_2, parent, false);
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
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView quantityTextView = view.findViewById(R.id.quantity_label);
        ImageButton plusBtn = view.findViewById(R.id.plus_button);
        ImageButton minusBtn = view.findViewById(R.id.minus_button);

        // Find the columns of product attributes that we're interested in
        int idColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);

        // Read the product attributes from the Cursor for the current product
        long productDbId = cursor.getLong(idColumnIndex);
        String productName = cursor.getString(nameColumnIndex);
        String productPrice = currencySymbol + nf.format(Integer.parseInt(cursor.getString(priceColumnIndex)));
        String productQuantity = nf.format(Integer.parseInt(cursor.getString(quantityColumnIndex)));

        // Update the TextViews with the attributes for the current product
        nameTextView.setText(productName);
        priceTextView.setText(productPrice);
        quantityTextView.setText(productQuantity);

        //setup tag for plus and minus buttons
        plusBtn.setTag(productDbId);
        minusBtn.setTag(productDbId);
    }

}
