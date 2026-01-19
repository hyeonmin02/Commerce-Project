package example;
//비즈니스 로직 클래스는 ‘계산 + 판단 + 규칙 담당’
//
//HashMap은 ‘여러 상품을 정리해서 보관하는 상자’
//
//main은 그냥:
//
//사용자 입력 받고
//
//비즈니스 로직에 시키고
//
//결과 출력하는 심부름꾼

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in); CommerceSystem으로 넘겨서 굳이 필요없음

        List<Product> products = new ArrayList<>();
        Product pr1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50);
        Product pr2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 20);
        Product pr3 = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 100);
        Product pr4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 100);

        products.add(pr1);
        products.add(pr2);
        products.add(pr3);
        products.add(pr4);

        CommerceSystem cs = new CommerceSystem(products);
        cs.start();
    }
}
//        Product pr1 = new Product();
//        pr1.name = "Galaxy S24";
//        pr1.price = 1200000;
//        pr1.explanation = "최신 안드로이드 스마트폰";
//        pr1.stock = 50;
//
//        Product pr2 = new Product();
//        pr2.name = "iPhone 16";
//        pr2.price = 1350000;
//        pr2.explanation = "Apple의 최신 스마트폰";
//        pr2.stock = 20;
//
//        Product pr3 = new Product();
//        pr3.name = "MacBook Pro";
//        pr3.price = 2400000;
//        pr3.explanation = "M3 칩셋이 탑재된 노트북";
//        pr3.stock = 100;
//
//        Product pr4 = new Product();
//        pr4.name = "AirPods Pro";
//        pr4.price = 350000;
//        pr4.explanation = "노이즈 캔슬링 무선 이어폰";
//        pr4.stock = 100;