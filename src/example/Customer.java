package example;

//- [ ]  설명 : 고객 정보를 관리하는 클래스입니다.
// 필수과제에서는 출력값 X
public class Customer {
    private String customerName;
    private String email;
    private String grade;
    private long amount; // (int는 최대 약 21억까지만 출력)

    // setter
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    // getter
    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }

    public long getAmount() {
        return amount;
    }

    // 생성자 (이번 주문 금액 포함된 누적 금액을 전달받음
    public Customer(String customerName, String email, long amount) {
        this.customerName = customerName;
        this.email = email;
        this.amount = amount;
        customerGrade(); // 등급 자동계산
    }

    // 총 구매금액에 따른 등급 관리
    void customerGrade() {
        if (amount < 500000) { //50만 미만
            grade = "브론즈";
        } else if (amount < 1000000) { //100만 미만 (위 코드에서 이미 50만미만은 브론즈라고 걸려져 50만 < amount <100만으로 해석)
            grade = "실버";
        } else if (amount < 2000000) { //200만 미만
            grade = "골드";
        } else { // 그 이상은 모두 플래티넘
            grade = "플래티넘";
        }
    }
}
