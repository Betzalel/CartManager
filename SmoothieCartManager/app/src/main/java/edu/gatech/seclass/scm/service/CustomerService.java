package edu.gatech.seclass.scm.service;

import java.util.List;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Transaction;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public interface CustomerService {

    public List<Customer> getAllCustomers();

    public Customer getCurrentCustomer();

    public Customer getCustomerByQRCode();

    public Customer getCustomerById(String customerId);

    public List<Transaction> getTransactionForCustomer(Customer customer);
}
