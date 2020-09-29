package net.class101.homework1.domain.vo;

import lombok.*;
import net.class101.homework1.domain.enums.ProductTypeEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartVO {

    @Id
    private String productNumber;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productTypeEnum;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal productStock;
    
}
