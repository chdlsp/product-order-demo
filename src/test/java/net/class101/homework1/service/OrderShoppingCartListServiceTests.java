package net.class101.homework1.service;

import javafx.beans.binding.BooleanExpression;
import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import net.class101.homework1.domain.vo.ShoppingCartVO;
import net.class101.homework1.exception.SoldOutException;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class OrderShoppingCartListServiceTests {

    @Autowired
    ProductInfoRepository productInfoRepository;

    OrderShoppingCartListService orderShoppingCartListService;

    @BeforeEach
    void setup() {
        this.orderShoppingCartListService = new OrderShoppingCartListService(productInfoRepository);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void updateProductStockInfo() {

        // given
        String productNumber = "58395";

        ProductInfoEntity productInfoEntity = ProductInfoEntity.builder()
                .productNumber(productNumber)
                .productName("선과 여백으로 채우는 2020년 캘린더와 엽서")
                .productTypeEnum(ProductTypeEnum.KIT)
                .productPrice(BigDecimal.valueOf(18620))
                .productStock(BigDecimal.valueOf(31))
                .build();

        ProductInfoEntity save = productInfoRepository.save(productInfoEntity);

        assertNotNull(save);
        assertEquals(save.getProductNumber(), productNumber);

        Optional<ProductInfoEntity> byInfoEntity = productInfoRepository.findByProductNumber(productNumber);
        @SuppressWarnings("OptionalGetWithoutIsPresent") ProductInfoEntity infoEntity = byInfoEntity.get();

        assertNotNull(byInfoEntity);
        assertEquals(infoEntity.getProductNumber(), productNumber);

        // when
        CountDownLatch countDownLatch = new CountDownLatch(40); // 40번 카운트 설정
        orderShoppingCartListService.setCountDownLatch(countDownLatch);

        ShoppingCartVO shoppingCartVO = ShoppingCartVO.builder()
                .productNumber(infoEntity.getProductNumber())
                .productName(infoEntity.getProductName())
                .productTypeEnum(infoEntity.getProductTypeEnum())
                .orderCount(1)
                .orderPrice(infoEntity.getProductPrice())
                .build();

        // then
        try {
            for(int i=0; i<40; i++) {
                orderShoppingCartListService.checkOrderPossible(shoppingCartVO);
            }
        } catch(SoldOutException e) {
            exception.expect(SoldOutException.class);
        }

        SoldOutException soldOutException = new SoldOutException(productNumber + " 상품번호를 구매할 수 없습니다.(재고없음)");
        String actualMessage = soldOutException.getMessage();

        String expectedMessage = productNumber + " 상품번호를 구매할 수 없습니다.(재고없음)";

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void getContainsYn() {

        // given
        ShoppingCartVO shoppingCartVOKit = ShoppingCartVO.builder()
                .productTypeEnum(ProductTypeEnum.KIT)
                .build();

        ShoppingCartVO shoppingCartVOKlass = ShoppingCartVO.builder()
                .productTypeEnum(ProductTypeEnum.KLASS)
                .build();

        // when
        String result1 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKit, ProductTypeEnum.KIT);
        String result2 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKlass, ProductTypeEnum.KLASS);
        String result3 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKit, ProductTypeEnum.KLASS);
        String result4 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKlass, ProductTypeEnum.KIT);

        // then
        assertEquals(result1, "Y");
        assertEquals(result2, "Y");
        assertEquals(result3, "N");
        assertEquals(result4, "N");

    }
}