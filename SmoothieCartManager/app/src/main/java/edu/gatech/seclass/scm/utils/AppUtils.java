package edu.gatech.seclass.scm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import edu.gatech.seclass.scm.model.Customer;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public class AppUtils {

    public static final String EMAIL_SUBJECT = "Smoothie Mart - Reward Information";

    public static final String EMAIL_SALUTATION = "Dear ";

    public static String getEmailBodyForGoldStatus(Customer customer){
        String emailBody = EMAIL_SALUTATION + customer.getName() + ", " +
                "<br>" +
                "<br>" +
                " Your recent purchase has earned you a GOLD status. <\n>" +
                " Thank you for your purchase. <\n>" +
                "<br>" +
                "<br>" +
                "Regards, <br>" +
                "Customer Services Team, <br>" +
                "Smoothie Mart";
        return emailBody;
    }

    public static String getEmailBodyForCreditEarned(Customer customer){
        String emailBody = EMAIL_SALUTATION + customer.getName() + ", " +
                "<br>" +
                "<br>" +
                " Your recent purchase has earned you a $5 credit. <\n>" +
                " Thank you for your purchase. <\n>" +
                "<br>" +
                "<br>" +
                "Regards, <br>" +
                "Customer Services Team, <br>" +
                "Smoothie Mart";
        return emailBody;
    }

    public static Date parseDate(String expDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date expiryDate = new Date();
        try {
            expiryDate = dateFormat.parse(expDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return expiryDate;
    }

    public static String generateCustomerID(){
        return UUID.randomUUID().toString();
    }

}
