package edu.gatech.seclass.scm.utils;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import edu.gatech.seclass.scm.sqlite.DBHelper;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public class DBUtils {

    private SQLiteDatabase database;

    private final Double defaultValue = 0.0;

    public static final String GET_ALL_PRODUCTS = "SELECT * FROM "+ DBHelper.PRODUCT;

    public static final String GET_ALL_CUSTOMERS = "SELECT * FROM " + DBHelper.CUSTOMER;

    public static final String GET_ORDERS_FOR_CUSTOMER = "SELECT " + DBHelper.COLUMN_CUSTOMER_ID +
            ", " +
            DBHelper.COLUMN_ORDER_ID +
            ", " +
            DBHelper.COLUMN_TOTAL_PRICE +
            " FROM " +
            DBHelper.ORDER ;

    public Double parseString(String value){
        if(null == value || TextUtils.isEmpty(value)){
            return defaultValue;
        }
        return Double.parseDouble(value);
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
}
