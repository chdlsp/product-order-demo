package net.class101.homework1.application;

import com.sun.tools.javac.util.Assert;
import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Class101RunnerTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    void findAllFromRepository() {

        // given
        List<ProductInfoEntity> productInfoRepositoryAll = productInfoRepository.findAll();

        // when
        Assert.checkNonNull(productInfoRepositoryAll);

        // then
        assertEquals(20, productInfoRepositoryAll.size());
    }

}