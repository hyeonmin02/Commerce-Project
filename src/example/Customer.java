package example;

//- [ ]  설명 : 고객 정보를 관리하는 클래스입니다.
// 필수과제에서는 출력값 X
public class Customer {
    String customerName;
    String email;
    String grade;
    int amount;

    // 생성자 (이번 주문 금액 포함된 누적 금액을 전달받음
    public Customer(String customerName, String email, int amount) {
        this.customerName = customerName;
        this.email = email;
        this.amount = amount;
        customerGrade(); // 등급 자동계산
    }

    // 총 구매금액에 따른 등급 관리
    void customerGrade() {
        if (amount < 5000000) {
            grade = "브론즈";
        } else if (amount < 1000000) {
            grade = "실버";
        } else if (amount < 2000000) {
            grade = "골드";
        } else {
            grade = "플래티넘";
        }
    }
}
