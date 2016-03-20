package edu.gatech.seclass.scm.dao;

import java.util.List;

import edu.gatech.seclass.scm.model.Product;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public interface ProductDao {

    public List<Product> getAllProducts();

    public Product getProductById(int productId);
}
