package edu.gatech.seclass.scm.service;

import java.util.List;

import edu.gatech.seclass.scm.model.Product;

/**
 * Created by shivendrasrivastava on 10/28/15.
 */
public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(int productId);
}
