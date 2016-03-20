package edu.gatech.seclass.scm.dao;

import java.util.List;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;

/**
 * Created by shivendrasrivastava on 10/29/15.
 */
public interface OrderDao {

    public List<Order> getOrdersByCustomer(Customer customer);

    public void insertOrderAndOrderEntry(Customer customer, Order order);
}
