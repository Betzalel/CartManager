package edu.gatech.seclass.scm.service.impl;

import java.util.Date;
import java.util.List;

import edu.gatech.seclass.scm.dao.CustomerDao;
import edu.gatech.seclass.scm.dao.OrderDao;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.service.RewardService;
import edu.gatech.seclass.scm.service.SendEmailService;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class RewardServiceImpl implements RewardService {

    private final Double limit = 50.0;

    private final Double yearLimit = 500.0;

    private final int goldStatusDiscount = 5;

    private final Double reward = 5.0;

    @Override
    public void calculateReward(Customer customer, Order order) {
        if(order.getTotalPrice() > limit){
            customer.setReward(customer.getReward() + reward);
            Date currentDate = new Date();
            customer.setRewardDate(currentDate.toString());
            getEmailService().sendEmailForCreditEarned(customer);
            //getCustomerDao().updateCustomerForReward(customer);
        }

        Double totalOrderValue = calculateYearToDateOrderPrice(getOrderDao().getOrdersByCustomer(customer));

        if(totalOrderValue > yearLimit){
            getEmailService().sendEmailForGoldStatus(customer);
            customer.setDiscountPercentage(goldStatusDiscount);
            //getCustomerDao().updateCustomerForDiscount(customer);
        }
    }

    private Double calculateYearToDateOrderPrice(List<Order> orderList){
        Double totalOrderValue = 0.0;
        for(Order order : orderList){
            totalOrderValue += order.getTotalPrice();
        }
        return totalOrderValue;
    }

    public SendEmailService getEmailService(){
        return (SendEmailService)ObjectFactory.getInstanceOf(AppConstants.SendEmailService);
    }

    public CustomerDao getCustomerDao(){
        return (CustomerDao) ObjectFactory.getInstanceOf(AppConstants.CustomerDao);
    }

    public OrderDao getOrderDao(){
        return (OrderDao) ObjectFactory.getInstanceOf(AppConstants.OrderDao);
    }
}
