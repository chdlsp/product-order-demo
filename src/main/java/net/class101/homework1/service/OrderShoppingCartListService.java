package net.class101.homework1.service;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import net.class101.homework1.domain.vo.ShoppingCartVO;
import net.class101.homework1.exception.SoldOutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class OrderShoppingCartListService {

    private final ProductInfoRepository productInfoRepository;

    public OrderShoppingCartListService(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean checkOrderPossible(ShoppingCartVO shoppingCartVO) {

        String productNumber = shoppingCartVO.getProductNumber();

        Optional<ProductInfoEntity> byProductNumber = productInfoRepository.findByProductNumber(productNumber);

        byProductNumber.ifPresent(productInfoEntity -> {
            int productStock = productInfoEntity.getProductStock().intValue();
            int orderStock = shoppingCartVO.getOrderCount();

            if (orderStock > productStock) {
                // 주문수량 > 재고수량 인 경우

                throw new SoldOutException(productNumber + " 상품번호를 구매할 수 없습니다.(재고없음)");

            } else if (productInfoEntity.getProductTypeEnum().equals(ProductTypeEnum.KIT)) {
                // 주문수량 <= 재고수량 인 경우 (정상)

                // 키트 상품인 경우 재고 차감 후 update 처리 (클래스는 무제한이라 가정함)
                updateProductStockInfo(productNumber, productInfoEntity, orderStock);
            }
        });

        return true;
    }

    // 키트 상품인 경우 재고 차감 후 update 처리
    private void updateProductStockInfo(String productNumber, ProductInfoEntity productInfoEntity, int orderStock) {
        ProductInfoEntity updateProductInfoEntity = ProductInfoEntity.builder()
                .productNumber(productNumber)
                .productName(productInfoEntity.getProductName())
                .productPrice(productInfoEntity.getProductPrice())
                .productTypeEnum(productInfoEntity.getProductTypeEnum())
                .productStock(productInfoEntity.getProductStock().subtract(BigDecimal.valueOf(orderStock)))
                .build();

        productInfoRepository.save(updateProductInfoEntity);
    }

    // 포함 여부 리턴
    public String getContainsYn(String containsYn, ShoppingCartVO shoppingCartVO, ProductTypeEnum productType) {

        if (containsYn.equals("N")) {
            if (shoppingCartVO.getProductTypeEnum().equals(productType)) {
                containsYn = "Y";
            }
        }
        return containsYn;
    }
}
