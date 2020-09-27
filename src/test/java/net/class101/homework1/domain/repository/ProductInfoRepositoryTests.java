package net.class101.homework1.domain.repository;

import com.sun.tools.javac.util.Assert;
import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.then;

@DataJpaTest
class ProductInfoRepositoryTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    void newProductSave() {

        // given
        String productNumber = "11111";
        ProductTypeEnum kit = ProductTypeEnum.KIT;
        String productName = "test_product";
        BigDecimal productPrice = BigDecimal.valueOf(10000);
        BigDecimal productStock = BigDecimal.valueOf(10000);

        ProductInfoEntity productInfoEntity = ProductInfoEntity.builder()
                .productNumber(productNumber)
                .productTypeEnum(kit)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();

        ProductInfoEntity infoEntity = productInfoRepository.save(productInfoEntity);

        // when
        Assert.checkNonNull(infoEntity.getProductNumber());

        // then
        then("11111").equals(infoEntity.getProductNumber()); // pk 확인

    }

}