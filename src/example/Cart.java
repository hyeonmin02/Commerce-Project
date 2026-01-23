package example;

import java.util.*;

public class Cart {
    // 장바구니에 담긴 "한 줄"들 (상품 + 수량)을 저장하는 리스트
    private List<CartItem> items = new ArrayList<>();

    // 상품 추가 (이미 담긴 상품이면 수량만 증가)
    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
        if (item.getProduct().equals(product)) { // item이 가지고 있는 상품과 지금 내가 넣으려는 상품이 같은가?
            item.setQuantity(item.getQuantity() + quantity); // 같은 상품이면,기존 수량에 새로 추가한 수량을 더해서 저장
//          현재 선택한 장바구니상품수량 수정(현재 선택한 장바구니 상품의 현재수량 + 추가한 수량)
            return;
        }
    }
        // 수량이 1보다 작으면 말이 안 되니까 그냥 무시(또는 예외)
//        if (quantity <= 0) {
//            throw new MyInputException("수량은 1 이상이어야 합니다");
//        }
        //이미 담겨있는 상품인지 확인

        // 장바구니 목록(items)에 새로운 장바구니 상품 하나를 만들어서 (상품,수량)을 넣어라
        items.add(new CartItem(product, quantity));
    }

    // 상품 삭제( 해당 상품 줄 자체를 제거)
    public boolean removeProduct(Product product) {
        return items.removeIf(item -> item.getProduct().equals(product));
        //  리스트 안을 전부 돌면서 조건이 true인 것들만 골라서 삭제 -> 람다식
//        for (CartItem item : items) {
//    if (item.getProduct().equals(product)) {
//        items.remove(item); 를 요약한 것
    } //TODO commerceSystem에 구현해야함

    // 수량 변경 (quantity로 "바로 설정")
    public void changeQuantity(Product product, int newQuantity) { // 기존 값에 덮어씌운다는 걸 보여주기 위해 newQuantity 매개변수사용
        if (newQuantity < 0) {
            throw new MyInputException("수량은 0 이상이어야 합니다.");
        }
//  TODO 수량 변경도 구현해야함
        for (CartItem item : items) { // items 장바구니 전체목록, item 그안의 상품 하나 (: ~안에 있는)
            // 장바구니 전체목록에 들어있는 상품 하나를 하니씩 꺼내본다
            if (item.getProduct().equals(product)) { //이 장바구니 항목(item)이 가지고 있는 Product 객체가, 지금 내가 비교하려는 Product 객체와 같은 객체인가?

                // 0개로 바꾸면 사실상 삭제가 자연스러움
                if (newQuantity == 0) {
                    items.remove(item);
                } else {
                    item.setQuantity(newQuantity); // 지금 보고있는 장바구니 상품 수량을 새로 입력한 수량(newQuantity)으로 바꿔라
                }
                return;
            }
        }
        // 여기까지 왔다는건 장바구니에 없는 상품을 수정하려는 것
        throw new MyInputException("장바구니에 없는 상품입니다.");
    }

    // 총 금액 계산
    public int getTotalPrice() {
        int total = 0;
        for (CartItem item : items) {
            // 한 줄 가격 = 상품가격 * 수량
            total += item.getTotal();
        }
        return total;
    }

    // 장바구니 초기화
    public void clear() {
        items.clear();
    }

    // 비었는지 확인
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // 주문/결제 로직에서 장바구니 내용을 돌려볼 때 필요
    public List<CartItem> getItems() {
        return items;
    }
}







