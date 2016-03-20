package edu.gatech.seclass.scm.model;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by shivendrasrivastava on 10/18/15.
 */
public class Order {

    private int orderID;

    private String customerID;

    private Double totalPrice;

    private Double subtotalPrice;

    private Double tax;

    private String discountType;

    private int orderEntryID;

    private Double discount;

    private String orderPlaceDate;

    private List<OrderEntry> orderEntries;

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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getOrderEntryID() {
        return orderEntryID;
    }

    public void setOrderEntryID(int orderEntryID) {
        this.orderEntryID = orderEntryID;
    }

    public Double getDiscount() {

        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getOrderPlaceDate() {
        return orderPlaceDate;
    }

    public void setOrderPlaceDate(String orderPlaceDate) {
        this.orderPlaceDate = orderPlaceDate;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public Double getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(Double subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }
}
