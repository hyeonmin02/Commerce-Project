package example;

import java.util.ArrayList;
import java.util.List;

// Product 클래스를 List로 관리

// 구조에 맞게 메소드 선언
public class Category {
    String categoryName;
    List<Product> products = new ArrayList<>(); // 이 카테고리의 상품들

    // 1) 카테고리 이름만 받고, products는 비어있는 리스트로 시작 생성자
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }   // 카테고리 이름 반환 메서드

    public String getCategoryName() {
        return categoryName; // 변경하기
    }

    //  상품추가 메서드
    public void addProduct(Product product) {
        products.add(product);
    }
}




