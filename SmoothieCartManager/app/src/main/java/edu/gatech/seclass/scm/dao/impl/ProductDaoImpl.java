package edu.gatech.seclass.scm.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.scm.dao.ProductDao;
import edu.gatech.seclass.scm.model.Product;
import edu.gatech.seclass.scm.sqlite.DBHelper;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.DBUtils;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public class ProductDaoImpl implements ProductDao {

    private DBUtils dbUtils;

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();

        SQLiteDatabase db = getDbUtils().getDatabase();

        Cursor cursor = db.rawQuery(DBUtils.GET_ALL_PRODUCTS, null);

        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setProductID(Integer.parseInt(cursor.getString(0)));
                product.setProductDesc(cursor.getString(1));
                product.setPrice(Double.parseDouble(cursor.getString(2)));
                product.setSize(cursor.getString(3));
                product.setQtyAvailable(Integer.parseInt(cursor.getString(4)));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }

    @Override
    public Product getProductById(int productId) {
        SQLiteDatabase db = getDbUtils().getDatabase();

        String[] columns = new String[]{DBHelper.COLUMN_PRODUCT_ID, DBHelper.COLUMN_PRODUCT_DESC, DBHelper.COLUMN_PRICE};

        String whereClause = "product_id = '"+productId+"'";

        Cursor cursor = db.query(DBHelper.PRODUCT, columns, whereClause, null, null, null, null);

        Product product = new Product();
        if(cursor.moveToFirst()) {
            do {
                product.setProductID(Integer.parseInt(cursor.getString(0)));
                product.setProductDesc(cursor.getString(1));
                product.setPrice(getDbUtils().parseString(cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return product;
    }


    public DBUtils getDbUtils() {
        return (DBUtils) ObjectFactory.getInstanceOf(AppConstants.DBUtils);
    }

}
