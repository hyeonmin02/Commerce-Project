package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MasterService {
    private final List<Category> categories; // TODO new를 왜 안 써도 되는지 새 객체로 만든다고 인식할시 왜 final이 막히는지?
    private final Cart cart;
    private final Scanner scanner;

    private static final String MASTER_PASSWORD = "0116"; // 관리자 비밀번호

    public MasterService(List<Category> categories, Cart cart, Scanner scanner) {
        this.categories = categories;
        this.cart = cart;
        this.scanner = scanner;
    }

    public void start() {
        if (!authenticate()) return; // 인증 실패면 바로 메인메뉴로 복귀

        while (true) { // 관리자비밀번호 입력성공 시
            System.out.println("\n[ 관리자 모드 ]");
            System.out.print("1. 상품 추가\n2.상품 수정\n3.상품 삭제\n4.전체 상품 현황\n0. 메인으로 돌아가기");
            // 쓸데없이 코드가 길어져서 \n로 한번에 정리
//            System.out.println("2. 상품 수정");
//            System.out.println("3. 상품 삭제");
//            System.out.println("4. 전체 상품 현황");
//            System.out.println("0. 메인으로 돌아가기");

            int choice = readInt("메뉴 선택"); //readInt가 뭐니

            switch (choice) {
                case 1 -> addProduct(); // 추가
                case 2 -> editProduct(); // 수정
                case 3 -> deleteProduct(); // 삭제
                case 4 -> (); //전체 상품 조회
                case 0 -> {
                    System.out.println("관리자 모드를 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 3번 틀리면 종료
    private boolean authenticate() {
        scanner.nextLine(); // 이전에 남긴 nextInt 엔터 처리 (안전장치)

        for (int loginAttempt = 1; loginAttempt <= 3; loginAttempt++) {
            System.out.println("관리자 비밀번호 입력: ");
            String input = scanner.nextLine();

            if (MASTER_PASSWORD.equals(input)) {
                System.out.println("인증 성공!");
                return true;
            }
            System.out.println("비밀번호가 틀렸습니다! (" + loginAttempt + "/3)");
        }
        System.out.println("로그인 3회 실패로 메인 메뉴로 돌아갑니다.");
        return false;
    }

    // 상품 추가
    private void addProduct() {
        Category category = selectCategory();
        if (category == null) return;

        System.out.println("상품명: ");
        String name = scanner.nextLine();

        // 같은 카테고리 안에서 중복 상품명 검증
        if(category.sameProductName(name)) {
            System.out.println("같은 카테고리 내에 이미 존재하는 상품명입니다.");
            return;
        }

        int price = readInt("가격: ");
        System.out.println("설명: ");
        String expl = scanner.nextLine();
        int stock = readInt("재고 수량: ");

        category.addProduct(new Product(name, price, expl, stock));
        System.out.println("상품이 성공적으로 추가되었습니다!");
}
}
