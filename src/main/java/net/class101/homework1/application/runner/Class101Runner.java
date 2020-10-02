package net.class101.homework1.application.runner;

import lombok.extern.slf4j.Slf4j;
import net.class101.homework1.domain.entity.ProductInfoEntity;
import net.class101.homework1.domain.enums.ProductTypeEnum;
import net.class101.homework1.domain.repository.ProductInfoRepository;
import net.class101.homework1.domain.vo.ShoppingCartVO;
import net.class101.homework1.service.ProductInfoHandleService;
import org.hibernate.WrongClassException;
import org.hibernate.engine.jdbc.spi.SchemaNameResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class Class101Runner implements ApplicationRunner {

    private final ProductInfoHandleService productInfoHandleService;

    public Class101Runner(ProductInfoHandleService productInfoHandleService) {
        this.productInfoHandleService = productInfoHandleService;
    }

    @Override
    public void run(ApplicationArguments args) {

        log.info("==========================");
        log.info("Class101Runner Started ...");
        log.info("==========================");

        List<ProductInfoEntity> productInfoEntityList = new ArrayList<>();

        productInfoEntityList.add(new ProductInfoEntity("16374", ProductTypeEnum.KLASS, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드는 비법", BigDecimal.valueOf(151950), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("26825", ProductTypeEnum.KLASS, "해금, 특별하고 아름다운 나만의 반려악기", BigDecimal.valueOf(114500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("65625", ProductTypeEnum.KLASS, "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패드 드로잉", BigDecimal.valueOf(174500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("91008", ProductTypeEnum.KIT, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", BigDecimal.valueOf(28000), BigDecimal.valueOf(10)));
        productInfoEntityList.add(new ProductInfoEntity("9236", ProductTypeEnum.KIT, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", BigDecimal.valueOf(9900), BigDecimal.valueOf(22)));
        productInfoEntityList.add(new ProductInfoEntity("55527", ProductTypeEnum.KLASS, "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실", BigDecimal.valueOf(299500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("2344", ProductTypeEnum.KLASS, "일상 유튜버 숏뚜의 감성을 그대로. 영화같은 브이로그 제작법", BigDecimal.valueOf(184500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("60538", ProductTypeEnum.KIT, "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전", BigDecimal.valueOf(135800), BigDecimal.valueOf(7)));
        productInfoEntityList.add(new ProductInfoEntity("78156", ProductTypeEnum.KIT, "일상을 따뜻하게 채우는 썬캐쳐와 무드등", BigDecimal.valueOf(45000), BigDecimal.valueOf(16)));
        productInfoEntityList.add(new ProductInfoEntity("53144", ProductTypeEnum.KLASS, "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다", BigDecimal.valueOf(249500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("78311", ProductTypeEnum.KLASS, "사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨", BigDecimal.valueOf(209500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("97166", ProductTypeEnum.KIT, "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이 기획한 3가지 수채화 키트", BigDecimal.valueOf(96900), BigDecimal.valueOf(5)));
        productInfoEntityList.add(new ProductInfoEntity("83791", ProductTypeEnum.KLASS, "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문", BigDecimal.valueOf(178500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("58395", ProductTypeEnum.KIT, "선과 여백으로 채우는 2020년 캘린더와 엽서", BigDecimal.valueOf(18620), BigDecimal.valueOf(31)));
        productInfoEntityList.add(new ProductInfoEntity("39712", ProductTypeEnum.KIT, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", BigDecimal.valueOf(17600), BigDecimal.valueOf(8)));
        productInfoEntityList.add(new ProductInfoEntity("96588", ProductTypeEnum.KLASS, "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸", BigDecimal.valueOf(234500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("42031", ProductTypeEnum.KIT, "나만의 음악을 만들기 위한 장비 패키지", BigDecimal.valueOf(209000), BigDecimal.valueOf(2)));
        productInfoEntityList.add(new ProductInfoEntity("45947", ProductTypeEnum.KLASS, "일러스트레이터 집시의 매력적인 얼굴 그리기", BigDecimal.valueOf(249500), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("74218", ProductTypeEnum.KLASS, "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기", BigDecimal.valueOf(191600), BigDecimal.valueOf(99999)));
        productInfoEntityList.add(new ProductInfoEntity("28448", ProductTypeEnum.KLASS, "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의 모든 것", BigDecimal.valueOf(52200), BigDecimal.valueOf(99999)));

        List<ProductInfoEntity> productInfoEntities = productInfoHandleService.saveProductInfoEntityList(productInfoEntityList);

        Scanner scanner = new Scanner(System.in);

        String next = "";

        while (true) {

            System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
            next = scanner.nextLine(); // input

            // order - 상품리스트 출력
            if (next.equals("o")) {

                String productNumber = "";
                String orderCount = "";

                for (int i = 0; i < productInfoEntities.size(); i++) {

                    if (i == 0) {
                        System.out.print("상품번호    판매가격    재고수    상품명\n");
                    }
                    ProductInfoEntity productInfoEntity = productInfoEntities.get(i);

                    System.out.printf("%8s  %8d  %8d   %-50s \n"
                            , productInfoEntity.getProductNumber()
                            , productInfoEntity.getProductPrice().intValue()
                            , productInfoEntity.getProductStock().intValue()
                            , productInfoEntity.getProductName());
                }

                List<ShoppingCartVO> shoppingCartList = new ArrayList<>(); // 장바구니 리스트 초기화

                while (true) {

                    System.out.print("\n상품번호: ");
                    productNumber = scanner.nextLine();

                    if (productNumber.equals(" ")) {
                        // 주문 완료
                        System.out.println("---------------------------------------");
                        for (ShoppingCartVO shoppingCartVO : shoppingCartList) {
                            System.out.printf("%s - %8d개 \n"
                                    , shoppingCartVO.getProductName(), shoppingCartVO.getOrderCount());
                        }

                        break;

                    } else {
                        // 주문 계속
                        log.info("productNumber : " +  productNumber);

                        // productNumber 정보 가져오기
                        ProductInfoEntity productInfoEntityByProductNumber =
                                productInfoHandleService.findProductInfoEntityByProductNumber(productNumber);

                        System.out.print("수량: ");
                        orderCount = scanner.nextLine();

                        ShoppingCartVO shoppingCartVO = ShoppingCartVO.builder()
                                .productNumber(productNumber)
                                .productTypeEnum(productInfoEntityByProductNumber.getProductTypeEnum())
                                .orderCount(Integer.parseInt(orderCount))
                                .productName(productInfoEntityByProductNumber.getProductName())
                                .build();

                        shoppingCartList.add(shoppingCartVO);
                    }
                }


            } else if (next.equals("q")) {
                System.out.print("고객님의 주문 감사합니다.");
                break;
            }
        }
    }
}
