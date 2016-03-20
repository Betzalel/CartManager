package edu.gatech.seclass.scm.model;

import java.text.DecimalFormat;

/**
 * Created by shivendrasrivastava on 10/18/15.
 */
public class Transaction {

    private String customerID;

    private Double amount;

    private Double discount;

    private String creditCardNo;

    private String productID;

    private String productDesc;

    private String authResponse;

    private String transactionID;

    private String orderID;

    private String date;

    private String discountType;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getAuthResponse() {
        return authResponse;
    }

    public void setAuthResponse(String authResponse) {
        this.authResponse = authResponse;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {



                DecimalFormat myFormat = new DecimalFormat("$###,###,###.00");
                String sDiscount = myFormat.format(discount);
                String sAmount = myFormat.format(amount);
                return "Date -" + date + "\n" +
                        "     Amount - " + sAmount + "\n" +
                        "     Discount - " + sDiscount + "\n" +
                        "     Discount Type - " + ((discountType.isEmpty()) ? "N/A" : discountType);

    }
}
