package edu.gatech.seclass.scm.utils;


import edu.gatech.seclass.scm.controller.AppSession;
import edu.gatech.seclass.scm.controller.Manager;
import edu.gatech.seclass.scm.dao.CustomerDao;
import edu.gatech.seclass.scm.dao.OrderDao;
import edu.gatech.seclass.scm.dao.ProductDao;
import edu.gatech.seclass.scm.dao.TransactionDao;
import edu.gatech.seclass.scm.dao.impl.CustomerDaoImpl;
import edu.gatech.seclass.scm.dao.impl.OrderDaoImpl;
import edu.gatech.seclass.scm.dao.impl.ProductDaoImpl;
import edu.gatech.seclass.scm.dao.impl.TransactionDaoImpl;
import edu.gatech.seclass.scm.service.CartService;
import edu.gatech.seclass.scm.service.CustomerService;
import edu.gatech.seclass.scm.service.PaymentProcessingService;
import edu.gatech.seclass.scm.service.ProductService;
import edu.gatech.seclass.scm.service.QRCodeScanService;
import edu.gatech.seclass.scm.service.RewardService;
import edu.gatech.seclass.scm.service.SendEmailService;
import edu.gatech.seclass.scm.service.impl.CartServiceImpl;
import edu.gatech.seclass.scm.service.impl.CustomerServiceImpl;
import edu.gatech.seclass.scm.service.impl.EmailServiceImpl;
import edu.gatech.seclass.scm.service.impl.PaymentServiceImpl;
import edu.gatech.seclass.scm.service.impl.ProductServiceImpl;
import edu.gatech.seclass.scm.service.impl.QRCodeScanServiceImpl;
import edu.gatech.seclass.scm.service.impl.RewardServiceImpl;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class ObjectFactory {

    private static Manager manager;

    private static QRCodeScanService qrCodeScanService;

    private static CustomerService customerService;

    private static PaymentProcessingService paymentProcessingService;

    private static SendEmailService emailService;

    private static RewardService rewardService;

    private static CustomerDao customerDao;

    private static DBUtils dbUtils;

    private static CartService cartService;

    private static ProductDao productDao;

    private static ProductService productService;

    private static TransactionDao transactionDao;

    private static OrderDao orderDao;

    private static AppSession appSession;

    public static Object getInstanceOf(String className){
        if(className.equals(AppConstants.Manager)){
            return getManager();
        }
        if(className.equals(AppConstants.QRCodeScanService)){
            return getQrCodeScanServiceInstance();
        }
        if(className.equals(AppConstants.CustomerService)){
            return getCustomerServiceInstance();
        }
        if(className.equals(AppConstants.SendEmailService)){
            return getEmailServiceInstance();
        }
        if(className.equals(AppConstants.RewardService)){
            return getRewardServiceInstance();
        }
        if(className.equals(AppConstants.PaymentProcessingService)){
            return getPaymentProcessingServiceInstance();
        }
        if(className.equals(AppConstants.CustomerDao)){
            return getCustomerDaoInstance();
        }
        if(className.equals(AppConstants.DBUtils)){
            return getDBUtilsInstance();
        }
        if(className.equals(AppConstants.CartService)){
            return getCartServiceInstance();
        }
        if(className.equals(AppConstants.ProductDao)){
            return getProductDaoInstance();
        }
        if(className.equals(AppConstants.ProductService)){
            return getProductServiceInstance();
        }
        if(className.equals(AppConstants.TransactionDao)){
            return getTransactionDaoInstance();
        }
        if(className.equals(AppConstants.OrderDao)){
            return getOrderDaoInstance();
        }
        if(className.equals(AppConstants.AppSession)){
            return getAppSessionInstance();
        }
        return null;
    }

    /**
     *
     * @return Manager
     */
    private static Manager getManager(){
        if(manager == null){
            synchronized (Manager.class){
                if(manager == null){
                    manager = new Manager();
                }
            }
        }
        return manager;
    }

    /**
     *
     * @return QRCodeScanServiceImpl
     */
    private static QRCodeScanService getQrCodeScanServiceInstance(){
        if(qrCodeScanService == null){
            synchronized (QRCodeScanServiceImpl.class){
                if(qrCodeScanService == null){
                    qrCodeScanService = new QRCodeScanServiceImpl();
                }
            }
        }
        return qrCodeScanService;
    }

    /**
     *
     * @return CustomerServiceImpl
     */
    private static CustomerService getCustomerServiceInstance(){
        if(customerService == null){
            synchronized (CustomerServiceImpl.class){
                if(customerService == null){
                    customerService = new CustomerServiceImpl();
                }
            }
        }
        return customerService;
    }

    /**
     *
     * @return PaymentServiceImpl
     */
    private static PaymentProcessingService getPaymentProcessingServiceInstance(){
        if(paymentProcessingService == null){
            synchronized (PaymentServiceImpl.class){
                if(paymentProcessingService == null){
                    paymentProcessingService = new PaymentServiceImpl();
                }
            }
        }
        return paymentProcessingService;
    }

    /**
     *
     * @return EmailServiceImpl
     */
    private static SendEmailService getEmailServiceInstance(){
        if(emailService == null){
            synchronized (EmailServiceImpl.class){
                if(emailService == null){
                    emailService = new EmailServiceImpl();
                }
            }
        }
        return emailService;
    }

    /**
     *
     * @return RewardServiceImpl
     */
    private static RewardService getRewardServiceInstance(){
        if(rewardService == null){
            synchronized (RewardServiceImpl.class){
                if(rewardService == null){
                    rewardService = new RewardServiceImpl();
                }
            }
        }
        return rewardService;
    }

    /**
     *
     * @return CustomerDaoImpl
     */
    private static CustomerDao getCustomerDaoInstance(){
        if(customerDao == null){
            synchronized (CustomerDaoImpl.class){
                if(customerDao == null){
                    customerDao = new CustomerDaoImpl();
                }
            }
        }
        return customerDao;
    }

    /**
     *
     * @return DBUtils
     */
    private static DBUtils getDBUtilsInstance(){
        if(dbUtils == null){
            synchronized (DBUtils.class){
                if(dbUtils == null){
                    dbUtils = new DBUtils();
                }
            }
        }
        return dbUtils;
    }

    /**
     *
     * @return CartService
     */
    private static CartService getCartServiceInstance(){
        if(cartService == null){
            synchronized (CartServiceImpl.class){
                if(cartService == null){
                    cartService = new CartServiceImpl();
                }
            }
        }
        return cartService;
    }

    /**
     *
     * @return ProductDao
     */
    private static ProductDao getProductDaoInstance(){
        if(productDao == null){
            synchronized (ProductDaoImpl.class){
                if(productDao == null) {
                    productDao = new ProductDaoImpl();
                }
            }
        }
        return productDao;
    }

    /**
     *
     * @return ProductService
     */
    private static ProductService getProductServiceInstance(){
        if(productService == null){
            synchronized (ProductServiceImpl.class){
                if(productService == null){
                    productService = new ProductServiceImpl();
                }
            }
        }
        return productService;
    }

    /**
     *
     * @return TransactionDao
     */
    private static TransactionDao getTransactionDaoInstance(){
        if(transactionDao == null){
            synchronized (TransactionDaoImpl.class){
                if(transactionDao ==  null){
                    transactionDao = new TransactionDaoImpl();
                }
            }
        }
        return transactionDao;
    }

    /**
     *
     * @return OrderDao
     */
    private static OrderDao getOrderDaoInstance(){
        if(orderDao == null){
            synchronized (OrderDaoImpl.class){
                if(orderDao == null){
                    orderDao = new OrderDaoImpl();
                }
            }
        }
        return orderDao;
    }

    private static AppSession getAppSessionInstance(){
        if(appSession == null){
            synchronized (AppSession.class){
                appSession = new AppSession();
            }
        }
        return appSession;
    }
}
