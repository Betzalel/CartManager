package edu.gatech.seclass.scm.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.scm.dao.TransactionDao;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Transaction;
import edu.gatech.seclass.scm.sqlite.DBHelper;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.DBUtils;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public class TransactionDaoImpl implements TransactionDao {

    private DBUtils dbUtils;

    @Override
    public void insertTransaction(Transaction transaction) {

        SQLiteDatabase db = getDbUtils().getDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CUSTOMER_ID, transaction.getCustomerID());
        values.put(DBHelper.COLUMN_ORDER_ID, transaction.getOrderID());
        values.put(DBHelper.COLUMN_AMOUNT, transaction.getAmount());
        values.put(DBHelper.COLUMN_DISCOUNT, transaction.getDiscount());
        values.put(DBHelper.COLUMN_CREDIT_CARD_NO, transaction.getCreditCardNo());
        values.put(DBHelper.COLUMN_PRODUCT_ID, transaction.getProductID());
        values.put(DBHelper.COLUMN_PRODUCT_DESC, transaction.getProductDesc());
        values.put(DBHelper.COLUMN_AUTH_RESPONSE, transaction.getAuthResponse());

        db.insert(DBHelper.TRANSACTION, null, values);

    }

    @Override
    public List<Transaction> getTransactionsForCustomer(Customer customer) {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        SQLiteDatabase db = getDbUtils().getDatabase();

        String[] columns = new String[]{DBHelper.COLUMN_TRANSACTION_DATE, DBHelper.COLUMN_AMOUNT, DBHelper.COLUMN_DISCOUNT, DBHelper.COLUMN_DISCOUNT_TYPE};

        String whereClause = "customer_id = '" + customer.getCustomerID() +"'";

        Cursor cursor = db.query(DBHelper.TRANSACTION, columns, whereClause, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Transaction transaction = new Transaction();
                transaction.setDate(cursor.getString(0));
                transaction.setAmount(getDbUtils().parseString(cursor.getString(1)));
                transaction.setDiscount(getDbUtils().parseString(cursor.getString(2)));
                transaction.setDiscountType(cursor.getString(3));
                transactionList.add(transaction);
            }while(cursor.moveToNext());
        }
        return transactionList;
    }

    public DBUtils getDbUtils() {
        return (DBUtils) ObjectFactory.getInstanceOf(AppConstants.DBUtils);
    }
}
