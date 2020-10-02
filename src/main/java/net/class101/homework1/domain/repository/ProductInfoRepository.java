package net.class101.homework1.domain.repository;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, String> {

    Optional<ProductInfoEntity> findByProductNumber(String productNumber);
}
