package com.example.demo.api;

import com.example.demo.model.Product;
import com.example.demo.service.PersonService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct (){
        return ResponseEntity.ok(productService.getAllProduct());
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product){
        return ResponseEntity.ok(this.productService.createProduct(product));
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProductbyID(@PathVariable long id){
        return ResponseEntity.ok(this.productService.getProductbyId(id));
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
        product.setId(id);
        return ResponseEntity.ok(this.productService.updateProduct(product));
    }
    @DeleteMapping(path = "{id}")
    public HttpStatus deleteProduct(@PathVariable long id){
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }

}
