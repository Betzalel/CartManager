package edu.gatech.seclass.scm.dao;

import java.util.List;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Transaction;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public interface TransactionDao {

    public void insertTransaction(Transaction transaction);

    public List<Transaction> getTransactionsForCustomer(Customer customer);
}
