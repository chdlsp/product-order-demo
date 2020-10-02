package net.class101.homework1.service;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import net.class101.homework1.domain.vo.ShoppingCartVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HandleShoppingCartInfoService {

    public void checkClassIsAlreadyExists(List<ShoppingCartVO> shoppingCartList,
                                          ProductInfoEntity productInfoEntityByProductNumber) {
        for (ShoppingCartVO shoppingCartVO : shoppingCartList) {
            // 이미 동일한 클래스를 선택했는지 여부 확인
            if (shoppingCartVO.getProductNumber().equals(productInfoEntityByProductNumber.getProductNumber())) {
                System.out.println("동일한 클래스를 추가할 수 없습니다.");
            }
        }
    }

    public boolean checkClassOrderCount(ProductInfoEntity productInfoEntityByProductNumber, int intOrderCount) {
        return productInfoEntityByProductNumber.getProductTypeEnum().equals(ProductTypeEnum.KLASS)
                && intOrderCount == 1;
    }
}
