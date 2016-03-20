package edu.gatech.seclass.scm.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.scm.dao.CustomerDao;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.sqlite.DBHelper;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.AppUtils;
import edu.gatech.seclass.scm.utils.DBUtils;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/27/15.
 */
public class CustomerDaoImpl implements CustomerDao {

    private DBUtils dbUtils;

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<Customer>();

        SQLiteDatabase db = getDbUtils().getDatabase();

        Cursor cursor = db.rawQuery(DBUtils.GET_ALL_CUSTOMERS, null);

        if(cursor.moveToFirst()){
            do{
                Customer customer = new Customer();
                customer.setName(cursor.getString(0));
                customer.setBillingAddress(cursor.getString(1));
                customer.setEmailAddress(cursor.getString(2));
                customer.setCustomerID(cursor.getString(3));
                customer.setStatus(cursor.getString(4));
                customer.setReward(getDbUtils().parseString(cursor.getString(5)));
                customer.setRewardDate(cursor.getString(6));

                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        return customerList;
    }

    @Override
    public Customer insertCustomer(Customer customer) {

        SQLiteDatabase db = getDbUtils().getDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, customer.getName());
        values.put(DBHelper.COLUMN_BILLING_ADDRESS, customer.getBillingAddress());
        values.put(DBHelper.COLUMN_EMAIL_ADDRESS, customer.getEmailAddress());
        values.put(DBHelper.COLUMN_CUSTOMER_ID, AppUtils.generateCustomerID());
        values.put(DBHelper.COLUMN_STATUS, customer.getStatus());
        values.put(DBHelper.COLUMN_REWARD, customer.getReward());
        values.put(DBHelper.COLUMN_REWARD_DATE, customer.getRewardDate());

        db.insert(DBHelper.CUSTOMER, null, values);

        return customer;
    }

    @Override
    public Customer updateCustomerForReward(Customer customer) {

        SQLiteDatabase db = getDbUtils().getDatabase();

        ContentValues values = new ContentValues();

        String[] args = new String[]{customer.getCustomerID(), customer.getEmailAddress()};

        values.put(DBHelper.COLUMN_REWARD, customer.getReward());
        values.put(DBHelper.COLUMN_REWARD_DATE, customer.getRewardDate());

        db.update(DBHelper.CUSTOMER, values, "customer_id = ? AND email_address = ? ", args);

        return customer;
    }

    @Override
    public Customer updateCustomerForDiscount(Customer customer) {
        SQLiteDatabase db = getDbUtils().getDatabase();

        ContentValues values = new ContentValues();

        String[] args = new String[]{customer.getCustomerID(), customer.getEmailAddress()};

        values.put(DBHelper.COLUMN_DISCOUNT, customer.getDiscountPercentage());

        db.update(DBHelper.CUSTOMER, values, "customer_id = ? AND email_address = ? ", args);

        return customer;
    }

    @Override
    public Customer getCustomerById(String qrCode) {

        SQLiteDatabase db = getDbUtils().getDatabase();

        ContentValues values = new ContentValues();

        String[] columns = new String[]{DBHelper.COLUMN_NAME,
                DBHelper.COLUMN_BILLING_ADDRESS, DBHelper.COLUMN_EMAIL_ADDRESS,
                DBHelper.COLUMN_CUSTOMER_ID, DBHelper.COLUMN_STATUS, DBHelper.COLUMN_REWARD,
                DBHelper.COLUMN_REWARD_DATE, DBHelper.COLUMN_DISCOUNT};

        String whereClause = " customer_id = '" +qrCode+ "'";

        Cursor cursor = db.query(DBHelper.CUSTOMER, columns, whereClause, null, null, null, null);
        Customer customer = new Customer();
        if(cursor.moveToFirst()){
            do{
                customer.setName(cursor.getString(0));
                customer.setBillingAddress(cursor.getString(1));
                customer.setEmailAddress(cursor.getString(2));
                customer.setCustomerID(cursor.getString(3));
                customer.setStatus(cursor.getString(4));
                customer.setReward(getDbUtils().parseString(cursor.getString(5)));
                customer.setRewardDate(cursor.getString(6));
                customer.setDiscountPercentage(Integer.parseInt(cursor.getString(7)));
            }while(cursor.moveToNext());
        }

        return customer;
    }


    public DBUtils getDbUtils() {
        return (DBUtils) ObjectFactory.getInstanceOf(AppConstants.DBUtils);
    }

}
