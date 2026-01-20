package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 설명: 커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스입니다.
public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    public List<Category> categories = new ArrayList<>(); // 카테고리들을 담아둔 리스트 전자제품,의류,식품 (Main에서 추가한 객체)

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void start() {
// while문 하나만 사용하였을 때 코드가 꼬임 두개로 나누니까 메인 따로 카테고리 리스트 따로 작동하는 모습 + int choice를 다른 변수로 생성하여 작동시키니 렉걸린거마냥 여러번 입력하니까 출력해줌 같은 while문에 다른 두개에 int 변수값을 넣으니 오류발생
        while (true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 메인 ] ");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).categoryName);
            }     //                                     리스트.get(번호)
            //“(i+1)번 뒤에, i번째 카테고리의 이름을 붙여서 화면에 찍어라
            System.out.println("0. 종료       | 프로그램 종료");
            int choice = sc.nextInt();
            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            if (choice == 2 || choice == 3) {
                System.out.println("그 카테고리는 현재 생성되지 않았습니다.");
                continue;
            }
            while (true) {
                Category cat = categories.get(0);
                System.out.println("\n[ " + cat.categoryName + " 카테고리 ]");
                // 무조건 리스트의 첫 번째(0번) 카테고리를 가져와라
                for (int i = 0; i < cat.products.size(); i++) {
                    cat.products.get(i).printInfo(i + 1);
                }

                System.out.println("0. 뒤로가기");
                int choice1 = sc.nextInt();
                if (choice1 == 0) {
                    break;
                }
                if (choice1 < 1 || choice1 > cat.products.size()) {
                    System.out.println("존재하지않는 상품입니다.");
                    continue;
                }

                Product selected = cat.products.get(choice1 - 1);

                System.out.println("선택한 상품: "
                        + selected.name + " | "
                        + selected.price + "원 | "
                        + selected.explanation + " | 재고: "
                        + selected.stock + "개");
                break;

            }
        }
    }
}
