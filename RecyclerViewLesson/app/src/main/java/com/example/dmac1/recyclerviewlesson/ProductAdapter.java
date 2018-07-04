// this project was created with the help from, https://www.youtube.com/watch?v=a4o9zFfyIM4
//  this is the Product adapter which is used to create and populate the RecyclerView
//  it has two basic parts, The RecyclerView adapter and the RecyclerView holder
// 1. create adaptor class (public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
// 2. create view holder class (class ProductViewHolder extends RecyclerView.ViewHolder
// 3. add a constructor to the view holder (right click, select Generate, select constructor)
// 4. implement the methods for the adapter class ((right click, select generate, select implement methods)
// 5. define a context variable object (the context will be needed by the view inflator)
// 6. define a product list variable object (to hold the product array)
// 7. create a constructor for the above variable objects (right click, select generate, select constructor, select all options)
// 8. add the layout inflator details to the onCreateViewHolder method
// 9. add the UI elements to the view holder class created at step 2
// 10. add the productList size to the getItmeCount method so the array size is returned
// 11. bind the data from the Product array and the viewholder
//
// in the the Main Activity, or whichever activity needs the recyclerView adapter
// create an instance of this ProductAdapter and set the adapter to the RecyclerView
// 13. add the RecyclerView object, add the Product Adapter Object, add the List of Products array
// 14. define the layout manager for the recyclerview
// 15. add products to the products array
// 16. create adater object and set context and product list
// 17. pass the adapter to the recyclerview


package com.example.dmac1.recyclerviewlesson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmac1.recyclerviewlesson.Product;
import com.example.dmac1.recyclerviewlesson.R;

import java.util.List;

// the adapter needs to define the view holder class, step 1
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    //need to set the context variable
    private Context productContext;

    //need to also set the product list variable
    private List<Product> productList;

    public ProductAdapter(Context productContext, List<Product> productList) {
        this.productContext = productContext;
        this.productList = productList;
    }

    // ---- these are the methods ---
    // this method will return an instance of the ProductViewHolder class, which is defined below and was created with step 2

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(productContext);
        View view = inflater.inflate(R.layout.list_layout,null);
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    // this method binds the data to the viewholder instance created above
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        // step 11
        Product product = productList.get(position);
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
    }

    // this method returns the size of the product list
    @Override
    public int getItemCount() {

        // step 10
        return productList.size();
    }
    // ---- end of methods ---


    // the view holder class goes inside the adapter and is used by the product view holder create method, created as part of step 2
    class ProductViewHolder extends RecyclerView.ViewHolder{

        // step 9
        TextView textViewTitle, textViewDesc, textViewRating, textViewPrice;

        //the view holder needs a constructor
        public ProductViewHolder(View itemView) {
            super(itemView);

            // step 9
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
