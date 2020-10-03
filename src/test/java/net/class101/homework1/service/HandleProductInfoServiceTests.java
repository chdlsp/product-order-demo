package net.class101.homework1.service;

import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class HandleProductInfoServiceTests {

    @InjectMocks
    private HandleProductInfoService handleProductInfoService;

    @Mock
    private ProductInfoRepository productInfoRepository;

    @Mock
    ProductInfoEntity productInfoEntity;

    List<ProductInfoEntity> productInfoEntityList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        productInfoEntityList.add(new ProductInfoEntity("16374", ProductTypeEnum.KLASS, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드는 비법", BigDecimal.valueOf(151950), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("26825", ProductTypeEnum.KLASS, "해금, 특별하고 아름다운 나만의 반려악기", BigDecimal.valueOf(114500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("65625", ProductTypeEnum.KLASS, "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패드 드로잉", BigDecimal.valueOf(174500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("91008", ProductTypeEnum.KIT,   "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", BigDecimal.valueOf(28000), BigDecimal.valueOf(10)));
        productInfoEntityList.add(new ProductInfoEntity("9236",  ProductTypeEnum.KIT,   "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", BigDecimal.valueOf(9900), BigDecimal.valueOf(22)));
        productInfoEntityList.add(new ProductInfoEntity("55527", ProductTypeEnum.KLASS, "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실", BigDecimal.valueOf(299500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("2344",  ProductTypeEnum.KLASS, "일상 유튜버 숏뚜의 감성을 그대로. 영화같은 브이로그 제작법", BigDecimal.valueOf(184500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("60538", ProductTypeEnum.KIT,   "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전", BigDecimal.valueOf(135800), BigDecimal.valueOf(7)));
        productInfoEntityList.add(new ProductInfoEntity("78156" ,ProductTypeEnum.KIT,   "일상을 따뜻하게 채우는 썬캐쳐와 무드등", BigDecimal.valueOf(45000), BigDecimal.valueOf(16)));
        productInfoEntityList.add(new ProductInfoEntity("53144", ProductTypeEnum.KLASS, "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다", BigDecimal.valueOf(249500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("78311", ProductTypeEnum.KLASS, "사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨", BigDecimal.valueOf(209500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("97166", ProductTypeEnum.KIT,   "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이 기획한 3가지 수채화 키트", BigDecimal.valueOf(96900), BigDecimal.valueOf(5)));
        productInfoEntityList.add(new ProductInfoEntity("83791", ProductTypeEnum.KLASS, "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문", BigDecimal.valueOf(178500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("58395", ProductTypeEnum.KIT,   "선과 여백으로 채우는 2020년 캘린더와 엽서", BigDecimal.valueOf(18620), BigDecimal.valueOf(31)));
        productInfoEntityList.add(new ProductInfoEntity("39712", ProductTypeEnum.KIT,   "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", BigDecimal.valueOf(17600), BigDecimal.valueOf(8)));
        productInfoEntityList.add(new ProductInfoEntity("96588", ProductTypeEnum.KLASS, "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸", BigDecimal.valueOf(234500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("42031", ProductTypeEnum.KIT,   "나만의 음악을 만들기 위한 장비 패키지", BigDecimal.valueOf(209000), BigDecimal.valueOf(2)));
        productInfoEntityList.add(new ProductInfoEntity("45947", ProductTypeEnum.KLASS, "일러스트레이터 집시의 매력적인 얼굴 그리기", BigDecimal.valueOf(249500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("74218", ProductTypeEnum.KLASS, "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기", BigDecimal.valueOf(191600), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("28448", ProductTypeEnum.KLASS, "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의 모든 것", BigDecimal.valueOf(52200), BigDecimal.valueOf(99999)));

    }

    @Test
    void saveProductInfoEntityList() {

        // setup
        ProductInfoRepository productInfoRepository = Mockito.mock(ProductInfoRepository.class);

        // given
        given(productInfoRepository.existsById(any())).willReturn(false);
        given(productInfoRepository.save(any())).willReturn(productInfoEntity);

        // when
        List<ProductInfoEntity> productInfoEntities = handleProductInfoService.saveProductInfoEntityList(productInfoEntityList);

        // then
        assertNotNull(productInfoEntities);
        assertEquals(20, productInfoEntities.size());
    }
}