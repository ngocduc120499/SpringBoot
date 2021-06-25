package com.example.demo.service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;

@Service
public class ProductService {
    private final ProductDAO productDAO;
    @Autowired
    public ProductService(@Qualifier("fakeProDAO") ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public Product createProduct(Product product){
        Product product1 = productDAO.createProduct(product);
        return product1;
    }
    public List<Product> getAllProduct(){
        return productDAO.getAllProduct();
    }
    public Product getProductbyId(long id){
        return productDAO.getProductByID(id);
    }
    public Product updateProduct(Product product){
        return productDAO.updateProduct(product);
    }
   public void deleteProduct(long id){
       productDAO.deleteProduct(id);
   }
}
