package com.budgettool.budget_tool.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double income;
    private Double expenses;
    private Double balance;

    // Calculate the balance based on income and expenses
    public void calculateBalance() {
        this.balance = this.income - this.expenses;
    }
}