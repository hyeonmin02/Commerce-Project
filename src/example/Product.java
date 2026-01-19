package example;

//- [ ]  설명 : 개별 상품을 관리하는 클래스입니다. 현재는 전자제품만 관리합니다.
//- [ ]  클래스는 `상품명`, `가격`, `설명`, `재고수량` 필드를 갖습니다.
public class Product {
    String name;
    int price;
    String explanation;
    int stock;

    public Product(String name, int price, String explanation, int stock) {
        this.name = name;
        this.price = price;
        this.explanation = explanation;
        this.stock = stock;
    }

    void printInfo() {
        System.out.printf(
                "%-15s | %10d | %-35s%n",
                name, price, explanation
        );
    }
}


//    private String name;
//    private int price;
//    private String explanation;
//    private int stock;
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public void setExplanation(String explanation) {
//        this.explanation = explanation;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//    public String getName() {
//        return name;
//    }
//    public int getPrice() {
//        return price;
//    }
//    public String getExplanation() {
//        return  explanation;
//    }
//    public int getStock() {
//        return stock;
//    }
//}
