package com.example.android.newsapppart1;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

//creates the news item row
class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    /**
     * Returns a view that displays information about the item at the given position
     * in the list of items.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the item at the given position in the list of items
        Item currentItem = getItem(position);

        //set the section name textview
        TextView section = listItemView.findViewById(R.id.section);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String sectionName = Objects.requireNonNull(currentItem).getSectionName();
            section.setText(sectionName);
            int color = 0;
            switch (sectionName) {
                case "Sport":
                    color = ContextCompat.getColor(getContext(), R.color.primaryColor);
                    section.setBackgroundColor(color);
                    break;
                case "Life and style":
                    color = ContextCompat.getColor(getContext(), R.color.primaryLightColor);
                    section.setBackgroundColor(color);
                    break;
                case "Books":
                    color = ContextCompat.getColor(getContext(), R.color.primaryDarkColor);
                    section.setBackgroundColor(color);
                    break;
                default:
                    color = ContextCompat.getColor(getContext(), R.color.defaultBackgroundColor);
                    section.setBackgroundColor(color);
                    break;
            }
        }

        //set the title text view
        TextView title = listItemView.findViewById(R.id.title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            title.setText(Objects.requireNonNull(currentItem).getWebTitle());
        }

        //set the contributor text view
        TextView contributor = listItemView.findViewById(R.id.contributor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            contributor.setText(Objects.requireNonNull(currentItem).getContributor());
        }

        //set the date text view
        TextView date = listItemView.findViewById(R.id.date);
        //String formattedDate = formatDate(currentItem.getPublicationDate());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            date.setText(formatDate(Objects.requireNonNull(currentItem).getPublicationDate()));
        }

        return listItemView;

    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(String date1) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        Date date = null;
        try {
            date = inputFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }
}