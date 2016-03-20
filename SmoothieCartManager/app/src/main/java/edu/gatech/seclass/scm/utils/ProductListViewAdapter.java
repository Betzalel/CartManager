package edu.gatech.seclass.scm.utils;

/**
 * Created by rhouck on 10/30/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.scm.R;
import edu.gatech.seclass.scm.model.ProductListView;

public class ProductListViewAdapter extends ArrayAdapter<ProductListView> {

    private final Context context;
    private final ArrayList<ProductListView> productListViews;
    private String msg = "ProductListViewAdapter : ";
    public ProductListViewAdapter(Context context, ArrayList<ProductListView> productListViews) {
        super(context, R.layout.product_item, productListViews);

        this.context = context;
        this.productListViews = productListViews;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.product_item, parent, false);

        TextView nameView  = (TextView) rowView.findViewById(R.id.item_title);
        TextView countView = (TextView) rowView.findViewById(R.id.item_counter);

        nameView.setText(productListViews.get(position).getName());
        countView.setText(productListViews.get(position).getCountString());

        return rowView;
    }

}
