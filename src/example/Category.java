package example;

import java.util.ArrayList;
import java.util.List;
// Product 클래스를 List로 관리
// 구조에 맞게 메소드 선언

public class Category {
    private String categoryName;
    private List<Product> products = new ArrayList<>(); // 이 카테고리의 상품들
    // 1) 카테고리 이름만 받고, products는 비어있는 리스트로 시작 생성자

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }   // 카테고리 이름 반환 메서드

    // getter
    public String getCategoryName() { // String은 복사해서 주는 값
        return categoryName;
    }

    public List<Product> getProducts() { //  List는 같은 실물 객체를 주는 것
        return new ArrayList<>(products); // get 꺼내줘 (창고 열쇠 사진 보내줌)
    }
    // return List<Product> products; (우리 집 창고 열쇠를 통째로 남에게 주는 것)

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    // setter 상품추가 메서드
    public void addProduct(Product product) {
        products.add(product);
    }
    // 상품삭제 메서드
    public void removeProduct(Product product) {
        products.remove(product);
    }
}
//    public void setProducts(List<Product>products) {
//        this.products = products; // 값을 받아야 해서 이름이 필요 // set 바꿔줘 요청
//    }



