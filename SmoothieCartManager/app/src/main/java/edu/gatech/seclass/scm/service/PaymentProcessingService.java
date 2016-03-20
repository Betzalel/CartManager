package edu.gatech.seclass.scm.service;

import edu.gatech.seclass.scm.model.CreditCard;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public interface PaymentProcessingService {

    public CreditCard readCard();

    public boolean processPayment(Customer customer, Order order, CreditCard creditCard);
}
