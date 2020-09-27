package net.class101.homework1.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.class101.homework1.domain.enums.ProductTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
public class ProductInfoEntity {

    @Id
    private String productNumber;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productTypeEnum;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal productStock;

    @Builder
    public ProductInfoEntity(String productNumber,
                             ProductTypeEnum productTypeEnum,
                             String productName,
                             BigDecimal productPrice,
                             BigDecimal productStock) {

        this.productNumber = productNumber;
        this.productTypeEnum = productTypeEnum;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

}
