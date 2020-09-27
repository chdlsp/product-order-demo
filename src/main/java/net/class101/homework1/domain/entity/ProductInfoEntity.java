package net.class101.homework1.domain.entity;

import net.class101.homework1.domain.enums.ProductTypeEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProductInfoEntity {

    @Id
    private String ProductNumber;

    private ProductTypeEnum productTypeEnum;

    private String productName;

    private BigDecimal price;

    private BigDecimal stock;

}
