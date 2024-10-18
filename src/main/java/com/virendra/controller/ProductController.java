package com.virendra.controller;

import com.virendra.entity.Product;
import com.virendra.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Inject
    ProductService productService;


    @PostMapping("/product")
    public Response createProduct(Product product) {
        productService.addProduct(product);
        return Response.ok().build();
    }

    //@GetMapping("/products")
    public Response getProducts() {
        return Response.ok(productService.getAllProducts("id", "asc")).build();
    }

    @GetMapping("/products/{name}")
    public Response getProducts(@PathVariable("name") String name) {
        List<Product> productByName = productService.getProductByName(name);
        if(productByName.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found for :"+name).build();
        }
        log.info("Product by name "+ productByName);
        return Response.ok(productByName).build();
    }

    @PutMapping("/products")
    public Response updateProduct(Product product) {
        Optional<Product> updateProduct = productService.updateProduct(product);
        return updateProduct.map(r -> Response.status(Response.Status.ACCEPTED)).orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Product not found for update")).build();
    }

    @DeleteMapping("/products/{id}")
    public Response deleteProduct(@PathVariable("id") Integer id) {
        if(productService.deleteProductById(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Product not found for id :"+id).build();
    }

    @GetMapping("/products/checkStock")
    public Response checkStock(@QueryParam(value = "id") Integer id, @QueryParam(value = "quantity") Integer quantity) {
        Product product = productService.findById(id);
        if(product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found for id :"+id).build();
        }
        if(product.getQuantity() >= quantity){
            return Response.ok("In Stock").build();
        }
        return Response.ok("Out of Stock").build();
    }

    @GetMapping("/products")
    public Response getProducts(@QueryParam(value = "key") String key, @QueryParam(value = "sort") String sortDir) {
        return Response.ok(productService.getAllProducts(key, sortDir)).build();
    }
}
