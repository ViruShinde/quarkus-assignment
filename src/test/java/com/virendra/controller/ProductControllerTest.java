package com.virendra.controller;

import com.virendra.entity.Product;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ProductControllerTest {

    @Test
    void testCreateProduct() {
        Product product = new Product( null, "Apple I phone2", "IOS device", 50000, 10);

        given()
                .contentType("application/json")
                .body(product)
                .when().post("/product")
                .then()
                .statusCode(200);
    }

    @Test
    void testGetProducts() {
        given()
                .when().get("/products?key=id&sort=asc")
                .then()
                .body("size()", is(6))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void testGetProductByName() {
        given()
                .when().get("/products/Apple I phone")
                .then()
                .body("size()", is(1))
                .body("name", hasItem("Apple I phone"))
                .body("[0].price", equalTo(Float.valueOf(50000)))
                .body("quantity", hasItem(10))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(1L, "Apple I phone", "IOS device", 40000, 5);

        given()
                .contentType("application/json")
                .body(product)
                .when().put("/products")
                .then()
                .statusCode(202);
    }

    @Test
    void testDeleteProduct() {
        given()
                .when().delete("/products/1")
                .then()
                .statusCode(200);
    }

    @Test
    void testCheckStock_InStock() {
        given()
                .when().get("/products/checkStock?id=1&quantity=5")
                .then()
                .statusCode(200)
                .body(is("In Stock"));
    }

    @Test
    void testCheckStock_OutOfStock() {
        given()
                .when().get("/products/checkStock?id=1&quantity=500")
                .then()
                .statusCode(200)
                .body(is("Out of Stock"));
    }
}
