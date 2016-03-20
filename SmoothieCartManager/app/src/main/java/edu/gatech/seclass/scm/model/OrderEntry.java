package edu.gatech.seclass.scm.model;

/**
 * Created by shivendrasrivastava on 10/18/15.
 */
public class OrderEntry {

    private int orderEntryID;

    private int orderID;

    private String customerID;

    private int productID;

    private String productDesc;

    private Double discount;

    private String discountType;

    private Double orderEntryPrice;

    private int qty;

    public int getOrderEntryID() {
        return orderEntryID;
    }

    public void setOrderEntryID(int orderEntryID) {
        this.orderEntryID = orderEntryID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getOrderEntryPrice() {
        return orderEntryPrice;
    }

    public void setOrderEntryPrice(Double orderEntryPrice) {
        this.orderEntryPrice = orderEntryPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
