package edu.gatech.seclass.scm.service;

import edu.gatech.seclass.scm.model.Customer;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public interface SendEmailService {

    public void sendEmailForGoldStatus(Customer customer);

    public void sendEmailForCreditEarned(Customer customer);
}
