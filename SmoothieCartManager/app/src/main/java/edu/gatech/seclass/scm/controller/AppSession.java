package edu.gatech.seclass.scm.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import edu.gatech.seclass.scm.model.CreditCard;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;

/**
 * Created by shivendrasrivastava on 10/29/15.
 */
public class AppSession {

    private Order currentOrder;

    private Customer currentCustomer;

    private SharedPreferences preferences;

    private CreditCard creditCard;

    private Order order;

    private Boolean isOrderComplete;

    private HashMap<Integer, Integer> productQtyMap;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    private Context context;

    public void setCurrentCustomer(Customer customer){
        if(null != customer){
            setCustomerId(customer);
        }
        this.currentCustomer = customer;
    }

    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

    private void setCustomerId(Customer customer){
        preferences.edit().putString("customer_id", customer.getCustomerID()).commit();
    }

    public String getCustomerId(){
        return preferences.getString("customer_id", "");
    }

    public Order getCurrentOrder(){
        return currentOrder;
    }

    public void setCurrentOrder(Order order){
        this.currentOrder = order;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Boolean getIsOrderComplete() {
        return isOrderComplete;
    }

    public void setIsOrderComplete(Boolean isOrderComplete) {
        this.isOrderComplete = isOrderComplete;
    }

    public HashMap<Integer, Integer> getProductQtyMap() {
        return productQtyMap;
    }

    public void setProductQtyMap(HashMap<Integer, Integer> productQtyMap) {
        this.productQtyMap = productQtyMap;
    }
}
