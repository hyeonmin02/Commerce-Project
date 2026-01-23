package example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
// + 메인에 존재하지않는 카테고리 선택 시 예외처리 (음수값,정수가 아닌 문자값 입력시 예외처리)
//  재고처리 고객이 상품을 살때마다 재고가 줄어듬 + 재고 채워넣기 메서드 만들기 + 고객 누적금액 체크 + 등급 체크 + 예외처리

// 설명: 커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스입니다.
public class CommerceSystem {
    private DecimalFormat df = new DecimalFormat("#,###");
    private final List<Category> categories = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final Cart cart = new Cart();
//    private final Customer customer = new Customer("신현민",CustomerGrade.SILVER);

    public CommerceSystem() {
        Category clothes = new Category("의류");
        Category food = new Category("식품");
        Category electronics = new Category("전자제품");

        electronics.addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        electronics.addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 20));
        electronics.addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 100));
        electronics.addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10));
        electronics.addProduct(new Product("Galaxy Z Trifold", 3590400, "두 번 접히는 혁신적인 폼팩터", 5));

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
    // TODO !CommerceSystem 생성자에 객체 생성을 했는데 addCategory는 쓸모가 없어진게 아닌가 튜터님께 여쭤보기!
    public void start() {

        while (true) {
            System.out.println("\n아래 메뉴를 선택해주세요.");
            System.out.println("[ 실시간 커머스 플랫폼 메인 ] ");

            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategoryName()); // 리스트.get(번호)
            } //                    (i+1)번 뒤에, i번째 카테고리의 이름을 붙여서 화면에 찍어라
            System.out.println("0. 종료      | 프로그램 종료");
            System.out.println("6. 관리자 모드");

            // 장바구니가 비어있지 않을 때만 주문관리를 보여줌 첫 번쨰 출력화면에 안뜸
            if (!cart.isEmpty()) {
                System.out.println("\n[ 주문 관리 ]");
                System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
                System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
            }

            int choice = sc.nextInt();
            if (choice == 6) {
                MasterService masterService =
                        new MasterService(categories, cart, sc);
                masterService.start();
                continue;   // 관리자 모드 끝나면 다시 메인메뉴로
            }

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            if (choice >= 1 && choice <= categories.size()) {
                Category cat = categories.get(choice - 1); // list.get(사용자입력 - 1)

                while (true) {
                    System.out.println("\n[ " + cat.getCategoryName() + " 카테고리 ]");
                    for (int i = 0; i < cat.getProducts().size(); i++) {
                        cat.getProducts().get(i).printInfo(i + 1); // 카테고리 번호 매기기
                    }
                    System.out.println("0. 뒤로가기");

                    int choice1 = sc.nextInt();

                    if (choice1 == 0) {
                        break;
                    }
                    if (choice1 < 1 || choice1 > cat.getProducts().size()) {
                        System.out.println("존재하지 않는 상품입니다. 다시 입력해주세요.");
                        continue;
                    }

                    Product select = cat.getProducts().get(choice1 - 1);

                    System.out.println("선택한 상품: " + select.getName() + " | "
                            + df.format(select.getPrice()) + "원 | "
                            + select.getExplanation() + " | 재고: "
                            + select.getStock() + "개");

                    System.out.println();

                    System.out.println("\"" + select.getName() + " | "
                            + df.format(select.getPrice()) + " | "
                            + select.getExplanation() + "\"");

                    while (true) {
                        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인    2. 취소");

                        int choice2 = sc.nextInt();
                        if (choice2 == 2) {
                            break;
                        }
                        if (choice2 != 1) {
                            System.out.println("1 또는 2만 입력해주세요.");
                            continue;
                        }
                        System.out.print("담을 수량을 입력해주세요. \n수량: ");

                        int quantity;

                        try { // 바깥 try
                            // 숫자 입력 받기 (문자 입력 시 InputMismatchException 발생)
                            try {
                                quantity = sc.nextInt();
                            } catch (InputMismatchException e) { // 문자값 예외처리 클래스
                                sc.nextLine(); // 잘못 입력(문자 등) 버리기 // 엔터 제거시에도 사용
                                throw new MyInputException("숫자만 입력해주세요!"); // 다시 내 예외클래스로 받기
                            }

                            // 수량이 1 이상인지 검증
                            if (quantity <= 0) {
                                throw new MyInputException("수량은 1 이상이어야합니다.");
                            } // throw로 처리해봄

                            // 재고 부족은 '정상 상황' → 예외 말고 if + 안내로 처리
                            if (!select.hasStock(quantity)) { // 수량이 없을 때
                                System.out.println("재고가 부족합니다.");
                                continue; // 다시 입력받는 화면(현재 while)로
                            }

                            // 장바구니 담기 (Cart 내부에서도 예외 던질 수 있음)
                            cart.addProduct(select, quantity);

                            System.out.println(select.getName() + " 가 " + quantity + "개가 장바구니에 추가되었습니다.");
                            break; // 성공했으면 현재 루프 종료

                        } catch (MyInputException e) { // 바깥 catch
                            // MyException으로 통일해서 여기서 메시지 출력하고 다시 입력받기
                            System.out.println(e.getMessage());
                            continue;
                        }
//    TODO 장바구니에 추가가 되고 장바구니 확인으로 바로 가는 게 맞는건가 아님 메인으로 가서 장바구니로 들어가 주문완료를 처리하는 게 맞는지 여쭤보기
//    TODO 현재 장바구니 추가 출력문 이후 카테고리로 다시 돌아감 0 뒤로가기 입력 후 메인에서 장바구니로 들어가 주문완료 처리해야되는 상황
                    }
                }
                continue; // 메인으로 돌아가기

            } //장바구니 메서드
            if (choice == 4 || choice == 5) {
                if (cart.isEmpty()) {
                    System.out.println("장바구니가 비어있습니다. 먼저 상품을 담아주세요");
                    continue;
                }
            }

            if (choice == 4) {
                System.out.println("아래와 같이 주문하시겠습니까?");
            } // 주문취소

            if (choice == 5) {
                cart.clear();
                System.out.println("진행중인 주문을 취소했습니다.");
                continue;
            }
            System.out.println("\n[ 장바구니 내역 ]");
            for (CartItem item : cart.getItems()) {
                Product p = item.getProduct(); // 장바구니 항목이 가지고 있는 상품을 꺼내 'p'라는 지역변수에 담음(별명)
                System.out.println (p.getName() + " | "
                        + df.format(p.getPrice()) + "원 | "
                        + p.getExplanation() + " | "
                        + item.getQuantity() + "개");
            }

            System.out.println("\n[ 총 주문금액 ]");
            System.out.println(df.format(cart.getTotalPrice()) + "원");

            System.out.println("\n1. 주문확정     2. 메인으로 돌아가기");
            int decision = sc.nextInt();
            if (decision == 2) { // 2면 메인으로 돌아가기
                continue;
            }
            if (decision != 1) { // 1이 아니면
                System.out.println("1 또는 2만 입력해주세요! ");
                continue;
            } // 1도 아니고 2도 아니니 자동적으로 1은 주문확정으로 정해짐

            System.out.println("\n주문이 완료되었습니다! " + "총 금액: " + df.format(cart.getTotalPrice()) + "원");


            for (CartItem item : cart.getItems()) {
                Product p = item.getProduct();
                int q = item.getQuantity();

                int before = p.getStock();
                p.decreaseStock(q);
                int after = p.getStock();

                System.out.println(p.getName() + " 재고가 " + before + "개 → " + (after) + "개로 업데이트되었습니다.");
            }
            cart.clear(); // 주문 끝났으니 장바구니 비우기
            continue;
        }

    }
}
//                        if (quantity <= 0) {
//                            System.out.println("수량은 1 이상이어야합니다.");
//                            continue;
//                        }
//                        if (select.hasStock(quantity)) { // boolean 재고 체크 true 장바구니에 담음
//                            cart.addProduct(select, quantity); //cart에 담기 (재고 차감 x)
//                            System.out.println(select.getName() + "가 " + quantity + "개가 장바구니에 추가되었습니다.");
//                            break;

//                        } else { //false 재고부족 예외처리
//                            System.out.println("재고가 부족합니다.");
//                           continue;
//                        }