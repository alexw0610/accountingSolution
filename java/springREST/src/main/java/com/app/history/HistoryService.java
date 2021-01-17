package com.app.history;

import com.app.expense.Expense;
import com.app.expense.ExpenseRepository;
import com.app.income.Income;
import com.app.income.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class HistoryService {


    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    @Autowired
    public HistoryService(ExpenseRepository expenseRepository, IncomeRepository incomeRepository){
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public Map<LocalDate, Float> getMonthlyHistory(LocalDate now) {

        java.time.LocalDate start = now.minus(12,ChronoUnit.MONTHS).withDayOfMonth(1);
        java.time.LocalDate end = now.withDayOfMonth(1);

        Iterable<Expense> expenses = expenseRepository.findAll();
        Iterable<Income> incomes = incomeRepository.findAll();

        Map<LocalDate, Float> months = new LinkedHashMap<>();

        for (int i = 1; i <= 12; i++) {
            months.put(start.plus(i, ChronoUnit.MONTHS), 0.0f);
        }

        for (Expense expense : expenses) {

            java.time.LocalDate date = LocalDate.parse(expense.getDate()).withDayOfMonth(1);

            if (months.containsKey(date) && !expense.isReoccurring()) {
                float temp = months.get(date) - expense.getValue();
                months.put(date, temp);
            }

            if (expense.isReoccurring()) {
                months.forEach((k, v) -> {
                    months.put(k, v - expense.getValue());
                });
            }

        }

        for (Income income : incomes) {

            java.time.LocalDate date = LocalDate.parse(income.getDate()).withDayOfMonth(1);

            if (months.containsKey(date) && !income.isReoccurring()) {
                float temp = months.get(date) + income.getValue();
                months.put(date, temp);
            }

            if (income.isReoccurring()) {
                months.forEach((k, v) -> {
                    months.put(k, v + income.getValue());
                });
            }
        }

        return months;

    }

    public Map<LocalDate, Float> getMonthlyHistoryCumulative(LocalDate now) {

        Map<LocalDate, Float> monthlyDeltas = getMonthlyHistory(now);

        monthlyDeltas.forEach((k, v) -> {

            LocalDate nextMonth = k.plus(1, ChronoUnit.MONTHS);

            if(monthlyDeltas.containsKey(nextMonth)){
                monthlyDeltas.put(nextMonth, monthlyDeltas.get(nextMonth)+v);
            }

        });

        return monthlyDeltas;
    }
}