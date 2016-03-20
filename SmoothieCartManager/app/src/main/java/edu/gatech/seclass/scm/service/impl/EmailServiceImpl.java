package edu.gatech.seclass.scm.service.impl;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.service.SendEmailService;
import edu.gatech.seclass.scm.utils.AppUtils;
import edu.gatech.seclass.services.EmailService;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class EmailServiceImpl implements SendEmailService {

    @Override
    public void sendEmailForGoldStatus(Customer customer) {
        EmailService.sendMail(customer.getEmailAddress(), AppUtils.EMAIL_SALUTATION, AppUtils.getEmailBodyForGoldStatus(customer));
    }

    @Override
    public void sendEmailForCreditEarned(Customer customer) {
        EmailService.sendMail(customer.getEmailAddress(), AppUtils.EMAIL_SALUTATION, AppUtils.getEmailBodyForCreditEarned(customer));
    }
}
