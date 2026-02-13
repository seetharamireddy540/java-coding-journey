package com.example.javacodingjourney.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaPrimitives {
    public static void main(String[] args) {
        LoanApplication la1 = new LoanApplication("123", BigDecimal.valueOf(110));
        LoanApplication la2 = new LoanApplication("124", BigDecimal.valueOf(50.5));
        List<LoanApplication> loanApplications = List.of(la1, la2);
        Set<LoanApplication> fee = loanApplications.stream()
                .filter(la -> la.getLoanAmount().compareTo(BigDecimal.valueOf(100)) >= 0)
                .map(la -> {
                    BigDecimal loanFee = la.getLoanAmount().multiply(BigDecimal.valueOf(0.12)); // 12%
                    la.setLoanFee(loanFee);
                         return  la;
                        }
                ).collect(Collectors.toSet());

        fee.forEach(System.out::println);
        System.out.println(fee);

    }

    public static class LoanApplication {
        private BigDecimal loanAmount;
        private String loadId;
        private BigDecimal loanFee;

        public LoanApplication(String loadId, BigDecimal loanAmount) {
            this.loadId = loadId;
            this.loanAmount = loanAmount;
        }

        public void setLoadId(String loadId) {
            this.loadId = loadId;
        }

        public void setLoanAmount(BigDecimal loanAmount) {
            this.loanAmount = loanAmount;
        }

        public BigDecimal getLoanFee() {
            return loanFee;
        }

        public void setLoanFee(BigDecimal loanFee) {
            this.loanFee = loanFee;
        }

        public String getLoadId() {
            return loadId;
        }

        public BigDecimal getLoanAmount() {
            return loanAmount;
        }

        @Override
        public String toString() {
            return "LoanApplication{" +
                    "loadId='" + loadId + '\'' +
                    ", loanAmount=" + loanAmount +
                    ", loanFee=" + loanFee +
                    '}';
        }
    }
}
