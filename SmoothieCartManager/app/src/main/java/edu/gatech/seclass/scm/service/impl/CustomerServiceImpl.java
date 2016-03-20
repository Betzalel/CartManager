package edu.gatech.seclass.scm.service.impl;

import java.util.List;

import edu.gatech.seclass.scm.dao.CustomerDao;
import edu.gatech.seclass.scm.dao.TransactionDao;
import edu.gatech.seclass.scm.dao.impl.CustomerDaoImpl;
import edu.gatech.seclass.scm.dao.impl.TransactionDaoImpl;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.service.CustomerService;
import edu.gatech.seclass.scm.service.QRCodeScanService;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class CustomerServiceImpl implements CustomerService{

    private QRCodeScanService qrCodeScanService;

    private CustomerDao customerDao;

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = getCustomerDao().getAllCustomers();

        return customerList;
    }

    @Override
    public Customer getCurrentCustomer() {
        return null;
    }

    @Override
    public Customer getCustomerByQRCode() {

        String qrCode = getQrCodeScanService().scanCustomerCard();

        if("ERR".equals(qrCode)){
            return null;
        }

        Customer customer = getCustomerDao().getCustomerById(qrCode);

        return customer;
    }

    @Override
    public List<Transaction> getTransactionForCustomer(Customer customer) {
        List<Transaction> transactionList = getTransactionDao().getTransactionsForCustomer(customer);
        return transactionList;
    }

    public Customer getCustomerById(String customerId) {
        return null;
    }

    public QRCodeScanService getQrCodeScanService() {
        return (QRCodeScanServiceImpl) ObjectFactory.getInstanceOf(AppConstants.QRCodeScanService);
    }

    public CustomerDao getCustomerDao() {
        return (CustomerDaoImpl) ObjectFactory.getInstanceOf(AppConstants.CustomerDao);
    }

    public TransactionDao getTransactionDao(){
        return (TransactionDaoImpl) ObjectFactory.getInstanceOf(AppConstants.TransactionDao);
    }

}
