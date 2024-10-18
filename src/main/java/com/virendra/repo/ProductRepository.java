package com.virendra.repo;

import com.virendra.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public List<Product> findByName(String name) {
        return find("name", name).list();
    }


    public List<Product> getAllProducts() {
       return listAll();
    }

    public List<Product> getAllProducts2(Sort sort) {
        return listAll(sort);

        /*return listAll().stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());*/
    }
}
