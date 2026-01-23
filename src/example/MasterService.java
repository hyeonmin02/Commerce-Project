package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class MasterService {
    private final List<Category> categories; // TODO new를 왜 안 써도 되는지 새 객체로 만든다고 인식할시 왜 final이 막히는지?
    private final Cart cart;
    private final Scanner scanner;

    private int readInt(String prompt) { // readInt 메서드 변수
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (MyInputException e) {
                System.out.println("숫자만 입력하세요.");
            }
        }
    }

    private Product findProductName() {
        System.out.print("수정할 상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        // 모든 카테고리를 돌면서 탐색
        for (Category c : categories) {
            for (Product p : c.getProducts()) {
                if (p.getName().equals(name)) {
                    return p;   // 찾으면 바로 리턴
                }
            }
        }

        // 끝까지 못 찾으면
        System.out.println("해당 상품을 찾을 수 없습니다.");
        return null;
    }


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
            System.out.print("1. 상품 추가\n2. 상품 수정\n3. 상품 삭제\n4. 전체 상품 현황\n0. 메인으로 돌아가기");
            // 쓸데없이 코드가 길어져서 \n로 한번에 정리
//            System.out.println("2. 상품 수정");
//            System.out.println("3. 상품 삭제");
//            System.out.println("4. 전체 상품 현황");
//            System.out.println("0. 메인으로 돌아가기");

            int choice = readInt("\n메뉴 선택: "); //readInt가 뭐니

            switch (choice) {
                case 1 -> addProduct(); // 추가
                case 2 -> editProduct(); // 수정
                case 3 -> deleteProduct(); // 삭제
                case 4 -> showAllProduct(); //전체 상품 조회
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
        scanner.nextLine();

        int count = 0;
        while (count < 3) {
            System.out.println("관리자 비밀번호 입력: ");
            String input = scanner.nextLine(); // 이전에 남긴 nextInt 엔터 처리 (안전장치)
            if (input.equals(MASTER_PASSWORD)) {
                return true;
            }
            count++;
            System.out.println("비밀번호가 틀렸습니다! (" + count + "/3)");
        }
        System.out.println("로그인 3회 실패로 관리자모드 접근을 차단합니다.");
        return false;
    }

    //        for (int loginAttempt = 1; loginAttempt <= 3; loginAttempt++) {
//            System.out.println("관리자 비밀번호 입력: ");
//            String input = scanner.nextLine();
//
//            if (MASTER_PASSWORD.equals(input)) {
//                System.out.println("인증 성공!");
//                return true;
//            }
//            System.out.println("비밀번호가 틀렸습니다! (" + loginAttempt + "/3)");
//        }
//        System.out.println("로그인 3회 실패로 메인 메뉴로 돌아갑니다.");
//        return false;
//    }

    // 1, 상품 추가 Category category = selectCategory(); if (category == null) return;
    private void addProduct() {
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }

        int idx = readInt("선택: ");
        if (idx < 1 || idx > categories.size()) {
            System.out.println("잘못된 선택입니다.");
            return;
        }
        Category category = categories.get(idx - 1);
        System.out.println("\n[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");
        System.out.print("상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        // 같은 카테고리 안에서 중복 상품명 검증
        if (category.sameProductName(name)) {
            System.out.println("같은 카테고리 내에 이미 존재하는 상품명입니다.");
            return;
        }

        int price = readInt("가격을 입력해주세요: ");

        System.out.print("상품 설명을 입력해주세요: ");

        String expl = scanner.nextLine();
        int stock = readInt("재고 수량을 입력해주세요: ");

        Product newProduct = new Product(name, price, expl, stock);
        System.out.println(
                newProduct.getName() + " | " +
                        String.format("%,d", newProduct.getPrice()) + "원 | " +
                        newProduct.getExplanation() + " | " +
                        "재고: " + newProduct.getStock() + "개"
        );

        System.out.println("위 정보로 상품을 추가하시겠습니까?");


        int choice1;
        while (true) {
            choice1 = readInt("1. 확인        2. 취소\n선택: ");
            if (choice1 == 1 || choice1 == 2) {
                break;
            }
            System.out.println("잘못된 입력입니다. 1 또는 2를 입력하세요.");
        }
        if (choice1 == 1) {
            category.addProduct(newProduct);
            System.out.println("\n상품이 성공적으로 추가되었습니다!");
        } else {
            System.out.println("\n상품 추가가 취소되었습니다.");
            return;
        }
    }

    // 2. 상품 수정
    private void editProduct() {
        Product target = findProductName();
        if (target == null) return;

        System.out.print("\n 수정할 항목을 선택해주세요: \n1. 가격\n2. 설명\n3. 재고수량");
        int choice = readInt("선택:");

        switch (choice) {
            case 1 -> {
                int beforePrice = target.getPrice(); // 기존 가격 저장
                System.out.println("현재 가격: " + String.format("%,d", beforePrice) + "원");
                int newPrice = readInt("새로운 가격을 입력해주세요: ");
                target.setPrice(newPrice);

                System.out.println(
                        target.getName() + "의 가격이 " +
                                String.format("%,d", beforePrice) + "원 → " +
                                String.format("%,d", newPrice) + "원으로 수정되었습니다."
                );
            }
            case 2 -> {
                String beforeExpl = target.getExplanation(); // 기존 설명 저장
                System.out.println("현재 설명: " + beforeExpl);
                String newExpl = scanner.nextLine();
                target.setExplanation(newExpl);
                System.out.println(beforeExpl + "라는 설명이 " + newExpl + "라고 수정되었습니다.");
            }
            case 3 -> {
                int beforeStock = target.getStock(); // 기존 재고 저장
                System.out.println("현재 재고: " + beforeStock + "개");
                int delta = readInt("변경할 재고 수량을 입력해주세요 (추가: +, 감소: -): ");
                int afterStock = beforeStock + delta;
                if (afterStock < 0) {
                    System.out.println("아무래도 재고는 0개 미만이 안되죠?(현재: " + beforeStock + "개)");
                    return;
                }
                target.setStock(afterStock);

                if (delta > 0) {
                    System.out.println(
                            target.getName() + "의 재고가" +
                                    beforeStock + "개 → " +
                                    afterStock + "개로 변경되었습니다. (+" + delta + "개");
                } else if (delta < 0) {
                    System.out.println(target.getName() + "의 재고가" +
                            beforeStock + "개 → " +
                            afterStock + "개로 변경되었습니다. (" + delta + "개)");
                } else {
                    System.out.println("변경된 재고가 없습니다. (현재 재고: " + beforeStock + "개)");
                }
            }

        }
    }

    // 3. 상품 삭제 + 장바구니에서도 삭제
    private void deleteProduct() {
        Product target = findProductName();
        if (target == null) return;

        System.out.println("정말 삭제하시겠습니까? (y/n): ");
        String yn = scanner.nextLine();

        if (!yn.equalsIgnoreCase("y")) {
            System.out.println("삭제를 취소합니다.");
            return;
        }

        // 카테고리들에서 제거
        for (Category category : categories) {
            category.removeProduct(target);
        }

        // 장바구니에서도 제거
        cart.removeProduct(target);

        System.out.println("삭제 완료되었습니다.");
    }

    // 4. 전체상품 현황
    private void showAllProduct() {
        System.out.println("\n[ 전체 상품 목록 ]");
        for (Category category : categories) {
            System.out.println("\n[ "+ category.getCategoryName()+ "카테고리 ]");

            if (category.getProducts().isEmpty()) {
                System.out.println("상품 없음");
                continue;
            }
            for (Product p : category.getProducts()) {
                System.out.println("- 상품명: " + p.getName());
                System.out.println("   가격: " + p.getPrice());
                System.out.println("   설명: " + p.getExplanation());
                System.out.println("   재고: " + p.getStock());
                System.out.println();
            }
        }
    }
}
//  도우미 메서드들
//      // 카테고리 선택
//    private Category selectCategory() {
//        System.out.println("\n카테고리 선택:");
//        for (int i = 0; i < categories.size(); i++) {
//            System.out.println((i + 1) + ". " + categories.get(i).getName());
//        }
//        System.out.println("0. 취소");
//
//        int idx = readInt("선택: ");
//        if (idx == 0) return null;
//        if (idx < 1 || idx > categories.size()) {
//            System.out.println("잘못된 선택입니다.");
//            return null;
//        }
//        return categories.get(idx - 1);
//    }
//
//    // 상품명으로 전체 카테고리에서 검색
//    private Product findProductByName() {
//        System.out.print("상품명 입력: ");
//        String name = scanner.nextLine();
//
//        for (Category c : categories) {
//            Product p = c.findByName(name);
//            if (p != null) return p;
//        }
//
//        System.out.println("해당 상품을 찾지 못했습니다.");
//        return null;
//    }
//
//    // nextInt 안전 입력 (버퍼 이슈 방지)
//    private int readInt(String prompt) {
//        while (true) {
//            System.out.print(prompt);
//            try {
//                int value = Integer.parseInt(scanner.nextLine());
//                return value;
//            } catch (NumberFormatException e) {
//                System.out.println("숫자만 입력하세요.");



