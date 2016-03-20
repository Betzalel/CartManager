package edu.gatech.seclass.scm.service;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public interface RewardService {

    public void calculateReward(Customer customer, Order order);
}
