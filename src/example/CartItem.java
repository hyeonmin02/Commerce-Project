package example;

public class CartItem {
    private Product product; // 상품
    private int quantity; // 수량

    public CartItem(Product product, int quantity) { // (상품 하나와 그 상품의 수량을 받아서 저장)한다는 객체 생성
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTotal() {
        return product.getPrice() * quantity;
    }
}
