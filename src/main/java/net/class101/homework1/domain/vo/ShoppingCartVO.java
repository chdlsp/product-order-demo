package net.class101.homework1.domain.vo;

import lombok.*;
import net.class101.homework1.domain.enums.ProductTypeEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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

    private int orderCount;


}
