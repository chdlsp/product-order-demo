package net.class101.homework1.service;

import javafx.beans.binding.BooleanExpression;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import net.class101.homework1.domain.vo.ShoppingCartVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderShoppingCartListServiceTests {

    @Mock
    ProductInfoRepository productInfoRepository;

    OrderShoppingCartListService orderShoppingCartListService;

    @BeforeEach
    void setup() {
        this.orderShoppingCartListService = new OrderShoppingCartListService(productInfoRepository);
    }

    @Test
    void checkOrderPossible() {
    }

    @Test
    void getContainsYn() {

        ShoppingCartVO shoppingCartVOKit = ShoppingCartVO.builder()
                .productTypeEnum(ProductTypeEnum.KIT)
                .build();

        ShoppingCartVO shoppingCartVOKlass = ShoppingCartVO.builder()
                .productTypeEnum(ProductTypeEnum.KLASS)
                .build();

        String result1 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKit, ProductTypeEnum.KIT);
        assertEquals(result1, "Y");

        String result2 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKlass, ProductTypeEnum.KLASS);
        assertEquals(result2, "Y");

        String result3 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKit, ProductTypeEnum.KLASS);
        assertEquals(result3, "N");

        String result4 = orderShoppingCartListService.getContainsYn("N", shoppingCartVOKlass, ProductTypeEnum.KIT);
        assertEquals(result4, "N");
        
    }
}