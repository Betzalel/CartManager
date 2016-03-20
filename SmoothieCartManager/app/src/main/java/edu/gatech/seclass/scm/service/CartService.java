package edu.gatech.seclass.scm.service;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.model.Product;

/**
 * Created by shivendrasrivastava on 10/25/15.
 */
public interface CartService {

    public Order addToOrder(Customer customer, Order order, Product product, int qty);

    public Order removeFromOrder(Customer customer, Order order, Product product);

    public Order calculateOrder(Customer customer, Order order);

    public Order deductReward(Customer customer, Order order);
}
