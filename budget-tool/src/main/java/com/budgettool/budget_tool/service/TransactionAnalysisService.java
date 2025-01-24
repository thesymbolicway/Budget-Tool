package com.budgettool.budget_tool.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionAnalysisService {
    
    public static class TransactionSummary {
        private final BigDecimal largestDeposit;
        private final BigDecimal largestWithdrawal;
        private final BigDecimal totalBalance;

        public TransactionSummary(BigDecimal largestDeposit, BigDecimal largestWithdrawal, BigDecimal totalBalance) {
            this.largestDeposit = largestDeposit;
            this.largestWithdrawal = largestWithdrawal;
            this.totalBalance = totalBalance;
        }

        public BigDecimal getLargestDeposit() { return largestDeposit; }
        public BigDecimal getLargestWithdrawal() { return largestWithdrawal; }
        public BigDecimal getTotalBalance() { return totalBalance; }
    }

    public TransactionSummary analyzeTransactions(List<BigDecimal> transactions) {
        BigDecimal largestDeposit = BigDecimal.ZERO;
        BigDecimal largestWithdrawal = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (BigDecimal transaction : transactions) {
            total = total.add(transaction);
            if (transaction.compareTo(BigDecimal.ZERO) > 0 && 
                transaction.compareTo(largestDeposit) > 0) {
                largestDeposit = transaction;
            }
            if (transaction.compareTo(BigDecimal.ZERO) < 0 && 
                transaction.abs().compareTo(largestWithdrawal.abs()) > 0) {
                largestWithdrawal = transaction;
            }
        }

        return new TransactionSummary(largestDeposit, largestWithdrawal, total);
    }
} 