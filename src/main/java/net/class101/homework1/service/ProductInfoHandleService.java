package net.class101.homework1.service;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductInfoHandleService {

    private final ProductInfoRepository productInfoRepository;

    public ProductInfoHandleService(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

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

}
