package edu.gatech.seclass.scm.service.impl;

import android.util.Log;

import java.util.Date;

import edu.gatech.seclass.scm.dao.OrderDao;
import edu.gatech.seclass.scm.dao.TransactionDao;
import edu.gatech.seclass.scm.model.CreditCard;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.service.CartService;
import edu.gatech.seclass.scm.service.PaymentProcessingService;
import edu.gatech.seclass.scm.service.RewardService;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class PaymentServiceImpl implements PaymentProcessingService {

    @Override
    public CreditCard readCard() {

        String cardInfo = CreditCardService.readCard();

        if(!"ERR".equals(cardInfo)){
            CreditCard creditCard = parseCardInfo(cardInfo);
            return creditCard;
        }
        return null;
    }

    @Override
    public boolean processPayment(Customer customer, Order order, CreditCard creditCard) {

        boolean isSuccess = PaymentService.processTransaction(creditCard.getFirstName(),
                creditCard.getLastName(), creditCard.getCcNumber(),
                new Date(), creditCard.getSecurityCode(),
                50.0);//order.getTotalPrice());
        Log.w("SmoothieCartManager :::", " Card Payment is "+isSuccess);
        if(isSuccess){
            getCartService().deductReward(customer, order);
            getRewardService().calculateReward(customer, order);
            getOrderDao().insertOrderAndOrderEntry(customer, order);
            insertTransaction(customer, order, creditCard);
            return true;
        }
        return false;
    }

    private void insertTransaction(Customer customer, Order order, CreditCard creditCard){
        Transaction transaction = populateTransaction(customer, order, creditCard);
        getTransactionDao().insertTransaction(transaction);
    }

    private Transaction populateTransaction(Customer customer, Order order, CreditCard creditCard){
        Transaction transaction = new Transaction();
        transaction.setCustomerID(customer.getCustomerID());
        transaction.setProductID("");
        transaction.setProductDesc("");
        transaction.setAmount(order.getTotalPrice());
        transaction.setCreditCardNo(creditCard.getCcNumber());
        transaction.setDiscount(order.getDiscount());
        return transaction;
    }

    private CreditCard parseCardInfo(String cardInfo){
        String[] cardInfoArray = cardInfo.split("#");

        CreditCard creditCard = new CreditCard();
        creditCard.setFirstName(cardInfoArray[0]);
        creditCard.setLastName(cardInfoArray[1]);
        creditCard.setCcNumber(cardInfoArray[2]);
        creditCard.setExpirationDate(cardInfoArray[3]);
        creditCard.setSecurityCode(cardInfoArray[4]);

        return creditCard;
    }


    private TransactionDao getTransactionDao(){
        return (TransactionDao) ObjectFactory.getInstanceOf(AppConstants.TransactionDao);
    }

    private RewardService getRewardService(){
        return (RewardService) ObjectFactory.getInstanceOf(AppConstants.RewardService);
    }

    private CartService getCartService(){
        return (CartService) ObjectFactory.getInstanceOf(AppConstants.CartService);
    }

    private OrderDao getOrderDao(){
        return (OrderDao) ObjectFactory.getInstanceOf(AppConstants.OrderDao);
    }
}
