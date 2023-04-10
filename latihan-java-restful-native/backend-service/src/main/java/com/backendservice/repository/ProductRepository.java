package com.backendservice.repository;

import com.backendservice.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    String packageName = "new com.backendservice.entity.";

    @Query(
            value = "SELECT " + packageName + "ProductEntity" +
                    "(p.productName, p.productQuantity, p.productAmount) " +
                    "FROM product p"
    )
    Page<ProductEntity> getAllProduct(Pageable pageable);
}
