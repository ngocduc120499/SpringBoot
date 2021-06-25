package com.example.demo.dao;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("fakeProDAO")
@Transactional
public class ProductDataAccessService implements ProductDAO {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDB  = this.productRepository.findById(product.getId());
        if(productDB.isPresent()){
            Product productUpdate = productDB.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;
        }else {
            throw new ResourceNotFoundException("Khong the them duoc" + product.getId());
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductByID(long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        if(productDb.isPresent()){
            return productDb.get();
        }else throw new ResourceNotFoundException("Khong tim thay"+productDb);

    }

    @Override
    public void deleteProduct(long productId) {
        Optional<Product> productDB = this.productRepository.findById(productId);
        if(productDB.isPresent()) {
            this.productRepository.delete(productDB.get());
        }else {
            throw new ResourceNotFoundException("Khong tim thay id: " + productId);
        }
    }
}
