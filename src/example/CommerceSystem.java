package example;

import java.util.List;
import java.util.Scanner;

// 설명: 커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스입니다.
public class CommerceSystem {
    List<Product> product1; // 먼저 창고 선언
    Scanner sc = new Scanner(System.in);

    CommerceSystem(List<Product> products) {
        this.product1 = products; // 그다음 저장
    }

    public void start() {
        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-13s | %9s | %-35s%n", "     상품명", "가격   ", "             설명");
            System.out.println("---------------------------------------------------------------");

            for (Product product : product1) {
                product.printInfo();
            }

            System.out.println("---------------------------------------------------------------");
            System.out.println("0. 종료          | 프로그램 종료");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
        }
    }
}
