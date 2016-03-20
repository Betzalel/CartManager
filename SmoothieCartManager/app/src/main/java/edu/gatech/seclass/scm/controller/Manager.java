package edu.gatech.seclass.scm.controller;

import android.util.Log;

import java.util.List;

import edu.gatech.seclass.scm.model.CreditCard;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.model.Product;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.service.CartService;
import edu.gatech.seclass.scm.service.CustomerService;
import edu.gatech.seclass.scm.service.PaymentProcessingService;
import edu.gatech.seclass.scm.service.ProductService;
import edu.gatech.seclass.scm.service.RewardService;
import edu.gatech.seclass.scm.service.SendEmailService;
import edu.gatech.seclass.scm.service.impl.CartServiceImpl;
import edu.gatech.seclass.scm.service.impl.CustomerServiceImpl;
import edu.gatech.seclass.scm.service.impl.EmailServiceImpl;
import edu.gatech.seclass.scm.service.impl.PaymentServiceImpl;
import edu.gatech.seclass.scm.service.impl.ProductServiceImpl;
import edu.gatech.seclass.scm.service.impl.RewardServiceImpl;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/21/15.
 */
public class Manager {

    String msg = "Manager : ";

    public Customer[] getAllCustomers(){
        List<Customer> customerList = getCustomerService().getAllCustomers();

        return (Customer[]) customerList.toArray(new Customer[customerList.size()]);
    }

    public List<Product> getAllProducts(){
        List<Product> productList = getProductService().getAllProducts();
        return productList;
    }

    public Transaction[] getCustomerTransactions() {
        List<Transaction> transactions =  getCustomerService().getTransactionForCustomer(getAppSession().getCurrentCustomer());

        return (Transaction[]) transactions.toArray(new Transaction[transactions.size()]);
    }

    public boolean scanQRForCustomer(){
        Customer customer = getCustomerService().getCustomerByQRCode();
        if(null == customer){
            return false;
        }
        getAppSession().setCurrentCustomer(customer);
        return true;
    }

    public void addToOrder(int productId, int qty){

        Customer customer = getAppSession().getCurrentCustomer();

        Order order = getAppSession().getOrder();

        if(order == null){
            order = new Order();
        }

        Product product = getProductService().getProductById(productId);

        order = getCartService().addToOrder(customer, order, product , qty);

        getAppSession().setOrder(order);
    }

    public void removeFromOrder(int productId, int qty){
        Customer customer = getAppSession().getCurrentCustomer();
        Order order = getAppSession().getOrder();

        Product product  = getProductService().getProductById(productId);

        order = getCartService().removeFromOrder(customer, order, product);

        getAppSession().setOrder(order);
    }

    public void calculateOrder(){
        Order order = getAppSession().getOrder();
        Customer customer = getAppSession().getCurrentCustomer();

        order = getCartService().calculateOrder(customer, order);
        getAppSession().setOrder(order);
    }

    public void setCustomer(String customerId) {
        Log.i(msg, "Setting Current Customer to Customer ID: " + customerId);
        getAppSession().setCurrentCustomer(getCustomerService().getCustomerById(customerId));
    }

    public void clearCustomer() {
        Log.i(msg, "Clearing Current Customer");
        getAppSession().setCurrentCustomer(null);
    }

    public boolean customerSet() {
        if (getAppSession().getCurrentCustomer() != null)
            return true;
        return false;
    }

    public String getCustomerName() {
        String toRet = null;
        if (getAppSession().getCurrentCustomer() != null) {
            toRet = getAppSession().getCurrentCustomer().getName();
        }
        return toRet;
    }

    public String getCustomerId() {
        String toRet = null;
        if (getAppSession().getCurrentCustomer() != null) {
            toRet = getAppSession().getCurrentCustomer().getCustomerID();
        }
        return toRet;
    }

    public CreditCard scanCard(){
        CreditCard card = getPaymentService().readCard();
        return card;
    }

    public boolean processPayment(Customer customer, Order order, CreditCard card){

        return getPaymentService().processPayment(customer, order, card);
    }

    public ProductService getProductService() {
        return (ProductServiceImpl) ObjectFactory.getInstanceOf(AppConstants.ProductService);
    }

    public CustomerService getCustomerService() {
        return (CustomerServiceImpl) ObjectFactory.getInstanceOf(AppConstants.CustomerService);
    }

    public RewardService getRewardService() {
        return (RewardServiceImpl) ObjectFactory.getInstanceOf(AppConstants.RewardService);
    }

    public PaymentProcessingService getPaymentService() {
        return (PaymentServiceImpl) ObjectFactory.getInstanceOf(AppConstants.PaymentProcessingService);
    }

    public SendEmailService getEmailService() {
        return (EmailServiceImpl) ObjectFactory.getInstanceOf(AppConstants.SendEmailService);
    }

    public CartService getCartService(){
        return (CartServiceImpl) ObjectFactory.getInstanceOf(AppConstants.CartService);
    }

    public AppSession getAppSession(){
        return (AppSession) ObjectFactory.getInstanceOf(AppConstants.AppSession);
    }
}
