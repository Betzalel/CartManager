package edu.gatech.seclass.scm.service.impl;

import java.util.List;

import edu.gatech.seclass.scm.dao.ProductDao;
import edu.gatech.seclass.scm.model.Product;
import edu.gatech.seclass.scm.service.ProductService;
import edu.gatech.seclass.scm.utils.AppConstants;
import edu.gatech.seclass.scm.utils.ObjectFactory;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public class ProductServiceImpl implements ProductService{

    private ProductDao productDao;

    @Override
    public List<Product> getAllProducts(){
        return getProductDao().getAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return getProductDao().getProductById(productId);
    }


    public ProductDao getProductDao() {
        return (ProductDao) ObjectFactory.getInstanceOf(AppConstants.ProductDao);
    }


}
