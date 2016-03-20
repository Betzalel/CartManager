package edu.gatech.seclass.scm.model;

import android.util.Log;

/**
 * Created by rhouck on 10/30/15.
 */
public class ProductListView {
    private String productName;
    private int    productCount;
    private int    productId;
    private Double    price;

    private String msg = "ProductListView : ";

    public ProductListView(int productId, String productName, Double price) {
        this.productName = productName;
        this.productId   = productId;
        this.price = price;
        productCount     = 0;

        Log.i(msg, "Create for " + this.productName);
    }

    public void increaseCount() {
        Log.i(msg, "Count increase");
        productCount++;
    }

    public void decreaseCount() {
        Log.i(msg, "Count decrease");
        productCount--;
    }

    public int getCount() {
        return productCount;
    }

    public String getCountString() { return Integer.toString(productCount);}

    public void setCountString(int count){
        this.productCount = count;
    }

    public String getName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
