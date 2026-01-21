package example;

import java.text.DecimalFormat;

//- [ ]  설명 : 개별 상품을 관리하는 클래스입니다. 현재는 전자제품만 관리합니다.
//- [ ]  클래스는 `상품명`, `가격`, `설명`, `재고수량` 필드를 갖습니다.
public class Product {
    private String name;
    private int price;
    private String explanation;
    private int stock;

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //getter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getStock() {
        return stock;
    }

    public Product(String name, int price, String explanation, int stock) {
        this.name = name;
        this.price = price;
        this.explanation = explanation;
        this.stock = stock;
    }

    public void printInfo(int number) {
        DecimalFormat df = new DecimalFormat("#,###"); // 자바에서 큰 숫자 콤마 찍는 클래스
        String priceStr = df.format(price);

        System.out.printf("%d. %-16s | %10s원 | %s%n", // %d 숫자 (int) %s 문자열 (String) %s%n (문자열 출력 후 줄바꿈)
                number, name, priceStr, explanation);
    }

}
