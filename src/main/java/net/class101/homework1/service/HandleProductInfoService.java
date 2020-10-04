package net.class101.homework1.service;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import net.class101.homework1.domain.vo.ShoppingCartVO;
import net.class101.homework1.exception.SnapshotInfoTooLongException;
import net.class101.homework1.exception.WrongProductNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HandleProductInfoService {

    private final ProductInfoRepository productInfoRepository;

    public HandleProductInfoService(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    // 전체 목록 저장 (Runner)
    public void saveProductInfoEntityList(List<ProductInfoEntity> productInfoEntityList) {

        productInfoEntityList.forEach(productInfoEntity -> productInfoRepository.save(
                ProductInfoEntity.builder()
                        .productNumber(productInfoEntity.getProductNumber())
                        .productTypeEnum(productInfoEntity.getProductTypeEnum())
                        .productName(productInfoEntity.getProductName())
                        .productPrice(productInfoEntity.getProductPrice())
                        .productStock(productInfoEntity.getProductStock())
                        .build()));
    }

    // productNumber 로 객체 찾기
    public ProductInfoEntity findProductInfoEntityByProductNumber(String productNumber) {

        Optional<ProductInfoEntity> byProductNumber = productInfoRepository.findByProductNumber(productNumber);

        byProductNumber.orElseThrow(() -> new WrongProductNumberException(productNumber + " does not exists"));

        return byProductNumber.get();

    }

    // 장바구니에 담은 금액과 프로덕트 클래스 정보의 가격이 다른 경우
    public void checkLatestProductPriceInfo(ShoppingCartVO shoppingCartVO, ProductInfoEntity productInfoEntity) {
        if(!productInfoEntity.getProductPrice().equals(shoppingCartVO.getOrderPrice())) {
            throw new SnapshotInfoTooLongException("상품 가격 정보가 변경되었습니다. 새로고침 후 다시 이용해 주세요.");
        }
    }

    public List<ProductInfoEntity> getProductInfoLists() {

        List<ProductInfoEntity> repositoryAll = productInfoRepository.findAll();

        return repositoryAll;
    }
}
