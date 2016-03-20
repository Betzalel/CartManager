package edu.gatech.seclass.scm.dao;

import java.util.List;

import edu.gatech.seclass.scm.model.Customer;

/**
 * Created by shivendrasrivastava on 10/27/15.
 */
public interface CustomerDao {

    public List<Customer> getAllCustomers();

    public Customer insertCustomer(Customer customer);

    public Customer updateCustomerForReward(Customer customer);

    public Customer updateCustomerForDiscount(Customer customer);

    public Customer getCustomerById(String qrCode);
}
