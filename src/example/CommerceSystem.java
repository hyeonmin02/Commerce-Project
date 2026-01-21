package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// + 메인에 존재하지않는 카테고리 선택 시 예외처리 (음수값,정수가 아닌 문자값 입력시 예외처리)
//  재고처리 고객이 상품을 살때마다 재고가 줄어듬 + 재고 채워넣기 메서드 만들기 + 고객 누적금액 체크 + 등급 체크 + 예외처리

// 설명: 커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스입니다.
public class CommerceSystem {
    private final List<Category> categories = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public CommerceSystem() {
        Category clothes = new Category("의류");
        Category food = new Category("식품");
        Category electronics = new Category("전자제품");

        electronics.addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        electronics.addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 20));
        electronics.addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 100));
        electronics.addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10));
        electronics.addProduct(new Product("Galaxy Z Trifold",3590400,"두 번 접히는 혁신적인 폼팩터",5));

        clothes.addProduct(new Product("맨시티 유니폼", 160000, "푸마 맨시티 어센틱 반팔 유니폼", 10));
        clothes.addProduct(new Product("나이키 에어포스", 149000, "나이키 오프화이트 에어포스", 15));
        clothes.addProduct(new Product("스투시 후드티", 206000, "스투시 베이직 네이비", 30));

        food.addProduct(new Product("두바이쫀득쿠키", 6000, "여기가 제일 쌉니다", 5));
        food.addProduct(new Product("말차 피자", 19000, "교토 말차 마르게리타 피자", 10));
        food.addProduct(new Product("마라탕", 30000, "올토핑 추가 마라탕", 4));

        categories.add(electronics);
        categories.add(clothes);
        categories.add(food);
    }

//    public void addCategory(Category category) {
//        categories.add(category);
//    }
    // !CommerceSystem 생성자에 객체 생성을 했는데 addCategory는 쓸모가 없어진게 아닌가 튜터님께 여쭤보기!

    public void start() {

        while (true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 메인 ] ");

            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategoryName()); // 리스트.get(번호)
            } //                    (i+1)번 뒤에, i번째 카테고리의 이름을 붙여서 화면에 찍어라

            System.out.println("0. 종료       | 프로그램 종료");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            if (choice < 1 || choice > categories.size()) {
                System.out.println("존재하지 않는 카테고리입니다.");
                continue;
            }

            while (true) {
                Category cat = categories.get(choice - 1); // list.get(사용자입력 - 1)
                System.out.println("\n[ " + cat.getCategoryName() + " 카테고리 ]");

                for (int i = 0; i < cat.getProducts().size(); i++) {
                    cat.getProducts().get(i).printInfo(i + 1);
                }

                System.out.println("0. 뒤로가기");
                int choice1 = sc.nextInt();
                if (choice1 == 0) {
                    break;
                }
                if (choice1 < 1 || choice1 > cat.getProducts().size()) {
                    System.out.println("존재하지않는 상품입니다.");
                    continue;
                }

                Product select = cat.getProducts().get(choice1 - 1);
                // 이 코드 질문하기
                System.out.println("선택한 상품: "
                        + select.getName() + " | "
                        + select.getPrice() + "원 | "
                        + select.getExplanation() + " | 재고: "
                        + select.getStock() + "개");
                break;
//                if (choice1 == 0) {
//                    break;
//                } else if (choice1 >= 1 && choice1 <= cat.getProducts().size()) {
//                    // 정상 선택
//                    Product selected = cat.getProducts().get(choice1 - 1);
//    ...
//                } else {
//                    System.out.println("존재하지 않는 상품입니다.");
//                }
            }
        }
    }
}
