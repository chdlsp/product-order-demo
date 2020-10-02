package net.class101.homework1.service;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.repository.ProductInfoRepository;
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
    public List<ProductInfoEntity> saveProductInfoEntityList(List<ProductInfoEntity> productInfoEntityList) {

        productInfoEntityList.forEach(productInfoEntity -> productInfoRepository.save(
                ProductInfoEntity.builder()
                        .productNumber(productInfoEntity.getProductNumber())
                        .productTypeEnum(productInfoEntity.getProductTypeEnum())
                        .productName(productInfoEntity.getProductName())
                        .productPrice(productInfoEntity.getProductPrice())
                        .productStock(productInfoEntity.getProductStock())
                        .build()));

        return productInfoEntityList;
    }

    // productNumber 로 객체 찾기
    public ProductInfoEntity findProductInfoEntityByProductNumber(String productNumber) {

        Optional<ProductInfoEntity> byProductNumber = productInfoRepository.findByProductNumber(productNumber);

        byProductNumber.orElseThrow(() -> new WrongProductNumberException(productNumber + " does not exists"));

        return byProductNumber.get();

    }

}
