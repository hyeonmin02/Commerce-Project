package example;

    public enum CustomerGrade { //enum 개념 정확히 뭔가? enum은 class이름을 붙이지않고도 왜 가능한가?
        BRONZE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15);

        private final int discountPercent;

        CustomerGrade(int discountPercent) {
            this.discountPercent = discountPercent;
        }

        public int getDiscountPercent() {
            return discountPercent;
        }

        // 총액에 할인 적용한 최종 금액
        public int applyDiscount(int totalPrice) {
            return totalPrice * (100 - discountPercent) / 100;
        }
    }
