package edu.gatech.seclass.scm.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.seclass.scm.dao.OrderDao;
import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.model.OrderEntry;
import edu.gatech.seclass.scm.sqlite.DBHelper;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.DBUtils;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/29/15.
 */
public class OrderDaoImpl implements OrderDao {



    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> orderList = new ArrayList<Order>();
        long milliseconds = (long) 365 * 24 * 60 * 60 * 1000;
        Date currentDate = new Date(System.currentTimeMillis());
        Date yearBackDate = new Date(currentDate.getTime() - milliseconds);

        SQLiteDatabase db = getDbUtils().getDatabase();
        String[] columns = new String[]{DBHelper.COLUMN_CUSTOMER_ID, DBHelper.COLUMN_ORDER_ID, DBHelper.COLUMN_TOTAL_PRICE};
        String whereClause = " customer_id = '" +customer.getCustomerID() + "' and order_place_date between '" + currentDate + "' and '" + yearBackDate + "'";
        Cursor cursor = db.query(DBHelper.ORDER, columns, whereClause, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Order order = new Order();
                order.setCustomerID(cursor.getString(0));
                order.setOrderID(Integer.parseInt(cursor.getString(1)));
                order.setTotalPrice(getDbUtils().parseString(cursor.getString(2)));
                orderList.add(order);
            }while(cursor.moveToNext());
        }
        return orderList;
    }

    @Override
    public void insertOrderAndOrderEntry(Customer customer, Order order) {
        SQLiteDatabase db = getDbUtils().getDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CUSTOMER_ID, customer.getCustomerID());
        values.put(DBHelper.COLUMN_SUBTOTAL_PRICE, order.getSubtotalPrice());
        values.put(DBHelper.COLUMN_TOTAL_PRICE, order.getTotalPrice());
        values.put(DBHelper.COLUMN_TAX, order.getTax());
        values.put(DBHelper.COLUMN_DISCOUNT, order.getDiscount());
        values.put(DBHelper.COLUMN_DISCOUNT_TYPE, order.getDiscountType());
        values.put(DBHelper.COLUMN_ORDER_PLACE_DATE, order.getOrderPlaceDate());
        db.insert(DBHelper.ORDER, null, values);

        for(OrderEntry orderEntry : order.getOrderEntries()){
            ContentValues entryValues = new ContentValues();
            entryValues.put(DBHelper.COLUMN_CUSTOMER_ID, customer.getCustomerID());
            entryValues.put(DBHelper.COLUMN_PRODUCT_ID, orderEntry.getProductID());
            entryValues.put(DBHelper.COLUMN_PRODUCT_DESC, orderEntry.getProductDesc());
            entryValues.put(DBHelper.COLUMN_DISCOUNT, orderEntry.getDiscount());
            entryValues.put(DBHelper.COLUMN_DISCOUNT_TYPE, orderEntry.getDiscountType());
            entryValues.put(DBHelper.COLUMN_ORDER_ENTRY_PRICE, orderEntry.getOrderEntryPrice());
            db.insert(DBHelper.ORDER_ENTRY, null, entryValues);
        }
    }

    public DBUtils getDbUtils() {
        return (DBUtils) ObjectFactory.getInstanceOf(AppConstants.DBUtils);
    }

}
